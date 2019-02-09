package com.example.hp.nibejpicodingchallenge.retro;

import com.example.hp.nibejpicodingchallenge.model.Currently;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    // example
    //GET https://api.darksky.net/forecast/0123456789abcdef9876543210fedcba/42.3601,-71.0589,255657600?exclude=currently,flags


    @GET("/forest/260c8b8122a756a92526154ad359a42e/{locationCoords}")
    Call<Currently> getCurrentForecast(@Path("locationCoords") String loc);
}
