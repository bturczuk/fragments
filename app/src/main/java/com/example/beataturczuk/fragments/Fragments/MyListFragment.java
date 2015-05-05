package com.example.beataturczuk.fragments.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.ListFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.beataturczuk.fragments.Activities.OneNews;
import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbObjects.News;
import com.example.beataturczuk.fragments.DataBase.dbObjects.Quote;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Helpers.NewsAdapter;
import com.example.beataturczuk.fragments.Internet.JsonParser;
import com.example.beataturczuk.fragments.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by beataturczuk on 24.04.15.
 */
public class MyListFragment extends ListFragment {

    public ArrayList<HashMap<String, String>> items = new ArrayList<HashMap<String, String>>();
    public static final String KEY_NEWS_ID = "news_id";
    public static final String TITLE_NEWS = "title_news";

    private Activity mActivity;
    private DbManage dbManage;
    SQLiteDatabase sqLiteDatabase;
    private List<News> all_news;
    ProgressDialog dialog;
    int success;


    private ArrayList<HashMap<String, String>> setListValues() {
        DbManage dbManage = new DbManage(getActivity());
        dbManage.open();
        //news = dbManage.getNews();
        List<News> all_news = dbManage.getNews();

        for (int i = 0; i < all_news.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();

            map.put(TableNews.COLUMN_TITLE, all_news.get(i).title);
            map.put(TableNews.COLUMN_BODY, all_news.get(i).body);
            map.put(TableNews.COLUMN_ID, all_news.get(i).column_id);

            items.add(map);

            dbManage.close();

        }
        return items;

    }

   @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       super.onListItemClick(l, v, position, id);

       l.getItemAtPosition(position).toString();
       l.getAdapter().getItem(position).toString();

       items.get(position).toString();

       Intent intent = new Intent(getActivity(), OneNews.class);

       intent.putExtra(KEY_NEWS_ID, items.get(position).toString());

       startActivity(intent);
   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.listview, container, false);

        return rootView;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getNewsFromServer mGetNews = new getNewsFromServer();
        mGetNews.execute();

        NewsAdapter adapter = new NewsAdapter(getActivity(), setListValues());
        setListAdapter(adapter);
    }

    public void showProgressDialog (Context mContext) {
        dialog = new ProgressDialog(mContext);

        if (dialog.isShowing()){
            dialog.dismiss();
            dialog.cancel();
        }else{
            dialog.show();
        }
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.progres_dialog_layout);
    }

    class getNewsFromServer extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog(getActivity());
        }

        @Override
        protected Void doInBackground(Void... params) {
            ContentValues mContentValues = new ContentValues();
            DbManage db = new DbManage(getActivity());

            JsonParser jParser = new JsonParser();
            JSONObject jResponse = jParser.makeHttpRequest(CommandData.NEWS_URL_ADDRESS, "GET", null, getActivity().getApplicationContext());

                if (jResponse != null){
                    try {
                        success = jResponse.getInt(ApplicationConstants.ApiConstans.SUCCESS);
                            if (success == 1){
                                JSONArray jArray = jResponse.getJSONArray(ApplicationConstants.ApiConstans.PRODUCTS);
                                //Rozpoczynamy ładowanie do Bazy
                                for (int i = 0; i < jArray.length(); i++) {
                                    JSONObject jObject = jArray.getJSONObject(i);
                                        mContentValues.put(TableNews.COLUMN_TYPE, jObject.getString(ApplicationConstants.NewsConstants.TYPE));
                                        mContentValues.put(TableNews.COLUMN_CREATED, jObject.getString(ApplicationConstants.NewsConstants.CREATED));
                                        mContentValues.put(TableNews.COLUMN_PUBLISHED, jObject.getString(ApplicationConstants.NewsConstants.PUBLISHED));
                                        mContentValues.put(TableNews.COLUMN_COMMENT_COUNT, jObject.getString(ApplicationConstants.NewsConstants.COMMENT_COUNT));
                                        mContentValues.put(TableNews.COLUMN_USER_ID, jObject.getString(ApplicationConstants.NewsConstants.USER_ID));
                                        mContentValues.put(TableNews.COLUMN_SOURCE, jObject.getString(ApplicationConstants.NewsConstants.SOURCE));
                                        mContentValues.put(TableNews.COLUMN_IMAGE, jObject.getString(ApplicationConstants.NewsConstants.IMAGE));
                                        mContentValues.put(TableNews.COLUMN_TITLE, jObject.getString(ApplicationConstants.NewsConstants.TITLE));
                                        mContentValues.put(TableNews.COLUMN_BODY, jObject.getString(ApplicationConstants.NewsConstants.BODY));

                                            db.open();
                                            db.setNews(mContentValues);
                                            db.close();
                                }

                            } else {
                                // mamy błąd! w WEB API!
                            }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getActivity(), "Pobrano dane!", Toast.LENGTH_SHORT);
            dialog.dismiss();
        }
    }
}


