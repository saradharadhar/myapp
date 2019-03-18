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

public class LodgeAdapter extends RecyclerView.Adapter<LodgeAdapter.MyViewHolder> {

    Context context;
    ArrayList<Lodges> lodges;

    public LodgeAdapter(Context c,ArrayList<Lodges> s) {

        context=c;
        lodges=s;


    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LodgeAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(lodges.get(i).getName());
        myViewHolder.hours.setText(lodges.get(i).getHours());
        //myViewHolder.type.setText(trainers.get(i).getType());
        myViewHolder.rating.setText(lodges.get(i).getRating());
        Picasso.get().load(lodges.get(i).getPhoto()).into(myViewHolder.photo);

    }

    @Override
    public int getItemCount() {
        return lodges.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,hours,rating;

        ImageView photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.rest_name);
            hours=(TextView)itemView.findViewById(R.id.rest_hours);
            //type=(TextView)itemView.findViewById(R.id.rest_type);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            photo=(ImageView)itemView.findViewById(R.id.rest_image);
        }
    }
}
