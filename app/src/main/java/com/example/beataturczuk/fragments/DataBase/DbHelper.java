package com.example.beataturczuk.fragments.DataBase;

//import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.Helpers.CommandData;


/**
 * Created by beataturczuk on 26.03.15.
 */
public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, CommandData.DB_NAME, null, CommandData.DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        TableQuote.onCreate(db);
        TableNews.onCreate(db);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        TableQuote.onUpgrade(db, oldVersion, newVersion);
        TableNews.onUpgrade(db, oldVersion, newVersion);
    }

    public void cleanDatabase(SQLiteDatabase db) {
        TableQuote.onUpgrade(db, db.getVersion(), db.getVersion());
        TableNews.onUpgrade(db, db.getVersion(), db.getVersion());
    }
}
