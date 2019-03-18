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

public class SpaAdapter extends RecyclerView.Adapter<SpaAdapter.MyViewHolder>{

    Context context;
    ArrayList<Spas> spas;

    public SpaAdapter(Context c,ArrayList<Spas> s) {

        context=c;
        spas=s;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SpaAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(spas.get(i).getName());
        myViewHolder.hours.setText(spas.get(i).getHours());
        //myViewHolder.address.setText(spas.get(i).getAddress());
        myViewHolder.rating.setText(spas.get(i).getRating());
        Picasso.get().load(spas.get(i).getPhoto()).into(myViewHolder.photo);

    }

    @Override
    public int getItemCount() {
        return spas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,hours,rating,address;

        ImageView photo;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.rest_name);
        hours=(TextView)itemView.findViewById(R.id.rest_hours);
        address=(TextView)itemView.findViewById(R.id.rest_type);
        rating=(TextView)itemView.findViewById(R.id.rest_rating);
        photo=(ImageView)itemView.findViewById(R.id.rest_image);
    }
}
}
