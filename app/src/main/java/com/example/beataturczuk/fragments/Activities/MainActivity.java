package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.example.beataturczuk.fragments.Fragments.JsonFragment;
import com.example.beataturczuk.fragments.Helpers.DBHelper;
import com.example.beataturczuk.fragments.R;


public class MainActivity extends Activity {

    private Activity mActivity;
    private DBHelper mMydb;

    public MainActivity() { }

    public MainActivity(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

        mMydb = new DBHelper(this);
        mMydb.getWritableDatabase();

        JsonFragment jsonFragment = new JsonFragment();


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, jsonFragment);

        fragmentTransaction.commit();
    }
}
