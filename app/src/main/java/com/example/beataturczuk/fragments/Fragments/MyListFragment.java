package com.example.beataturczuk.fragments.Fragments;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbObjects.News;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.Helpers.NewsAdapter;
import com.example.beataturczuk.fragments.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by beataturczuk on 24.04.15.
 */
public class MyListFragment extends ListFragment {


    private Activity mActivity;
    private DbManage dbManage;
    SQLiteDatabase sqLiteDatabase;
    private List<News> all_news;


    private ArrayList<HashMap<String, String>> setListValues() {
        DbManage dbManage = new DbManage(getActivity());
        dbManage.open();
        //news = dbManage.getNews();
        List<News> all_news = dbManage.getNews();

        ArrayList<HashMap<String, String>> items = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < all_news.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();

            map.put(TableNews.COLUMN_TITLE, all_news.get(i).getTitle());
            map.put(TableNews.COLUMN_BODY, all_news.get(i).getBody());
            map.put(TableNews.COLUMN_ID, all_news.get(i).getId());

            items.add(map);
            Log.d("dodane ajtemsy", "" +items);

            Log.d("List item title", ""+ all_news.get(i).getTitle());
            Log.d("List item body", ""+ all_news.get(i).getBody());
            Log.d("List item id", ""+ all_news.get(i).getId());


            Log.d("List length", ""+ all_news.size());
            dbManage.close();

        }
        return items;

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.listview, container, false);

        return rootView;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NewsAdapter adapter = new NewsAdapter(getActivity(), setListValues());

        setListAdapter(adapter);
    }
}
