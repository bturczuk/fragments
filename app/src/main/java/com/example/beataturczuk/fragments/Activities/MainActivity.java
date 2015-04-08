package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.example.beataturczuk.fragments.Fragments.JsonFragment;
import com.example.beataturczuk.fragments.Helpers.DBHelper;
import com.example.beataturczuk.fragments.R;


public class MainActivity extends Activity {

    private Activity activity;
    private DBHelper mydb;

    public MainActivity() {}

    public MainActivity (Activity activity) {
        this.activity = activity;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);
        mydb.getWritableDatabase();

        JsonFragment jsonFragment = new JsonFragment();


        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, jsonFragment);

        fragmentTransaction.commit();

        // call AsynTask to perform network operation on separate thread

    }
}
