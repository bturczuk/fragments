package com.example.beataturczuk.fragments.DataBase.dbTables;

import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by beataturczuk on 10.04.15.
 */
public class TableQuote extends SQLiteOpenHelper {

    private Context mContext;

    public TableQuote(Context context) {
        super(context, ApplicationConstants.ApiQuoteKeys.DB_NAME, null, ApplicationConstants.ApiQuoteKeys.DATABASE_VERSION);
        this.mContext = context;
    }

    public static final String DATABASE_CREATE = "create table " +
            ApplicationConstants.ApiQuoteKeys.TABLE_NAME + "(" + ApplicationConstants.ApiQuoteKeys.PRODUCT_COLUMN_ID
            + " integer primary key autoincrement, "
            + ApplicationConstants.ApiQuoteKeys.PRODUCT_MESSAGE + " text not null, "
            + ApplicationConstants.ApiQuoteKeys.PRODUCT_AUTHOR + " text not null); ";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DbHelper.class.getName(),
                "Upgrading database from version " + oldVersion +
                        " to " + newVersion + ", which will destroy all old data"
        );
        db.execSQL("DROP TABLE IF EXIST " + ApplicationConstants.ApiQuoteKeys.TABLE_NAME);
        onCreate(db);
    }


}
