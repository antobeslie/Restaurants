package com.anto.restaurants.RestaurantsP.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResId {
    @SerializedName("res_id")
    @Expose
    private Integer resId;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "ResId{" +
                "resId=" + resId +
                '}';
    }
}
