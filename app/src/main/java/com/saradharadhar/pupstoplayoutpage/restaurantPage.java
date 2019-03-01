package com.saradharadhar.pupstoplayoutpage;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class restaurantPage extends AppCompatActivity {

    //TextView t,b;

    DatabaseReference reference;
    private RecyclerView recyclerView;
    ArrayList<Restaurants> list;
    RestaurantAdapter restaurantAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);
        /*t=(TextView)findViewById(R.id.textViewTitle);
        b=(TextView)findViewById(R.id.textViewTitle2);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Amatic-Bold.ttf");
        t.setTypeface(myCustomFont);
        b.setTypeface(myCustomFont);*/
        recyclerView=(RecyclerView)findViewById(R.id.restaurant_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<Restaurants>();

        reference= FirebaseDatabase.getInstance().getReference().child("Restaurants");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Restaurants r=dataSnapshot1.getValue(Restaurants.class);
                    list.add(r);
                }

                restaurantAdapter=new RestaurantAdapter(restaurantPage.this,list);
                recyclerView.setAdapter(restaurantAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(restaurantPage.this, "wrong", Toast.LENGTH_SHORT).show();

            }
        });




    }
}
