package com.saradharadhar.pupstoplayoutpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    Context context;
    ArrayList<Restaurants> restaurants;

    public RestaurantAdapter(Context c,ArrayList<Restaurants> r) {

        context=c;
        restaurants=r;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(restaurants.get(i).getName());
        myViewHolder.hours.setText(restaurants.get(i).getHours());
        myViewHolder.type.setText(restaurants.get(i).getType());
        myViewHolder.rating.setText(restaurants.get(i).getRating());
        Picasso.get().load(restaurants.get(i).getPhoto()).into(myViewHolder.photo);




    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,hours,type,rating;

        ImageView photo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.rest_name);
            hours=(TextView)itemView.findViewById(R.id.rest_hours);
            type=(TextView)itemView.findViewById(R.id.rest_type);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            photo=(ImageView)itemView.findViewById(R.id.rest_image);



        }
    }
}
