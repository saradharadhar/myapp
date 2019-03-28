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

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Trainers> trainers;
    DatabaseReference mDatabase;
    ArrayList<Orders> list;
    OnNoteListenerTrain mOnNoteListener;

    public TrainerAdapter(Context c,ArrayList<Trainers> s,OnNoteListenerTrain mOnNoteListener) {

        context=c;
        trainers=s;
        this.mOnNoteListener=mOnNoteListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview,viewGroup,false);
        return new MyViewHolder(view,mOnNoteListener);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView name,hours,rating;

        ImageView photo;

        Button book;

        OnNoteListenerTrain onNoteListener;

        public MyViewHolder(@NonNull View itemView,OnNoteListenerTrain onNoteListener) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.rest_name);
            hours=(TextView)itemView.findViewById(R.id.rest_hours);
            //type=(TextView)itemView.findViewById(R.id.rest_type);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            photo=(ImageView)itemView.findViewById(R.id.rest_image);

            this.onNoteListener=onNoteListener;

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            onNoteListener.onNoteClickTrain(getAdapterPosition());

        }
    }

    public interface OnNoteListenerTrain
    {
        void onNoteClickTrain(int position);
    }



}
