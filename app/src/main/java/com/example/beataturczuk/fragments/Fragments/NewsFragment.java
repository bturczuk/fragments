package com.example.beataturczuk.fragments.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beataturczuk.fragments.Activities.HttpAsyncTask;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.R;

/**
 * Created by beataturczuk on 22.04.15.
 */
public class NewsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Activity mActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.news, container, false);

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask(mActivity, getActivity(), (TextView) rootView.findViewById(R.id.nius));

        httpAsyncTask.execute(CommandData.NEWS_URL_ADDRESS);

        return rootView;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
