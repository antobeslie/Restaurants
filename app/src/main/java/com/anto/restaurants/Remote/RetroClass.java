package com.anto.restaurants.Remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {

    private static final String BASE_URL = "https://developers.zomato.com/api/";


    private static Retrofit getRetroInstance() {

        Gson gson = new GsonBuilder().setLenient().create();


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }


    public static RetroApi getApiService() {

        return getRetroInstance().create(RetroApi.class);


    }


}
