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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        myViewHolder.name.setText(restaurants.get(i).getName());
        myViewHolder.hours.setText(restaurants.get(i).getHours());
        myViewHolder.type.setText(restaurants.get(i).getType());
        myViewHolder.rating.setText(restaurants.get(i).getRating());
        Picasso.get().load(restaurants.get(i).getPhoto()).into(myViewHolder.photo);

        myViewHolder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name;
                name=restaurants.get(i).getName();

                Orders c=new Orders();
                c.setName(name);
                writeToOrders(c);
            }
        });




    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,hours,type,rating;

        Button book;

        ImageView photo;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.rest_name);
            hours=(TextView)itemView.findViewById(R.id.rest_hours);
            type=(TextView)itemView.findViewById(R.id.rest_type);
            rating=(TextView)itemView.findViewById(R.id.rest_rating);
            photo=(ImageView)itemView.findViewById(R.id.rest_image);
            book=(Button)itemView.findViewById(R.id.book);



        }
    }

    private void writeToOrders(final Orders c)
    {
        mDatabase= FirebaseDatabase.getInstance().getReference();

        //final int size = 0;
        //int size = getSize();
    //    Long tsLong = System.currentTimeMillis();
     //   String ts = tsLong.toString();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String format = simpleDateFormat.format(new Date());
        mDatabase.child("Orders").child("order" +format).setValue(c);



    }

    public int getSize()
    {
        mDatabase= FirebaseDatabase.getInstance().getReference();
        final int[] count = {0};

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count[0] = (int) dataSnapshot.child("Orders").getChildrenCount();


              //  for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
             //   {
                    //  Orders r=dataSnapshot1.getValue(Orders.class);

                    // list.add(r);
                    // count++;
             //   }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return count[0];
    }
}
