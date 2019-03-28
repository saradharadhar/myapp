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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder1> {

    Context context;
    ArrayList<Orders> carts;

    public OrdersAdapter(Context c,ArrayList<Orders> r) {

        context=c;
        carts=r;

    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrdersAdapter.MyViewHolder1(LayoutInflater.from(context).inflate(R.layout.cardview2,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 myViewHolder, int i) {

        myViewHolder.name.setText(carts.get(i).getName());
        myViewHolder.placedBy.setText(carts.get(i).getPlacedBy());
        myViewHolder.time.setText(carts.get(i).getTimeAndDate());



    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    class MyViewHolder1 extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView placedBy,time;
        Button book;

        //ImageView photo;*/


        public MyViewHolder1(View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.orderplace_name);
            placedBy=(TextView)itemView.findViewById(R.id.orderplace_placedBy);
            time=(TextView)itemView.findViewById(R.id.orderplace_time);
            /*rating=(TextView)itemView.findViewById(R.id.rest_rating);
            photo=(ImageView)itemView.findViewById(R.id.rest_image);
            book=(Button)itemView.findViewById(R.id.book);

            hours.setVisibility(View.INVISIBLE);
            type.setVisibility(View.INVISIBLE);
            rating.setVisibility(View.INVISIBLE);
            photo.setVisibility(View.INVISIBLE);
            book.setVisibility(View.INVISIBLE);
*/

        }
    }
}
