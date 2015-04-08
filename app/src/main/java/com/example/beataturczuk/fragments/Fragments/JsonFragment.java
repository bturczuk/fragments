package com.example.beataturczuk.fragments.Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.beataturczuk.fragments.Activities.HttpAsyncTask;
import com.example.beataturczuk.fragments.Helpers.CommandData;
import com.example.beataturczuk.fragments.R;


/**
 * Created by beataturczuk on 26.03.15.
 */
public class JsonFragment extends Fragment {

    private Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View rootView = inflater.inflate(R.layout.fragment, container, false);

        HttpAsyncTask httpAsyncTask = new HttpAsyncTask(activity, getActivity(), (TextView) rootView.findViewById(R.id.etResponse));

        httpAsyncTask.execute(CommandData.URL_ADDRESS);



        return rootView;
    }

}


