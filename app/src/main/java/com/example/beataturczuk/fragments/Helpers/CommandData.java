package com.example.beataturczuk.fragments.Helpers;

import android.app.Activity;
import android.content.Context;

/**
 * Created by beataturczuk on 26.03.15.
 */
public final class CommandData {


    private static Activity mActivity;
    private static Context mContext;


    private CommandData(Activity activity, Context context) {
        this.mActivity = activity;
        this.mContext = context;
    }
    //only url adresses and simple functions
    public static final String URL_ADDRESS = "http://lfc.pl/app/webroot/get_quote.php";


    public static void networkConnection() {
        if (!com.example.beataturczuk.fragments.Internet.NetworkConnection.networkStatus(mContext)) {
            com.example.beataturczuk.fragments.Internet.NetworkConnection.noNetworkDialog(mActivity);
            //this.cancel(true);
        } else {
            return;
        }
    }
}
