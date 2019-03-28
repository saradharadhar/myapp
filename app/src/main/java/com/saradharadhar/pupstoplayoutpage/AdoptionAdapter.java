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
//adapter for adoption recylcer
public class AdoptionAdapter extends RecyclerView.Adapter<AdoptionAdapter.MyViewHolder> {

    Context context;
    ArrayList<Adoptions> adoptions;
OnNoteListenerAdopt onNoteListener;
    public AdoptionAdapter(Context c,ArrayList<Adoptions> r,OnNoteListenerAdopt onNoteListener) {

        context=c;
        adoptions=r;
        this.onNoteListener=onNoteListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);
        return new MyViewHolder(view,onNoteListener);
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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        TextView breed,age,colour,rating;
        ImageView photo;

        Button adopt;
        OnNoteListenerAdopt onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListenerAdopt onNoteListener) {
            super(itemView);

            breed=(TextView)itemView.findViewById(R.id.rest_name);
            age=(TextView)itemView.findViewById(R.id.rest_type);
            colour=(TextView) itemView.findViewById(R.id.rest_hours);
            photo=(ImageView) itemView.findViewById(R.id.rest_image);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            //adopt.setText("Adopt");
            rating.setVisibility(View.INVISIBLE);

            this.onNoteListener= onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
          onNoteListener.onNoteClickAdopt(getAdapterPosition());

        }
    }

    public interface OnNoteListenerAdopt
    {
        void onNoteClickAdopt(int position);
    }
}
