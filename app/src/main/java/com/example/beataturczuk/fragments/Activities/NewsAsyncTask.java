package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Internet.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

            String nius;
            String id;
            String type;
            String created;
            String published;
            String user_id;
            String source;
            String image;
            String title;
            String body;


            for (int i = 0; i < news.length(); i++) {
                try {
                    JSONObject jObject = news.getJSONObject(i);
                    id = jObject.getString(ApplicationConstants.NewsConstants.NEWS_ID).toString();
                    type = jObject.getString(ApplicationConstants.NewsConstants.TYPE).toString();
                    created = jObject.getString(ApplicationConstants.NewsConstants.CREATED).toString();
                    published = jObject.getString(ApplicationConstants.NewsConstants.PUBLISHED).toString();
                    user_id = jObject.getString(ApplicationConstants.NewsConstants.USER_ID).toString();
                    source = jObject.getString(ApplicationConstants.NewsConstants.SOURCE).toString();
                    image = jObject.getString(ApplicationConstants.NewsConstants.IMAGE).toString();
                    title = jObject.getString(ApplicationConstants.NewsConstants.TITLE).toString();
                    body = jObject.getString(ApplicationConstants.NewsConstants.BODY).toString();

                    nius = "TITLE:" + title + "\n NEWS: " + body + "\n PUBLISHED: " + published;

                    mTextView.setText(nius);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
