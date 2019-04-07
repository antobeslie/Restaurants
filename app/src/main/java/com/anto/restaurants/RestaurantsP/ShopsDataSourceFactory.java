package com.anto.restaurants.RestaurantsP;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class ShopsDataSourceFactory extends DataSource.Factory {
    private static MutableLiveData<ShopsDataSource> mutableLiveData;
    ShopsModel shopsModel;


    public ShopsDataSourceFactory(ShopsModel shopsModel) {

        this.shopsModel = shopsModel;


        mutableLiveData = new MutableLiveData<ShopsDataSource>();


    }

    @Override
    public DataSource create() {


        ShopsDataSource shopsDataSource = new ShopsDataSource(shopsModel);

        mutableLiveData.postValue(shopsDataSource);
        return shopsDataSource;

    }


    public static MutableLiveData<ShopsDataSource> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setNew(ShopsModel shopsModel) {
        this.shopsModel = shopsModel;


    }

}
