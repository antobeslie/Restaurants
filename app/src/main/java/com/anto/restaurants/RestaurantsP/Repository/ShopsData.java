package com.anto.restaurants.RestaurantsP.Repository;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

@Entity(tableName = "shops_data")
public class ShopsData {


    @PrimaryKey
    @ColumnInfo(name = "Resid")
    int id;

    public String shopName;

    public String shopAddress;

    public double shopRating;

    public String shopCuisines;

    public int shopCost;

    public String shopImage;

    public ShopsData(int id, String shopName, String shopAddress, double shopRating, String shopCuisines, int shopCost, String shopImage) {
        this.id = id;
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopRating = shopRating;
        this.shopCuisines = shopCuisines;
        this.shopCost = shopCost;
        this.shopImage = shopImage;
    }

    public static DiffUtil.ItemCallback<ShopsData> DIFF_CALLBACK = new DiffUtil.ItemCallback<ShopsData>() {
        @Override
        public boolean areItemsTheSame(@NonNull ShopsData shopsData, @NonNull ShopsData t1) {
            return shopsData.id == t1.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ShopsData shopsData, @NonNull ShopsData t1) {
            return shopsData.equals(t1);
        }
    };


    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        ShopsData article = (ShopsData) obj;
        return article.id == this.id;
    }

    @Override
    public String toString() {
        return "ShopsData{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopRating=" + shopRating +
                ", shopCuisines='" + shopCuisines + '\'' +
                ", shopCost=" + shopCost +
                ", shopImage='" + shopImage + '\'' +
                '}';
    }
}
