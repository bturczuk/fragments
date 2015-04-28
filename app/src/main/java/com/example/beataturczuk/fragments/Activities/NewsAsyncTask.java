package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Internet.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeSet;

/**
 * Created by beataturczuk on 22.04.15.
 */
public class NewsAsyncTask extends AsyncTask<String, String, String> {

    public NewsAsyncTask(Activity activity, Context context, TextView textView) {
        this.mActivity = activity;
        this.mTextView = textView;
        this.mContext = context;
        mMydb = new DbHelper(context);
        mDbManage = new DbManage(context);
    }

    private TableQuote mTableQuote;
    private Activity mActivity;
    private DbHelper mMydb;
    private TextView mTextView;
    private Context mContext;
    private DbManage mDbManage;


    @Override
    protected void onPreExecute() {
        CommandData.networkConnection(mContext, mActivity);
    }

    @Override
    protected String doInBackground(String... urls) {
        return JsonParser.httpGetRequest(CommandData.NEWS_URL_ADDRESS);
    }

    @Override
    protected void onPostExecute(String result) {

        try {
            JSONObject json = new JSONObject(result);
            JSONArray news = json.getJSONArray(ApplicationConstants.NewsConstants.PRODUCTS);
            Log.d("dodalem!!!!", "" + news.toString());

            String nius;
            String type;
            String created;
            String published;
            String comment_count;
            String user_id;
            String source;
            String image;
            String title;
            String body;


            for (int i = 0; i < news.length(); i++) {
                try {
                    JSONObject jObject = news.getJSONObject(i);
                    type = jObject.getString(ApplicationConstants.NewsConstants.TYPE).toString();
                    created = jObject.getString(ApplicationConstants.NewsConstants.CREATED).toString();
                    published = jObject.getString(ApplicationConstants.NewsConstants.PUBLISHED).toString();
                    comment_count = jObject.getString(ApplicationConstants.NewsConstants.COMMENT_COUNT).toString();
                    user_id = jObject.getString(ApplicationConstants.NewsConstants.USER_ID).toString();
                    source = jObject.getString(ApplicationConstants.NewsConstants.SOURCE).toString();
                    image = jObject.getString(ApplicationConstants.NewsConstants.IMAGE).toString();
                    title = jObject.getString(ApplicationConstants.NewsConstants.TITLE).toString();
                    body = jObject.getString(ApplicationConstants.NewsConstants.BODY).toString();

                    nius = "TITLE:" + title + "\n NEWS: " + body + "\n PUBLISHED: " + published;
                    //Log.d("dodalem!!!!", nius);

                    ContentValues mContentValues = new ContentValues();
                    mContentValues.put(TableNews.COLUMN_TYPE, type);
                    mContentValues.put(TableNews.COLUMN_CREATED, created);
                    mContentValues.put(TableNews.COLUMN_PUBLISHED, published);
                    mContentValues.put(TableNews.COLUMN_COMMENT_COUNT, comment_count);
                    mContentValues.put(TableNews.COLUMN_USER_ID, user_id);
                    mContentValues.put(TableNews.COLUMN_SOURCE, source);
                    mContentValues.put(TableNews.COLUMN_IMAGE, image);
                    mContentValues.put(TableNews.COLUMN_TITLE, title);
                    mContentValues.put(TableNews.COLUMN_BODY, body);

                    mDbManage.open();
                    mDbManage.setNews(mContentValues);

                    if (mTextView != null){
                        mTextView.setText(nius);
                    }

                    mDbManage.close();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            Toast.makeText(mContext, "Dodano newsy", Toast.LENGTH_SHORT);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
