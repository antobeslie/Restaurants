package com.anto.restaurants.Home;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PlacesViewModel extends AndroidViewModel {
    public LiveData<List<PlacesData>> mAllPlaces;
    public PlacesRepository placesRepository;


    public PlacesViewModel(@NonNull Application application) {
        super(application);

        placesRepository = new PlacesRepository(application);
        mAllPlaces = placesRepository.getAllPlaces();

    }

    public LiveData<List<PlacesData>> getAllPlaces() {
        return mAllPlaces;
    }

    public void insert(PlacesData placesData) {

        placesRepository.insert(placesData);

    }

    public void deleteAll() {
        placesRepository.deleteAll();
    }

}
