package com.anto.restaurants.support;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.anto.restaurants.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommonUtils {

    public static final int GPS_REQUEST = 99;
    public static String LOADING = "LOADING";
    public static String LOADED = "LOADED";

    public static String LOADING_MORE = "LOADING_MORE";
    public static String LOADED_MORE = "LOADED_MORE";


    public static String LOADING_SEARCH = "LOADING_SEARCH";
    public static String LOADED_SEARCH = "LOADED_SEARCH";

    public static String FAILED = "FAILED";
    private Context context;

    public CommonUtils(Context context) {

        this.context = context;
    }

    public int getRateColor(double rate) {


        if (rate >= 4)
            return context.getResources().getColor(R.color.colorPrimary);
        else if (rate >= 3)
            return context.getResources().getColor(R.color.green);
        else if (rate >= 2)
            return context.getResources().getColor(R.color.light_green);
        else if (rate >= 1)
            return context.getResources().getColor(R.color.yellow_color);
        else
            return context.getResources().getColor(R.color.dark_red_color);

    }


    public String getAddress(double lat, double lng) {

        Geocoder geocoder;
        List<Address> addresses = new ArrayList<>();
        geocoder = new Geocoder(context, Locale.getDefault());

        String address = "";

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            if (addresses.size() > 0) {

                if (addresses.get(0).getSubLocality() != null)
                    address += addresses.get(0).getSubLocality() + ", " + addresses.get(0).getLocality();
                else
                    address += addresses.get(0).getLocality();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }


}
