package com.saradharadhar.pupstoplayoutpage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    Context context;
    ArrayList<Restaurants> restaurants;
    DatabaseReference mDatabase;
    ArrayList<Orders> list;
    OnNoteListener mOnNoteListener;

    public RestaurantAdapter(Context c, ArrayList<Restaurants> r,OnNoteListener onNoteListener) {

        context=c;
        restaurants=r;
        this.mOnNoteListener=onNoteListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);
        return new MyViewHolder(view,mOnNoteListener);

        //return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false),mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.name.setText(restaurants.get(i).getName());
        myViewHolder.hours.setText(restaurants.get(i).getHours());
        myViewHolder.type.setText(restaurants.get(i).getType());
        myViewHolder.rating.setText(restaurants.get(i).getRating());
        Picasso.get().load(restaurants.get(i).getPhoto()).into(myViewHolder.photo);
        /*myViewHolder.phone.equals(restaurants.get(i).getPhone());
        myViewHolder.latitude.equals(restaurants.get(i).getLatitude());
        myViewHolder.longitude.equals(restaurants.get(i).getLongitude());*/




    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name,hours,type,rating;

        Button book;

        ImageView photo;

        OnNoteListener onNoteListener;

        String latitude,longitude,phone;


        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.rest_name);
            hours=(TextView)itemView.findViewById(R.id.rest_hours);
            type=(TextView)itemView.findViewById(R.id.rest_type);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            photo=(ImageView)itemView.findViewById(R.id.rest_image);
            latitude="";
            longitude="";
            phone="";

            this.onNoteListener=onNoteListener;

            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {

            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }
    public interface OnNoteListener
    {
        void onNoteClick(int position);
    }
}
