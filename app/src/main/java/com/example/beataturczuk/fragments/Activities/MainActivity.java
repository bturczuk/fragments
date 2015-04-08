package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.beataturczuk.fragments.Fragments.JsonFragment;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Helpers.DBHelper;
import com.example.beataturczuk.fragments.R;


public class MainActivity extends Activity {

    //private TextView etResponse;
    private DBHelper mydb;



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

        //new JsonFragment().getActivity();

    }
}
