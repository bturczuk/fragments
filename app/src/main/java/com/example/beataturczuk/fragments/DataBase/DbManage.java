package com.example.beataturczuk.fragments.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.beataturczuk.fragments.DataBase.dbObjects.Quote;

import java.sql.SQLException;

/**
 * Created by beataturczuk on 13.04.15.
 */
public class DbManage {

    private Context context;

    public DbManage(Context context) {
        this.context = context;
    }


    private static DbHelper dbHelper;
    private static SQLiteDatabase database;
    private Quote quote;


    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public static void open() throws SQLException {
           database = dbHelper.getWritableDatabase();
       }

      public void close() {
          dbHelper.cleanDatabase(database);
      }

    public void cleanDB() {
        dbHelper.cleanDatabase(database);
    }
}
