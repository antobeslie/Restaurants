package com.anto.restaurants.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anto.restaurants.R;
import com.anto.restaurants.RestaurantsP.ShopsActivity;
import com.anto.restaurants.support.CommonUtils;
import com.anto.restaurants.support.GpsUtils;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.List;
import java.util.Objects;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class HomeActivity extends AppCompatActivity {


    private static final String TAG = HomeActivity.class.getSimpleName();
    TextInputEditText searchText;
    ImageView editRemove;

    ProgressBar searchProgress;
    private static final int REQUEST_LOCATION_PERMISSION_CODE = 99;

    private PlacesViewModel mPlacesViewModel;
    PlacesListAdapter adapter;
    boolean isGPS;
    private FusedLocationProviderClient fusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fromXml();


        new GpsUtils(HomeActivity.this).turnGPSOn(isGPSEnable -> isGPS = isGPSEnable);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mPlacesViewModel = ViewModelProviders.of(this).get(PlacesViewModel.class);


        mPlacesViewModel.getAllPlaces().observe(this, words -> {
            adapter.setPlaces(words);
        });

        places();


    }

    void fromXml() {

        searchText = findViewById(R.id.text_edit);
        editRemove = findViewById(R.id.editCancel);
        searchProgress = findViewById(R.id.editProgress);


        editRemove.setOnClickListener(v -> {

            Objects.requireNonNull(searchText.getText()).clear();

        });


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new PlacesListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.HORIZONTAL));


    }


    void places() {

        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
        }

        PlacesClient placesClient = Places.createClient(this);


        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    showSearchProgress(s.toString());

                    mPlacesViewModel.deleteAll();

                    FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                            .setCountry("in")
                            .setTypeFilter(TypeFilter.REGIONS)
                            .setSessionToken(token)
                            .setQuery(s.toString())
                            .build();
                    placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {

                        for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {


                            PlacesData sss = new PlacesData(prediction.getPrimaryText(null).toString(), prediction.getPlaceId());
                            mPlacesViewModel.insert(sss);


                        }


                        hideSearchProgress(s.toString());
                    }).addOnFailureListener((exception) -> {
                        if (exception instanceof ApiException) {
                            ApiException apiException = (ApiException) exception;
                            Log.e("TAG", "Place not found: " + apiException.getStatusCode());
                        }

                        hideSearchProgress(s.toString());
                    });
                } catch (Exception e) {
                    Toast.makeText(HomeActivity.this, "THere may be some problem loading places", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    void showSearchProgress(String s) {

        if (s.length() > 0)
            editRemove.setVisibility(View.VISIBLE);
        else
            editRemove.setVisibility(View.INVISIBLE);
        searchProgress.setVisibility(View.VISIBLE);

    }

    void hideSearchProgress(String s) {


        searchProgress.setVisibility(View.INVISIBLE);
        if (s.length() > 0) {
            editRemove.setVisibility(View.VISIBLE);
        } else
            editRemove.setVisibility(View.INVISIBLE);


    }

    public void locateMe(View v) {

        if (isGPS) {
            if (ActivityCompat.checkSelfPermission(HomeActivity.this, ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(HomeActivity.this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(HomeActivity.this,
                        new String[]{ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        REQUEST_LOCATION_PERMISSION_CODE);
            } else {
                getLocation();
            }

        } else {

            Toast.makeText(HomeActivity.this, "Please enable location", Toast.LENGTH_SHORT).show();
        }


    }


    @SuppressLint("MissingPermission")
    void getLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {

                    if (location != null) {
                        Intent intent = new Intent(HomeActivity.this, ShopsActivity.class);
                        intent.putExtra("lat", location.getLatitude());
                        intent.putExtra("lng", location.getLongitude());
                        startActivity(intent);
                    } else {
                        Toast.makeText(HomeActivity.this, "Couldn't fetch location", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            Toast.makeText(HomeActivity.this, "This sample requires Location access", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CommonUtils.GPS_REQUEST) {
                isGPS = true;
            }
        }
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();

        this.finishAffinity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            mPlacesViewModel.deleteAll();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
