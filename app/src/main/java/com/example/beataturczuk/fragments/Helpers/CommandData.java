package com.example.beataturczuk.fragments.Helpers;

import android.app.Activity;
import android.content.Context;

/**
 * Created by beataturczuk on 26.03.15.
 */
public final class CommandData {

    //Database Strings:
    public static final String DB_NAME = "MyDBName.db";
    public static final int DATABASE_VERSION = 4;

    //JSON Parser settings:
    public static final int TIMEOUT = 300;
    public static final int NUMOFDIGITS = 8;

    //only url adresses and simple functions
    public static final String URL_ADDRESS = "http://lfc.pl/app/webroot/get_quote.php";
    public static final String NEWS_URL_ADDRESS = "http://www.lfc.pl/app/webroot/get_posts.php?call=1&first=0";

    //viewPager Strings;
    public static final int NUM_PAGES = 5;


    public static void networkConnection(Context mContext, Activity mActivity) {
        if (!com.example.beataturczuk.fragments.Internet.NetworkConnection.networkStatus(mContext)) {
            com.example.beataturczuk.fragments.Internet.NetworkConnection.noNetworkDialog(mActivity);
            //this.cancel(true);
        } else {
            return;
        }
    }
}
