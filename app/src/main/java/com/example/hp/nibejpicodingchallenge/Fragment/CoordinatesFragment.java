package com.example.hp.nibejpicodingchallenge.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.nibejpicodingchallenge.R;


public class CoordinatesFragment extends Fragment {

    Button fragButton;
    EditText lat;
    EditText lon;

    ForecastFragment forecastFragment = new ForecastFragment();


    public CoordinatesFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_coordinates, container, false);

        fragButton = fragmentView.findViewById(R.id.forecastButton);
        lat = fragmentView.findViewById(R.id.latitudeId);
        lon = fragmentView.findViewById(R.id.longitudeId);



        fragButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Test", "onCreateView: "+lat.getText().toString());

                Bundle bundle = new Bundle();
                bundle.putString("lat",lat.getText().toString());
                bundle.putString("lon",lon.getText().toString());

                forecastFragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, forecastFragment);
                fragmentTransaction.commit();

            }
        });

        return fragmentView;
    }
}
