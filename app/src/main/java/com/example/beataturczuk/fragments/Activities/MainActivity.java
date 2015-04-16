package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;


import com.example.beataturczuk.fragments.DataBase.DbHelper;
import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.Fragments.JsonFragment;
import com.example.beataturczuk.fragments.R;

import java.sql.SQLException;


public class MainActivity extends Activity {

    private Activity mActivity;
    private DbHelper mMydb;
    private Context mContext;
   private DbManage mDbManage;
    public MainActivity() { }

    public MainActivity(Activity activity, Context context) {
        this.mActivity = activity;
        this.mContext = context;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);


        mMydb = new DbHelper(mContext);
        mDbManage = new DbManage(mContext);
        mMydb.getWritableDatabase();
        try {
            mDbManage.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        JsonFragment jsonFragment = new JsonFragment();


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, jsonFragment);

        fragmentTransaction.commit();
    }
}
