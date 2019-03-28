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

public class vetAdapter extends RecyclerView.Adapter<vetAdapter.MyViewHolder> {

    Context context;
    ArrayList<Vets> vets;
    DatabaseReference mDatabase;
    ArrayList<Orders> list;
    OnNoteListenerVet mOnNoteListener;

    public vetAdapter(Context c,ArrayList<Vets> s,OnNoteListenerVet mOnNoteListener) {

        context=c;
        vets=s;
        this.mOnNoteListener=mOnNoteListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);
        return new MyViewHolder(view,mOnNoteListener);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.name.setText(vets.get(i).getName());
        myViewHolder.hours.setText(vets.get(i).getHours());
        myViewHolder.type.setText(vets.get(i).getType());
        myViewHolder.rating.setText(vets.get(i).getRating());
        Picasso.get().load(vets.get(i).getPhoto()).into(myViewHolder.photo);

    }

    @Override
    public int getItemCount() {
        return vets.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name,hours,rating,type;

        ImageView photo;

        Button book;

        OnNoteListenerVet onNoteListener;

        public MyViewHolder(@NonNull View itemView,OnNoteListenerVet onNoteListener) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.rest_name);
            hours=(TextView)itemView.findViewById(R.id.rest_hours);
            type=(TextView)itemView.findViewById(R.id.rest_type);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            photo=(ImageView)itemView.findViewById(R.id.rest_image);
            this.onNoteListener=onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClickVet(getAdapterPosition());
        }
    }

    public interface OnNoteListenerVet
    {
        void onNoteClickVet(int position);
    }

}
