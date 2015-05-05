package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbObjects.Quote;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Fragments.JsonFragment;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.R;


public class MainActivity extends Activity {

    private DbHelper mMydb;
    private DbManage mDbManage;
    ScreenSlidePage slidePagerActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);


        mMydb = new DbHelper(getApplicationContext());
        mDbManage = new DbManage(getApplicationContext());
        mDbManage.open();
        mDbManage.close();
    }
}
