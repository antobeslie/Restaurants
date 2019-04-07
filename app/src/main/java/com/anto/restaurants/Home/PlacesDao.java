package com.anto.restaurants.Home;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PlacesDao {

    @Insert
    void insert(PlacesData placesData);

    @Query("Delete from places_data")
    void deleteAll();

    @Query("Select * from places_data")
    LiveData<List<PlacesData>> getAllPlaces();

}
