package com.shokry.nearby.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shokry.nearby.Models.ForsquareResponseModel;
import com.shokry.nearby.R;
import com.squareup.picasso.Picasso;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ForsquareResponseModel dataSet;
    Activity activity;

    public RecyclerAdapter(ForsquareResponseModel data, Activity activity) {
        this.dataSet = data;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {


        if (dataSet.getResponse().getVenues().get(listPosition).getName() != null) {
            holder.placeNameTextView.setText(dataSet.getResponse().getVenues().get(listPosition).getName());
        } else {
            holder.placeNameTextView.setText("no data");
        }

        if (dataSet.getResponse().getVenues().get(listPosition).getLocation() != null) {
            holder.placeAddressTextView.setText(dataSet.getResponse().getVenues().get(listPosition).getLocation().getAddress());
        } else {
            holder.placeAddressTextView.setText("no data");
        }
        if (dataSet.getResponse().getVenues().get(listPosition).getCategories().size() > 0) {
            String imageUrl = dataSet.getResponse().getVenues().get(listPosition).getCategories().get(0).getIcon().getPrefix() + "120" + dataSet.getResponse().getVenues().get(listPosition).getCategories().get(0).getIcon().getSuffix();
            Picasso.with(activity).load(
                    imageUrl).error(activity.getResources().getDrawable(R.drawable.defult_image)).resize(200, 200).into(holder.placeIconImageView);
            holder.placeInfoTextView.setText(dataSet.getResponse().getVenues().get(listPosition).getCategories().get(0).getName());

        } else {
            holder.placeIconImageView.setImageDrawable(activity.getResources().getDrawable(R.drawable.defult_image));
            holder.placeInfoTextView.setText("no data");

        }

        holder.itemRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + dataSet.getResponse().getVenues().get(listPosition).getLocation().getLat() + "," + dataSet.getResponse().getVenues().get(listPosition).getLocation().getLng()));
                activity.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return dataSet.getResponse().getVenues().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView placeNameTextView,
                placeInfoTextView,
                placeAddressTextView;
        ImageView placeIconImageView;
        RelativeLayout itemRelativeLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.placeNameTextView = (TextView) itemView.findViewById(R.id.recycler_place_name);
            this.placeInfoTextView = (TextView) itemView.findViewById(R.id.recycler_place_info);
            this.placeAddressTextView = (TextView) itemView.findViewById(R.id.recycler_place_address);
            this.placeIconImageView = (ImageView) itemView.findViewById(R.id.recycler_image);
            this.itemRelativeLayout = (RelativeLayout) itemView.findViewById(R.id.recycler_layout);

        }
    }


}
