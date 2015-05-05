package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beataturczuk.fragments.DataBase.dbObjects.News;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.Fragments.MyListFragment;
import com.example.beataturczuk.fragments.R;

/**
 * Created by beataturczuk on 05.05.15.
 */
public class OneNews extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_news);


        TextView mTitle = (TextView) findViewById(R.id.news_title);
        //TextView mBody = (TextView) findViewById(R.id.news_body);

        String mId = getIntent().getStringExtra(MyListFragment.KEY_NEWS_ID);

        Toast.makeText(this, mId, Toast.LENGTH_SHORT).toString();
        //Toast.makeText(this, title, Toast.LENGTH_SHORT).toString();
       mTitle.setText(getIntent().getStringExtra(MyListFragment.KEY_NEWS_ID));
       //mBody.setText(getIntent().getStringExtra(MyListFragment.TITLE_NEWS));
    }
}
