package com.example.beataturczuk.fragments.Internet;

import android.content.Context;
import android.util.Log;

import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by beataturczuk on 26.03.15.
 */
public class JsonParser {
    public static final String TAG = "JSONParser ";

    private static InputStream mInputStream = null;
    private static JSONObject mJObj = null;
    private static String mJson = "";

    public static final String GET = "GET";
    public static final String POST = "POST";

    public JsonParser() {
    }

    public JSONObject makeHttpRequest(String url, String method, JSONObject params, Context context) {

        try {

            HttpParams httpParams = new BasicHttpParams();
            httpParams.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            HttpClient httpClient = new DefaultHttpClient(httpParams);

            if (POST.equals(method)) {

                HttpPost httpPost = new HttpPost(url);
                httpPost.addHeader("content-type", "application/json");
                httpPost.setEntity(new StringEntity(params.toString(), HTTP.UTF_8));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                mInputStream = httpEntity.getContent();

            } else if (GET.equals(method)) {
                HttpGet httpGet = new HttpGet(url);
                httpGet.addHeader("content-type", "application/json");

                HttpResponse httpResponse = httpClient.execute(httpGet);

                HttpEntity httpEntity = httpResponse.getEntity();
                mInputStream = httpEntity.getContent();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(mInputStream, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            mInputStream.close();
            mJson = sb.toString();

            Log.d(TAG, "mJson = " + mJson);

        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try {
            mJObj = new JSONObject(mJson);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return mJObj;
    }
}
