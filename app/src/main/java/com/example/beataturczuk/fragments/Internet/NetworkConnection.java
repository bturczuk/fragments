package com.example.beataturczuk.fragments.Internet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.beataturczuk.fragments.Helpers.ApplicationConstants;
import com.example.beataturczuk.fragments.R;

/**
 * Created by beataturczuk on 26.03.15.
 */


public final class NetworkConnection {

    private NetworkConnection() {

            }

    public static boolean networkStatus(Context context) {

            return (NetworkConnection.isWifiAvailable(context)
            || NetworkConnection.isMobileNetworkAvailable(context));
    }

    public static boolean isMobileNetworkAvailable(Context con) {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    con.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo myNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (myNetworkInfo.isConnected()) {
                  return true;
            } else {
                return false;
            }
    }

    public static boolean isWifiAvailable(Context con) {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                con.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo myNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (myNetworkInfo.isConnected()) {
                 return true;
           } else {
                return false;
           }
    }

    public static void noNetworkDialog(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(R.string.no_internet_message)
                .setCancelable(false)
                .setPositiveButton(R.string.exit_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        activity.finish();
                    }
                })
                .setNegativeButton(R.string.refresh_button, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = activity.getIntent();
                        activity.finish();
                        activity.startActivity(intent);
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
