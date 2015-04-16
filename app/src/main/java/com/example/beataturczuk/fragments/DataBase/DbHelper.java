package com.example.beataturczuk.fragments.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;


/**
 * Created by beataturczuk on 26.03.15.
 */
public class DbHelper extends SQLiteOpenHelper {



    private Context context;
    private TableQuote tableQuote = new TableQuote(this.context);

    public DbHelper(Context context) {
        super(context, ApplicationConstants.ApiQuoteKeys.DB_NAME, null, ApplicationConstants.ApiQuoteKeys.DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate(SQLiteDatabase db) {
        tableQuote.onCreate(db);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            tableQuote.onUpgrade(db, oldVersion, newVersion);
    }

    public void cleanDatabase(SQLiteDatabase db) {
        tableQuote.onUpgrade(db, db.getVersion(), db.getVersion());
    }

    public void insertData(String body, String author) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ApplicationConstants.ApiQuoteKeys.PRODUCT_MESSAGE, body);
        values.put(ApplicationConstants.ApiQuoteKeys.PRODUCT_AUTHOR, author);
        sqLiteDatabase.insert("products", null, values);
    }
}
