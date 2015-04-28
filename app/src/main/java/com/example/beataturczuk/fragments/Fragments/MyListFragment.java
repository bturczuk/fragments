package com.example.beataturczuk.fragments.Fragments;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.beataturczuk.fragments.Activities.GetNewsAsyncTask;
import com.example.beataturczuk.fragments.Activities.HttpAsyncTask;
import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbObjects.News;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableQuote;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.Helpers.NewsAdapter;
import com.example.beataturczuk.fragments.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by beataturczuk on 24.04.15.
 */
public class MyListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Activity mActivity;
    private DbManage dbManage;
    SQLiteDatabase sqLiteDatabase;
    private String title;
    private List<News> news;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.listview, container, false);

        return rootView;

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NewsAdapter adapter = new NewsAdapter(getActivity(), setListValues(), null);

        setListAdapter(adapter);
    }

    private ArrayList<HashMap<String, String>> setListValues() {
        DbManage dbManage = new DbManage(getActivity());
        dbManage.open();
        news = dbManage.getNews();
        List<News> news = dbManage.getNews();
        dbManage.close();

        ArrayList<HashMap<String, String>> items = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < news.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();

            map.put(TableNews.COLUMN_TITLE, news.get(i).getTitle());
            map.put(TableNews.COLUMN_BODY, news.get(i).getBody());
            map.put(TableNews.COLUMN_ID, news.get(i).getId());

            items.add(map);
        }
        return items;
    }

}
