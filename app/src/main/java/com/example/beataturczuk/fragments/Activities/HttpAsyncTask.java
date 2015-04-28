package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Internet.JsonParser;

import java.sql.SQLException;


/**
 * Created by beataturczuk on 18.03.15.
 */
public class HttpAsyncTask extends AsyncTask<String, String, String> {


    public HttpAsyncTask(Activity activity, Context context, TextView textView) {
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


    //checking if you have internet connection
    @Override
    protected void onPreExecute() {
        CommandData.networkConnection(mContext, mActivity);
    }

    @Override
    protected String doInBackground(String... urls) {
        return JsonParser.httpGetRequest(CommandData.URL_ADDRESS);
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {

        try {
            JSONObject json = new JSONObject(result);
            JSONArray articles = json.getJSONArray(ApplicationConstants.ApiQuoteKeys.PRODUCTS);

            String str;
            String body;
            String author;


            for (int i = 0; i < articles.length(); i++) {
                try {
                    JSONObject jObject = articles.getJSONObject(i);
                    body = jObject.getString(ApplicationConstants.ApiQuoteKeys.BODY).toString();
                    author = jObject.getString(ApplicationConstants.ApiQuoteKeys.AUTHOR).toString();
                    str = "MESSAGE: " + body + "\n\nAUTHOR: " + author;

                    mDbManage.open();
                    mTextView.setText(str);
                    mDbManage.close();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
