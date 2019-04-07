package com.anto.restaurants.RestaurantsP.Repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface ShopsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ShopsData shopsData);

    @Query("Delete from shops_data")
    void deleteAll();

    @Query("Select * from shops_data")
    List<ShopsData> getShopsData();


    @Query("Select * from shops_data where shopName like :search")
    List<ShopsData> getShopsDataSearch(String search);

}
