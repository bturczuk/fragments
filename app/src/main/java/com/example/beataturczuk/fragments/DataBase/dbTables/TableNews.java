package com.example.beataturczuk.fragments.DataBase.dbTables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.DataBase.dbObjects.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beataturczuk on 22.04.15.
 */
public class TableNews {

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


    private SQLiteDatabase database;
    private Context mContext;
    private ContentValues mContentValues;


    public static final String TABLE_NAME = "news";
    public static final String COLUMN_ID = "column_id"; // (z podkresleniem nazywamy id w bazie, bez podkreslenia id zewnetrzne)
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_CREATED = "created";
    public static final String COLUMN_PUBLISHED = "published";
    public static final String COLUMN_COMMENT_COUNT = "comment_count";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_SOURCE = "source";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_BODY = "body";

    public static final String DATABASE_NEWS_CREATE = " create table " +
            TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TYPE + " text, "
            + COLUMN_CREATED + " text not null, "
            + COLUMN_PUBLISHED + " text not null, "
            + COLUMN_COMMENT_COUNT + " text, "
            + COLUMN_USER_ID+ " text not null, "
            + COLUMN_SOURCE + " text not null, "
            + COLUMN_IMAGE + " text not null, "
            + COLUMN_TITLE + " text not null, "
            + COLUMN_BODY + " text not null"
            + "); ";

    public static final String INSERT_BLANK_NEWS= "insert into " +
            TABLE_NAME
            + "("
            + COLUMN_TYPE
            + ","
            + COLUMN_CREATED
            + ","
            + COLUMN_PUBLISHED
            + ","
            + COLUMN_COMMENT_COUNT
            + ","
            + COLUMN_USER_ID
            + ","
            + COLUMN_SOURCE
            + ","
            + COLUMN_IMAGE
            + ","
            + COLUMN_TITLE
            + ","
            + COLUMN_BODY
            + ")"
            + " values "
            + "('','','','','','','','','');";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_NEWS_CREATE);
        db.execSQL(INSERT_BLANK_NEWS);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(),
                "Upgrading database from version " + oldVersion +
                        " to " + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public static void insert(SQLiteDatabase db) {

    }

}
