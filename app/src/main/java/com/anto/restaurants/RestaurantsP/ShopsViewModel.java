package com.anto.restaurants.RestaurantsP;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.anto.restaurants.RestaurantsP.Repository.ShopsData;

public class ShopsViewModel extends ViewModel {


    public LiveData<String> networkState;
    private ShopsDataSourceFactory itemDataSourceFactory;

    ShopsModel shopsModel;

    public LiveData<PagedList<ShopsData>> articleLiveData;

    LiveData<ShopsDataSource> liveDataSource;

    public ShopsViewModel(ShopsModel shopsModel) {
        this.shopsModel = shopsModel;
        init();
    }

    public LiveData<String> getNetworkState() {
        return networkState;
    }

    public LiveData<PagedList<ShopsData>> getArticleLiveData() {
        return articleLiveData;
    }

    private void init() {

        itemDataSourceFactory = new ShopsDataSourceFactory(shopsModel);

        liveDataSource = ShopsDataSourceFactory.getMutableLiveData();
        networkState = Transformations.switchMap(ShopsDataSourceFactory.getMutableLiveData(), ShopsDataSource::getNetworkState);


        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ShopsDataSource.PAGE_SIZE)
                        .setInitialLoadSizeHint(ShopsDataSource.LOAD_COUNT)
                        .build();

        articleLiveData = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();

    }

    public void invalidate() {
        ShopsDataSourceFactory.getMutableLiveData().getValue().invalidate();

    }

    public void setNew(ShopsModel shopsModel) {

        this.shopsModel = shopsModel;
        itemDataSourceFactory.setNew(shopsModel);
        ShopsDataSourceFactory.getMutableLiveData().getValue().invalidate();
        System.out.println("In View Model");

    }


}
