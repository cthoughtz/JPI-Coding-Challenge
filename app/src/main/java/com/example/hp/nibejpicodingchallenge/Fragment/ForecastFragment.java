package com.example.hp.nibejpicodingchallenge.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hp.nibejpicodingchallenge.R;


public class ForecastFragment extends Fragment {
 String lat;
 String lon;
 String coords;

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ForecastFragmentView = inflater.inflate(R.layout.fragment_forecast, container, false);

        lat = getArguments().getString("lat");
        lon = getArguments().getString("lon");

        coords = lat+","+lon;;

        return ForecastFragmentView;
    }

}
