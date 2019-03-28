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

public class SpaAdapter extends RecyclerView.Adapter<SpaAdapter.MyViewHolder>{

    Context context;
    ArrayList<Spas> spas;
    DatabaseReference mDatabase;
    ArrayList<Orders> list;
    OnNoteListenerSpa mOnNoteListener;

    public SpaAdapter(Context c,ArrayList<Spas> s,OnNoteListenerSpa mOnNoteListener) {

        context=c;
        spas=s;
        this.mOnNoteListener=mOnNoteListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);
        return new MyViewHolder(view,mOnNoteListener);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,hours,rating,address;

        ImageView photo;

        Button book;

        OnNoteListenerSpa onNoteListener;

    public MyViewHolder(@NonNull View itemView, OnNoteListenerSpa onNoteListener) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.rest_name);
        hours=(TextView)itemView.findViewById(R.id.rest_hours);
        address=(TextView)itemView.findViewById(R.id.rest_type);
        rating=(TextView)itemView.findViewById(R.id.rest_rating);
        photo=(ImageView)itemView.findViewById(R.id.rest_image);
        this.onNoteListener=onNoteListener;

        itemView.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {

            onNoteListener.onNoteClickSpa(getAdapterPosition());

        }
    }

public interface OnNoteListenerSpa
{
    void onNoteClickSpa(int position);
}


}
