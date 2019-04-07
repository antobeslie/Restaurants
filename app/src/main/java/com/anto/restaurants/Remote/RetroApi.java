package com.anto.restaurants.Remote;

import com.anto.restaurants.RestaurantsP.Models.SearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface RetroApi {

    @Headers("user-key: c################4")
    @GET("v2.1/search")
    Call<SearchResponse> getRestaurants(@Query("lat") double lat, @Query("lon") double lng, @Query("start") int start, @Query("count") int count, @Query("sort") String sort, @Query("q") String search);


}