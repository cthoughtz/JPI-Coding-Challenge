package com.example.hp.nibejpicodingchallenge;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.nibejpicodingchallenge.Fragment.CoordinatesFragment;
import com.example.hp.nibejpicodingchallenge.Fragment.ForecastFragment;

public class MainActivity extends AppCompatActivity {

    CoordinatesFragment coordinatesFragment = new CoordinatesFragment();
    ForecastFragment forecastFragment = new ForecastFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, coordinatesFragment);
        fragmentTransaction.commit();

    }
}
