package com.anto.restaurants.RestaurantsP;

import android.app.Application;

public class ShopsModel {
    private Application mApplication;
    private double lat, lng;
    private String sort, search;
    private int searchFlag;

    public ShopsModel(Application mApplication, double lat, double lng, String sort, String search, int searchFlag) {
        this.mApplication = mApplication;
        this.lat = lat;
        this.lng = lng;
        this.sort = sort;
        this.search = search;
        this.searchFlag = searchFlag;
    }

    public Application getmApplication() {
        return mApplication;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getSort() {
        return sort;
    }

    public String getSearch() {
        return search;
    }

    public int getSearchFlag() {
        return searchFlag;
    }
}
