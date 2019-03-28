package com.saradharadhar.pupstoplayoutpage;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class spaAdmin extends AppCompatActivity {

    DatabaseReference reference;
    private RecyclerView recyclerView;
    ArrayList<Orders> list;
    OrdersAdapter OrdersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa_admin);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Spa Orders");

        recyclerView=(RecyclerView)findViewById(R.id.order_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        reference= FirebaseDatabase.getInstance().getReference().child("Spa Orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<Orders>();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Orders r=dataSnapshot1.getValue(Orders.class);

                    list.add(r);
                }

                OrdersAdapter =new OrdersAdapter(spaAdmin.this,list);
                recyclerView.setAdapter(OrdersAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(spaAdmin.this, "wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
