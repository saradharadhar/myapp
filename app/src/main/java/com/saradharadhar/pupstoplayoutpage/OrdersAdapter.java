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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {

    Context context;
    ArrayList<Orders> carts;

    public OrdersAdapter(Context c,ArrayList<Orders> r) {

        context=c;
        carts=r;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrdersAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.name.setText(carts.get(i).getName());



    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;


        public MyViewHolder(View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.rest_name);


        }
    }
}
