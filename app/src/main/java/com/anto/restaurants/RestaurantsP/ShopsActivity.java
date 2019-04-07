package com.anto.restaurants.RestaurantsP;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anto.restaurants.R;
import com.anto.restaurants.RestaurantsP.Repository.ShopsData;
import com.anto.restaurants.support.CommonUtils;
import com.anto.restaurants.support.ProgressD;
import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.PlacesClient;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static android.support.constraint.Constraints.TAG;


public class ShopsActivity extends AppCompatActivity {

    public Double lat, lng;
    String sort = "real_distance";
    RecyclerView recyclerView;
    ProgressD progressD;
    SwipeRefreshLayout refreshLayout;
    Toolbar toolbar;
    TextView titleTxt;
    ImageView imBack;
    TextInputEditText searchText;
    ImageView editRemove;
    ProgressBar searchProgress;
    ShopsViewModel itemViewModel;
    String placeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        fromXml();


        if (getIntent().hasExtra("placeId")) {

            placeId = getIntent().getStringExtra("placeId");
            String areaName = getIntent().getStringExtra("areaName");


            List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ADDRESS);

            FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields)
                    .build();
            PlacesClient placesClient = Places.createClient(this);

            placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
                Place place = response.getPlace();
                Log.i(TAG, "Place found: " + place.getName() + " " + Objects.requireNonNull(place.getLatLng()).latitude + " " + place.getLatLng().longitude + " address " + place.getAddress());
                lat = place.getLatLng().latitude;
                lng = place.getLatLng().longitude;
                titleTxt.setText(Objects.requireNonNull(place.getAddress()).replaceAll(", India", "").replaceAll("\\d", ""));
                progressD = new ProgressD(this, "Loading Restaurants in " + areaName);
                init();

            }).addOnFailureListener((exception) -> {
                if (exception instanceof ApiException) {

                    Log.e(TAG, "Place not found: " + exception.getMessage());
                }
            });


            System.out.println("lat lng " + lat + " " + lng);
        } else {

            lat = getIntent().getDoubleExtra("lat", 0);
            lng = getIntent().getDoubleExtra("lng", 0);


            progressD = new ProgressD(this, "Loading restaurants near you");
            titleTxt.setText(new CommonUtils(this).getAddress(lat, lng));
            init();
        }

    }

    void fromXml() {


        recyclerView = findViewById(R.id.shops_recyler);
        refreshLayout = findViewById(R.id.swipeRefresh);
        toolbar = findViewById(R.id.toptool);
        titleTxt = findViewById(R.id.txt_title);
        imBack = findViewById(R.id.action_back);
        imBack.setOnClickListener(v -> onBackPressed());
        titleTxt.setOnClickListener(v -> onBackPressed());
        titleTxt.setPaintFlags(titleTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        searchText = findViewById(R.id.text_edit);
        editRemove = findViewById(R.id.editCancel);
        searchProgress = findViewById(R.id.editProgress);

        editRemove.setOnClickListener(v -> {

            Objects.requireNonNull(searchText.getText()).clear();

        });

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                ShopsModel shopsModel = new ShopsModel(ShopsActivity.this.getApplication(), lat, lng, sort, s.toString(), 1);

                itemViewModel.setNew(shopsModel);

                if (s.toString().length() > 0) {
                    editRemove.setVisibility(View.VISIBLE);
                } else {
                    editRemove.setVisibility(View.INVISIBLE);
                }

            }
        });

    }


    void init() {


        ShopsModel shopsModel = new ShopsModel(ShopsActivity.this.getApplication(), lat, lng, sort, "", 0);

        itemViewModel = ViewModelProviders.of(this, new ShopsViewModelFactory(shopsModel)).get(ShopsViewModel.class);

        itemViewModel.networkState.observe(this, s -> {

            assert s != null;
            if (s.equalsIgnoreCase(CommonUtils.LOADING)) {
                progressD.showProgressDialog();
            } else if (s.equalsIgnoreCase(CommonUtils.LOADED) || s.equalsIgnoreCase(CommonUtils.LOADED_MORE)) {
                progressD.HideProgressDialog();
            } else if (s.equalsIgnoreCase(CommonUtils.LOADING_SEARCH)) {
                searchProgress.setVisibility(View.VISIBLE);
            } else if (s.equalsIgnoreCase(CommonUtils.LOADED_SEARCH)) {
                searchProgress.setVisibility(View.INVISIBLE);
                progressD.HideProgressDialog();
            }

        });

        final ShopsListAdapter adapter = new ShopsListAdapter(ShopsActivity.this);


        itemViewModel.articleLiveData.observe(this, new Observer<PagedList<ShopsData>>() {
            @Override
            public void onChanged(@Nullable PagedList<ShopsData> items) {

                adapter.submitList(items);

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShopsActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        refreshLayout.setOnRefreshListener(() -> {

            itemViewModel.invalidate();
            refreshLayout.setRefreshing(false);
        });

    }

}
