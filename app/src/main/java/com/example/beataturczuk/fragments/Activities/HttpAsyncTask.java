package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.app.Application;
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
import com.example.beataturczuk.fragments.R;




/**
 * Created by beataturczuk on 18.03.15.
 */
public class HttpAsyncTask extends AsyncTask<String, String, String> {


    public HttpAsyncTask(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;
        mydb = new DBHelper(activity);
    }


    private Activity activity;
    private DBHelper mydb;
    private Context context;

    //checking if you have internet connection
    @Override
    protected void onPreExecute() {

        if (!NetworkConnection.networkStatus(context)) {
            NetworkConnection.noNetworkDialog(activity);
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

                    mydb.insertData(body, author);

                    ((TextView) activity.findViewById(R.id.etResponse)).setText(str);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
