package com.example.beataturczuk.fragments.Activities;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.TextView;


import com.example.beataturczuk.fragments.Fragments.JsonFragment;
import com.example.beataturczuk.fragments.Fragments.MyListFragment;
import com.example.beataturczuk.fragments.Fragments.NewsFragment;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.R;

/**
 * Created by beataturczuk on 21.04.15.
 */
public class ScreenSlidePage extends FragmentActivity {

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private MyListFragment myListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        getNewsFromWS();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private void getNewsFromWS() {
        Log.d("getNewsFromWS","inside");
        NewsAsyncTask newsAsyncTask = new NewsAsyncTask(this, getApplicationContext(), null);
        newsAsyncTask.execute(CommandData.NEWS_URL_ADDRESS);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position==1){
                return new JsonFragment();
            } else {
                return new MyListFragment();

            }
        }

        @Override
        public int getCount() {
            return CommandData.NUM_PAGES;
        }
    }

}
