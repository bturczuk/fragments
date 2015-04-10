package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Helpers.DBHelper;
import com.example.beataturczuk.fragments.Internet.JsonParser;
import com.example.beataturczuk.fragments.Internet.NetworkConnection;


/**
 * Created by beataturczuk on 18.03.15.
 */
public class HttpAsyncTask extends AsyncTask<String, String, String> {


    public HttpAsyncTask(Activity activity, Context context, TextView textView) {
        this.mActivity = activity;
        this.mContext = context;
        this.mTextView = textView;
        mMydb = new DBHelper(context);
    }


    private Activity mActivity;
    private DBHelper mMydb;
    private Context mContext;
    private TextView mTextView;

    //checking if you have internet connection
    @Override
    protected void onPreExecute() {

        if (!NetworkConnection.networkStatus(mContext)) {
            NetworkConnection.noNetworkDialog(mActivity);
        } else {
            return;
        }
    }

    @Override
    protected String doInBackground(String... urls) {

        return JsonParser.httpGetRequest(urls[0]);
    }


    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(String result) {

        try {
            JSONObject json = new JSONObject(result);
            JSONArray articles = json.getJSONArray(CommandData.PRODUCTS);

            String str;
            String body;
            String author;


            for (int i = 0; i < articles.length(); i++) {
                try {
                    JSONObject jObject = articles.getJSONObject(i);
                    body = jObject.getString(CommandData.BODY).toString();
                    author = jObject.getString(CommandData.AUTHOR).toString();
                    str = "MESSAGE: " + body + "\n\nAUTHOR: " + author;

                    mMydb.insertData(body, author);

                    mTextView.setText(str);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
