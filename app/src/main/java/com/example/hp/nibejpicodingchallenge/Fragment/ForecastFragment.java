package com.example.hp.nibejpicodingchallenge.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hp.nibejpicodingchallenge.R;
import com.example.hp.nibejpicodingchallenge.forecastmodel.TestStuff;
import com.example.hp.nibejpicodingchallenge.model.Example;
import com.example.hp.nibejpicodingchallenge.retro.APIClient;
import com.example.hp.nibejpicodingchallenge.retro.Api;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ForecastFragment extends Fragment {
 String lat;
 String lon;
 int requestCode = 9001;
 String apiKey;
 String iconUrl;

 private TextView currentTemperature;
 private TextView currentHumidity;
 private TextView currentWindSpeed;
 private ImageView currentImage;
 private ListView dailyForecast;
 private TextView currentCity;


 String test = "Atlanta";

    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View ForecastFragmentView = inflater.inflate(R.layout.fragment_forecast, container, false);

        lat = getArguments().getString("lat");
        lon = getArguments().getString("lon");
        apiKey = getResources().getString(R.string.apiKey);
        iconUrl =  "http://openweathermap.org/img/w/";


        currentTemperature = ForecastFragmentView.findViewById(R.id.currentTempId);
        currentHumidity = ForecastFragmentView.findViewById(R.id.currentHumidityId);
        currentWindSpeed = ForecastFragmentView.findViewById(R.id.currentWindSpeedId);
        currentImage = ForecastFragmentView.findViewById(R.id.currentWeatherID);
        dailyForecast = ForecastFragmentView.findViewById(R.id.listViewDaily);
        currentCity = ForecastFragmentView.findViewById(R.id.currentDateID);



        permissionsRequest();

        retroService();



        return ForecastFragmentView;
    }




    private void retroService() {

        Api api = APIClient.getRetrofitClient().create(Api.class);
        Call<Example>call = api.getCurrentForecast(lat,lon,apiKey);
        Call<TestStuff> callTest = api.getHourlyForecast(lat,lon,apiKey);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                double kelvinToFahrenheit = (response.body().getMain().getTemp()-273.15) *(9/5)+32;

                DecimalFormat decimalFormat = new DecimalFormat("##.##");
                String formatedFahrenheit = decimalFormat.format(kelvinToFahrenheit);

                currentTemperature.setText("Temp: "+formatedFahrenheit+ " \u2109");
                currentHumidity.setText("Humidity: "+response.body().getMain().getHumidity().toString()+" %");
                currentWindSpeed.setText("Wind: " +response.body().getWind().getSpeed().toString()+" m/s");  //  Meters per second instead of miles per hour
                currentCity.setText(response.body().getName());


                String icon = response.body().getWeather().get(0).getIcon();

                Glide.with(ForecastFragment.this).load(iconUrl+icon+".png").into(currentImage);

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });

        callTest.enqueue(new Callback<TestStuff>() {
            @Override
            public void onResponse(Call<TestStuff> call, Response<TestStuff> response) {

               // Log.d("Second Call", "onSuccessResponse "+response.body().getList().toString());
                Log.d("Items", "onResponse-Time: "+response.body().getList().get(1).getDtTxt());
                Log.d("Items", "onResponse:-Image: "+response.body().getList().get(1).getWeather().get(0).getIcon());
                Log.d("Items", "onResponse:-Description: "+response.body().getList().get(1).getWeather().get(0).getDescription());
                Log.d("Items", "onResponse:-Wind-Speed:  "+response.body().getList().get(1).getWind().getSpeed());


                WeatherListAdapter adapter = new WeatherListAdapter(response.body().getList());
                dailyForecast.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<TestStuff> call, Throwable t) {

                Log.d("Failed", "onFailure: "+t.toString());
            }
        });

    }

    class WeatherListAdapter extends BaseAdapter {
        List<com.example.hp.nibejpicodingchallenge.forecastmodel.List> data;

        public WeatherListAdapter(List<com.example.hp.nibejpicodingchallenge.forecastmodel.List> data) {
            this.data = data;
        }


        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {   //todo change layout to list item
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false );
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.weatherIconImageView = convertView.findViewById(R.id.image_view_weather_icon);
                viewHolder.weatherTimeTextView = convertView.findViewById(R.id.text_view_weather_date);
                viewHolder.weatherTempratureTextView = convertView.findViewById(R.id.text_view_weather_temperate);
                viewHolder.weatherWindSpeedTextView = convertView.findViewById(R.id.text_view_weather_wind_speed);
                viewHolder.weatherDescriptionTextView = convertView.findViewById(R.id.text_view_weather_description);
                convertView.setTag(viewHolder);
            }

            com.example.hp.nibejpicodingchallenge.forecastmodel.List item = data.get(position);
            System.out.println(iconUrl+item.getWeather().get(0).getIcon()+".png");
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            Glide.with(ForecastFragment.this).load(
                    iconUrl+item.getWeather().get(0).getIcon()+".png").into(viewHolder.weatherIconImageView);
            viewHolder.weatherTempratureTextView.setText("Temprature: "+item.getClouds().toString());
            viewHolder.weatherDescriptionTextView.setText(item.getWeather().get(0).getDescription());
            viewHolder.weatherWindSpeedTextView.setText("Wind Speed: "+item.getWind().getSpeed());
            viewHolder.weatherTempratureTextView.setText("Time: "+item.getDtTxt());

            return convertView;
        }
    }

    public class ViewHolder {
        ImageView weatherIconImageView;
        TextView weatherTimeTextView;
        TextView weatherWindSpeedTextView;
        TextView weatherTempratureTextView;
        TextView weatherDescriptionTextView;
    }

    private void permissionsRequest() {
        //TODO - Fix the permissions issues. It doesn't ask the to grant access to location.
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);

            return;
        }
    }

}
