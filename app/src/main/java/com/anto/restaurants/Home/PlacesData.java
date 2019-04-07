package com.anto.restaurants.Home;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "places_data")
public class PlacesData {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    int id;
    @NonNull
    @ColumnInfo(name = "areaName")
    String areaName;
    @NonNull
    @ColumnInfo(name = "placeId")
    String placeId;

    public PlacesData(String areaName, String placeId) {
        this.areaName = areaName;
        this.placeId = placeId;
    }


}
