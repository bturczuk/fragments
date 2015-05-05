package com.example.beataturczuk.fragments.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbObjects.News;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Helpers.NewsAdapter;
import com.example.beataturczuk.fragments.Internet.JsonParser;
import com.example.beataturczuk.fragments.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by beataturczuk on 05.05.15.
 */
public class FirstFragment extends Fragment {

    private Activity mActivity;
    private DbManage dbManage;
    SQLiteDatabase sqLiteDatabase;
    private List<News> all_news;
    ProgressDialog dialog;
    int success;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment, container, false);

        return rootView;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getNewsFromServer mGetNews = new getNewsFromServer();
        mGetNews.execute();

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

    class getNewsFromServer extends AsyncTask<Void, Void, Void> {
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
            JSONObject jResponse = jParser.makeHttpRequest(CommandData.URL_ADDRESS, "GET", null, getActivity().getApplicationContext());

            if (jResponse != null){
                try {
                    success = jResponse.getInt(ApplicationConstants.ApiConstans.SUCCESS);
                    if (success == 1){
                        JSONArray jArray = jResponse.getJSONArray(ApplicationConstants.ApiConstans.PRODUCTS);
                        //Rozpoczynamy ładowanie do Bazy
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObject = jArray.getJSONObject(i);
                            mContentValues.put(TableQuote.COLUMN_AUTHOR, jObject.getString(ApplicationConstants.ApiQuoteKeys.AUTHOR));
                            mContentValues.put(TableQuote.COLUMN_BODY, jObject.getString(ApplicationConstants.ApiQuoteKeys.BODY));

                            db.open();
                            db.setFirstNews(mContentValues);
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