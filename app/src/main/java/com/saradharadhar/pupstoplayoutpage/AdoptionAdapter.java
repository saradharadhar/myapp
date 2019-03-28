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

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdoptionAdapter extends RecyclerView.Adapter<AdoptionAdapter.MyViewHolder> {

    Context context;
    ArrayList<Adoptions> adoptions;

    public AdoptionAdapter(Context c,ArrayList<Adoptions> r) {

        context=c;
        adoptions=r;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new AdoptionAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.breed.setText(adoptions.get(i).getBreed());
        myViewHolder.age.setText(adoptions.get(i).getAge());
        myViewHolder.colour.setText(adoptions.get(i).getColour());
        Picasso.get().load(adoptions.get(i).getPhoto()).into(myViewHolder.photo);

    }

    @Override
    public int getItemCount() {
        return adoptions.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView breed,age,colour,rating;
        ImageView photo;

        Button adopt;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            breed=(TextView)itemView.findViewById(R.id.rest_name);
            age=(TextView)itemView.findViewById(R.id.rest_type);
            colour=(TextView) itemView.findViewById(R.id.rest_hours);
            photo=(ImageView) itemView.findViewById(R.id.rest_image);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            adopt.setText("Adopt");
            rating.setVisibility(View.INVISIBLE);
        }
    }
}
