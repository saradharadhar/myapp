package com.saradharadhar.pupstoplayoutpage;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

public class restaurantPage extends AppCompatActivity {
    private static final String TAG ="FireLog" ;
    //TextView t,b;
    private RecyclerView recyclerView;

    private FirebaseFirestore firebaseFirestore;

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

        firebaseFirestore=FirebaseFirestore.getInstance();

        firebaseFirestore.collection("Restaurants").addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null)
                {
                    Log.d(TAG,"Error : "+e.getMessage());
                    return;
                }
                for(DocumentSnapshot doc : queryDocumentSnapshots)
                {
                    String name=doc.getString("name");
                    Log.d(TAG,"Name : "+name);
                }
            }
        });
    }
}
