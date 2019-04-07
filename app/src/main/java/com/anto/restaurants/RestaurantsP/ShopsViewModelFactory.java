package com.anto.restaurants.RestaurantsP;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class ShopsViewModelFactory implements ViewModelProvider.Factory {
    ShopsModel shopsModel;
    public ShopsViewModelFactory(ShopsModel shopsModel) {
        this.shopsModel = shopsModel;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ShopsViewModel(shopsModel);
    }


}