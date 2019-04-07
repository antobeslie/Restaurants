package com.anto.restaurants.RestaurantsP;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anto.restaurants.R;
import com.anto.restaurants.RestaurantsP.Repository.ShopsData;
import com.anto.restaurants.support.CommonUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ShopsListAdapter extends PagedListAdapter<ShopsData, ShopsListAdapter.RecyclerViewHolder> {


    Context context;

    public ShopsListAdapter(Context context) {
        super(ShopsData.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rest_item, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {

        ShopsData shopsData = getItem(i);
        recyclerViewHolder.bindView(shopsData);


    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {


        ImageView shop_icon;
        TextView shop_name, shop_address, shop_rating, shop_cuisines, shop_cost;
        CardView rate_card;
        ProgressBar progressBar;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            progressBar = itemView.findViewById(R.id.progress);
            shop_icon = itemView.findViewById(R.id.shop_icon);
            shop_name = itemView.findViewById(R.id.shop_name);
            shop_address = itemView.findViewById(R.id.shop_address);
            shop_rating = itemView.findViewById(R.id.shop_rating);
            shop_cuisines = itemView.findViewById(R.id.shop_cuisines);
            shop_cost = itemView.findViewById(R.id.shop_cost);
            rate_card = itemView.findViewById(R.id.rate_card);
        }

        void bindView(ShopsData restaurant) {


            String cost_txt = context.getString(R.string.Rs) + restaurant.shopCost + " for two people";
            shop_name.setText(restaurant.shopName);
            shop_address.setText(restaurant.shopAddress);
            shop_cost.setText(cost_txt);
            shop_rating.setText(String.valueOf(restaurant.shopRating));
            shop_cuisines.setText(restaurant.shopCuisines);


            String thumb = restaurant.shopImage;

            try {
                if (!TextUtils.isEmpty(thumb))
                    Picasso.get().load(thumb)
                            .fit()
                            .centerCrop()
                            .placeholder(R.drawable.ee_min)
                            .into(shop_icon, new Callback() {
                                @Override
                                public void onSuccess() {
                                    progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Exception e) {
                                    progressBar.setVisibility(View.GONE);

                                }
                            });
                else {
                    progressBar.setVisibility(View.GONE);
                    shop_icon.setImageDrawable(context.getDrawable(R.drawable.ee_min));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            rate_card.setCardBackgroundColor(new CommonUtils(context).getRateColor(restaurant.shopRating));


        }
    }
}
