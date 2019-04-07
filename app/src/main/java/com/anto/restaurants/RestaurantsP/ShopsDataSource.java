package com.anto.restaurants.RestaurantsP;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;

import com.anto.restaurants.RestaurantsP.Models.Restaurant;
import com.anto.restaurants.RestaurantsP.Models.Restaurant_;
import com.anto.restaurants.RestaurantsP.Models.SearchResponse;
import com.anto.restaurants.Remote.RetroApi;
import com.anto.restaurants.Remote.RetroClass;
import com.anto.restaurants.RestaurantsP.Repository.ShopsData;
import com.anto.restaurants.RestaurantsP.Repository.ShopsRepository;
import com.anto.restaurants.support.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopsDataSource extends PositionalDataSource<ShopsData> {

    private double lat, lng;
    private String sort, search;
    private int searchFlag;

    Application mApplication;
    public static int PAGE_SIZE = 10;
    public static int LOAD_COUNT = 10;

    private MutableLiveData<String> networkState;
    ShopsRepository shopsRepository;


    ShopsDataSource(ShopsModel shopsModel) {

        mApplication = shopsModel.getmApplication();
        this.lat = shopsModel.getLat();
        this.lng = shopsModel.getLng();
        this.sort = shopsModel.getSort();
        this.search = shopsModel.getSearch();
        this.searchFlag = shopsModel.getSearchFlag();

        shopsRepository = new ShopsRepository(mApplication);

        networkState = new MutableLiveData<>();

    }


    MutableLiveData<String> getNetworkState() {
        return networkState;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<ShopsData> callback) {

        RetroApi retroApi = RetroClass.getApiService();
        int totalCount = LOAD_COUNT;

        int position = computeInitialLoadPosition(params, totalCount);
        int loadSize = computeInitialLoadSize(params, position, totalCount);


        if (searchFlag > 0) {
            networkState.postValue(CommonUtils.LOADING_SEARCH);


            List<ShopsData> localData;
            String se = "%" + search + "%";
            localData = shopsRepository.getShopsSearch(se);

            if (localData.size() > 0 && search.length() > 0) {


                callback.onResult(localData, position, localData.size());
                networkState.postValue(CommonUtils.LOADED_SEARCH);

                return;
            }

        } else
            networkState.postValue(CommonUtils.LOADING);


        Call<SearchResponse> call = retroApi.getRestaurants(lat, lng, position, loadSize, sort, search);


        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {


                if (response.isSuccessful()) {
                    int statcode = response.code();
                    System.out.println(" Stat Code : " + statcode);


                    List<Restaurant> restaurants = response.body().getRestaurants();


                    List<ShopsData> shops = new ArrayList<>();

                    for (Restaurant restaurant :
                            restaurants) {


                        ShopsData shopsData = new ShopsData(restaurant.getRestaurant().getR().getResId(),
                                restaurant.getRestaurant().getName(),
                                restaurant.getRestaurant().getLocation().getLocality(),
                                restaurant.getRestaurant().getUserRating().getAggregateRating(),
                                restaurant.getRestaurant().getCuisines(),
                                restaurant.getRestaurant().getAverageCostForTwo(),
                                restaurant.getRestaurant().getFeaturedImage()
                        );
                        shopsRepository.insert(shopsData);
                        shops.add(shopsData);

                    }


                    int resCount = 0;
                    if (response.body().getResultsFound() != null)
                        resCount = response.body().getResultsFound();


                    callback.onResult(shops, position, resCount);

                    if (searchFlag > 0)
                        networkState.postValue(CommonUtils.LOADED_SEARCH);
                    else
                        networkState.postValue(CommonUtils.LOADED);

                } else {
                    networkState.postValue(CommonUtils.FAILED);
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                ;
                System.out.println(" Stat Error : " + t.getLocalizedMessage());
                System.out.println(" Stat Error 2 : " + t.toString());
                networkState.postValue(CommonUtils.FAILED);

            }
        });


    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<ShopsData> callback) {


        if (searchFlag > 0) {
            networkState.postValue(CommonUtils.LOADING_SEARCH);
        } else
            networkState.postValue(CommonUtils.LOADING_MORE);


        RetroApi retroApi = RetroClass.getApiService();


        Call<SearchResponse> call = retroApi.getRestaurants(lat, lng, params.startPosition, params.loadSize, sort, search);


        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {


                if (response.isSuccessful()) {
                    int statcode = response.code();
                    System.out.println(" Stat Code : " + statcode);

                    List<Restaurant> restaurants = response.body().getRestaurants();


                    List<ShopsData> shops = new ArrayList<>();
                    for (Restaurant restaurant :
                            restaurants) {

                        ShopsData shopsData = new ShopsData(restaurant.getRestaurant().getR().getResId(),
                                restaurant.getRestaurant().getName(),
                                restaurant.getRestaurant().getLocation().getLocality(),
                                restaurant.getRestaurant().getUserRating().getAggregateRating(),
                                restaurant.getRestaurant().getCuisines(),
                                restaurant.getRestaurant().getAverageCostForTwo(),
                                restaurant.getRestaurant().getFeaturedImage()
                        );
                        shopsRepository.insert(shopsData);
                        shops.add(shopsData);

                    }

                    callback.onResult(shops);

                    if (searchFlag > 0)
                        networkState.postValue(CommonUtils.LOADED_SEARCH);
                    else
                        networkState.postValue(CommonUtils.LOADED_MORE);
                } else {
                    networkState.postValue(CommonUtils.FAILED);
                }

            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                System.out.println(" Stat Error : " + t.getLocalizedMessage());
                System.out.println(" Stat Error 2 : " + t.toString());
                networkState.postValue(CommonUtils.FAILED);
            }
        });


    }
}
