package com.anto.restaurants.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.anto.restaurants.R;
import com.anto.restaurants.RestaurantsP.ShopsActivity;

import java.util.ArrayList;
import java.util.List;

public class PlacesListAdapter extends RecyclerView.Adapter<PlacesListAdapter.PlacesViewHolder> {

    class PlacesViewHolder extends RecyclerView.ViewHolder {
        private final TextView placeName;

        private PlacesViewHolder(View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.textview);
        }
    }

    private final LayoutInflater mInflater;
    private List<PlacesData> mPlaces;
    private View itemView;
    Context context;

    public PlacesListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public PlacesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(R.layout.places_item, parent, false);
        return new PlacesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlacesViewHolder holder, int position) {


        if (mPlaces != null) {


            holder.placeName.setText(mPlaces.get(position).areaName);


            itemView.setOnClickListener(v -> {


                try {
                    PlacesData current = mPlaces.get(position);

                    Intent intent = new Intent(context, ShopsActivity.class);
                    intent.putExtra("placeId", current.placeId);
                    intent.putExtra("areaName", current.areaName);
                    context.startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(context, "There may be some problem with places api", Toast.LENGTH_SHORT).show();
                }

            });
        } else {
            holder.placeName.setText("No Place");
        }
    }

    public void setPlaces(List<PlacesData> places) {

        mPlaces = new ArrayList<>();
        mPlaces.clear();
        mPlaces = places;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if (mPlaces != null)
            return mPlaces.size();
        else return 0;
    }
}