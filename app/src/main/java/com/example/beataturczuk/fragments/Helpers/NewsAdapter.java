package com.example.beataturczuk.fragments.Helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by beataturczuk on 27.04.15.
 */
public class NewsAdapter extends BaseAdapter {

    private Activity mActivity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;

    public NewsAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        mActivity = a;
        data = d;
        inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        if (getCount() > 0) {
            return false;
        }
        return true;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    public void displayNewData(ArrayList<HashMap<String, String>> new_items) {
        data = new_items;
        notifyDataSetChanged();
        super.notifyDataSetChanged();
    }

    public void refreshList() {
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null) {
           vi = inflater.inflate(R.layout.news_layout, null);
    }

        TextView newsTitle = (TextView) vi.findViewById(R.id.newsTitle);
        TextView newsBody = (TextView) vi.findViewById(R.id.newsBody);

        HashMap<String, String> items = data.get(position);

        newsTitle.setText(items.get(TableNews.COLUMN_TITLE));
        newsBody.setText(items.get(TableNews.COLUMN_BODY));


        return vi;
    }

}