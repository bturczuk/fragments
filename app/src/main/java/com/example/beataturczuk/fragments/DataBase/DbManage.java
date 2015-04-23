package com.example.beataturczuk.fragments.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.beataturczuk.fragments.DataBase.dbObjects.Quote;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;

import java.sql.SQLException;

/**
 * Created by beataturczuk on 13.04.15.
 */
public class DbManage {

    private  DbHelper dbHelper;
    private  SQLiteDatabase database;
    private Quote quote;

    private Context context;

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
          dbHelper.cleanDatabase(database);
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
}
