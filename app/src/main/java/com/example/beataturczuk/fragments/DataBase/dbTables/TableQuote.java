package com.example.beataturczuk.fragments.DataBase.dbTables;

import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beataturczuk on 10.04.15.
 */
public class TableQuote {

    private SQLiteDatabase database;
    private Context mContext;

    public static final String TABLE_NAME = "quote";
    public static final String COLUMN_ID = "_id"; // (z podkresleniem nazywamy id w bazie, bez podkreslenia id zewnetrzne)
    public static final String COLUMN_AUTHOR = "author";
    public static final String COLUMN_BODY = "body";

    public static final String DATABASE_CREATE = " create table " +
            TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_BODY + " text not null, "
            + COLUMN_AUTHOR + " text not null"
            + "); ";

    //Zawsze tzreba też wstrzyknac puste dane:

    public static final String INSERT_BLANK_QUOTE = "insert into " +
            TABLE_NAME
            + "("
            + COLUMN_BODY
            + ","
            + COLUMN_AUTHOR
            + ")"
            + " values "
            + "('','');";


    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        db.execSQL(INSERT_BLANK_QUOTE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(),
                "Upgrading database from version " + oldVersion +
                        " to " + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}


