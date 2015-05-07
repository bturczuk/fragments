package com.example.beataturczuk.fragments.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beataturczuk.fragments.DataBase.DbManage;
import com.example.beataturczuk.fragments.DataBase.dbObjects.News;
import com.example.beataturczuk.fragments.DataBase.dbTables.TableNews;
import com.example.beataturczuk.fragments.Fragments.MyListFragment;
import com.example.beataturczuk.fragments.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by beataturczuk on 05.05.15.
 */
public class OneNews extends Activity {

    private List<News> one_news;
    private ArrayList<HashMap<String, String>> news = new ArrayList<HashMap<String, String>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_news);


        TextView mTitle = (TextView) findViewById(R.id.news_title);
        TextView mBody = (TextView) findViewById(R.id.news_body);
        TextView mComments = (TextView) findViewById(R.id.news_comments);

        String mId = getIntent().getStringExtra(MyListFragment.KEY_NEWS_ID);
        GetOneNews(mId);

        mTitle.setText(news.get(0).get(TableNews.COLUMN_TITLE));
        mBody.setText(news.get(0).get(TableNews.COLUMN_BODY));
        mComments.setText("Comments: " + news.get(0).get(TableNews.COLUMN_COMMENT_COUNT));

    }

    private ArrayList<HashMap<String, String>> GetOneNews(String ID) {
        DbManage dbManage = new DbManage(this);
        dbManage.open();
        List<News> one_news  = dbManage.getOneNews(ID);

        for (int i = 0; i < one_news.size(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();

            map.put(TableNews.COLUMN_TITLE, one_news.get(i).title);
            map.put(TableNews.COLUMN_COMMENT_COUNT, one_news.get(i).comment_count);
            map.put(TableNews.COLUMN_BODY, one_news.get(i).body);
            map.put(TableNews.COLUMN_ID, one_news.get(i).column_id);

            news.add(map);

            dbManage.close();

        }
        return news;

    }
}
