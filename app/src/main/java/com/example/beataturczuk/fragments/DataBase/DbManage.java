package com.example.beataturczuk.fragments.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

//import com.example.beataturczuk.fragments.Activities.GetNewsAsyncTask;
import com.example.beataturczuk.fragments.DataBase.dbObjects.News;
import com.example.beataturczuk.fragments.DataBase.dbObjects.Quote;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beataturczuk on 13.04.15.
 */
public class DbManage {

    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private Quote quote;
    private TextView mTextView;
    private ContentValues mContentValues;
    private DbManage mDbManage;
    private Context context;
    private String newsForList;

    public DbManage(Context context) {
       //inicjowanie :)
       dbHelper = new DbHelper(context);
    }


    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void open(){
           database = dbHelper.getWritableDatabase();
       }

      public void close() {
          dbHelper.close();
      }

    public void cleanDB() {
        dbHelper.cleanDatabase(database);
    }

    public void setQuote(ContentValues mContentValues) {
        try {
            database.update(
                    TableQuote.TABLE_NAME,
                    mContentValues, null, null
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNews(ContentValues mContentValues) {
        try {
            database.insert(
                    TableNews.TABLE_NAME,
                    null,
                    mContentValues
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFirstNews(ContentValues mContentValues) {
        try {
            database.insert(
                    TableQuote.TABLE_NAME,
                    null,
                    mContentValues
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<News> getNews() {
        List<News> news = new ArrayList<News>();

        Cursor mCursor = database.query(
                TableNews.TABLE_NAME,
                new String[]{
                        TableNews.COLUMN_ID,
                        TableNews.COLUMN_TYPE,

                        TableNews.COLUMN_CREATED,
                        TableNews.COLUMN_PUBLISHED,
                        TableNews.COLUMN_COMMENT_COUNT,
                        TableNews.COLUMN_USER_ID,

                        TableNews.COLUMN_SOURCE,
                        TableNews.COLUMN_IMAGE,
                        TableNews.COLUMN_TITLE,

                        TableNews.COLUMN_BODY
                        },

                null,
                null,
                null,
                null,
                null
        );
        if (mCursor.moveToFirst()) {
            while (!mCursor.isAfterLast()) {
                news.add(
                        new News(
                                mCursor.getString(0),
                                mCursor.getString(1),
                                mCursor.getString(2),
                                mCursor.getString(3),
                                mCursor.getString(4),
                                mCursor.getString(5),
                                mCursor.getString(6),
                                mCursor.getString(7),
                                mCursor.getString(8),
                                mCursor.getString(9)
                        )
                );
                mCursor.moveToNext();
            };
        }
        mCursor.close();
        return news;
    }

    public void getFirstNews(String result) {

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
