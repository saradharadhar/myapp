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

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Trainers> trainers;

    public TrainerAdapter(Context c,ArrayList<Trainers> s) {

        context=c;
        trainers=s;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TrainerAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(trainers.get(i).getName());
        myViewHolder.hours.setText(trainers.get(i).getHours());
        //myViewHolder.type.setText(trainers.get(i).getType());
        myViewHolder.rating.setText(trainers.get(i).getRating());
        Picasso.get().load(trainers.get(i).getPhoto()).into(myViewHolder.photo);

    }

    @Override
    public int getItemCount() {
        return trainers.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
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
