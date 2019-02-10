package com.example.hp.nibejpicodingchallenge.retro;



import com.example.hp.nibejpicodingchallenge.forecastmodel.TestStuff;
import com.example.hp.nibejpicodingchallenge.model.Example;
import com.example.hp.nibejpicodingchallenge.model.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    // example
    //GET https://api.darksky.net/forecast/0123456789abcdef9876543210fedcba/42.3601,-71.0589,255657600?exclude=currently,flags

    @GET("weather")
    Call<Example> getCurrentForecast(@Query("lat") String lat, @Query("lon") String lon,@Query("appid") String key);

    @GET("forecast")
    Call<TestStuff> getHourlyForecast(@Query("lat") String lat, @Query("lon") String lon, @Query("appid") String key);
}

