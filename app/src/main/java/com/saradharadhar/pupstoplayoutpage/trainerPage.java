package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class trainerPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, TrainerAdapter.OnNoteListenerTrain {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    DatabaseReference reference;
    private RecyclerView recyclerView;
    ArrayList<Trainers> list;
    TrainerAdapter trainerAdapter ;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_page);

        recyclerView = (RecyclerView) findViewById(R.id.trainer_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        auth=FirebaseAuth.getInstance();
        getSupportActionBar().setTitle("Trainers");
        final TrainerAdapter.OnNoteListenerTrain onNoteListenerTrain=this;

        reference = FirebaseDatabase.getInstance().getReference().child("Trainers");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Trainers>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Trainers s = dataSnapshot1.getValue(Trainers.class);
                    list.add(s);
                }

                trainerAdapter = new TrainerAdapter(trainerPage.this, list,onNoteListenerTrain);
                recyclerView.setAdapter(trainerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(trainerPage.this, "wrong", Toast.LENGTH_SHORT).show();

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_id);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // method invoked only when the actionBar is not null.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(trainerPage.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.nav_home)
        {
            Intent login = new Intent(trainerPage.this, homePage3.class);
            startActivity(login);
            finish();

        }
        if(menuItem.getItemId()==R.id.nav_rest)
        {
            Intent home = new Intent(trainerPage.this, restaurantPage.class);
            startActivity(home);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_spa)
        {
            Intent home = new Intent(trainerPage.this, spaPage.class);
            startActivity(home);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_vet)
        {
            Intent vet = new Intent(trainerPage.this, vetPage.class);
            startActivity(vet);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_trainer)
        {
            Intent trainer = new Intent(trainerPage.this, trainerPage.class);
            startActivity(trainer);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_lodge)
        {
            Intent lodge = new Intent(trainerPage.this, lodgePage.class);
            startActivity(lodge);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_logout)
        {
            auth.signOut();
            Intent login = new Intent(trainerPage.this, Main2Activity.class);
            startActivity(login);
            finish();

        }
        if(menuItem.getItemId()==R.id.nav_shop)
        {
            Intent shop = new Intent(trainerPage.this, shopPage.class);
            startActivity(shop);
            finish();
        }
        return false;
    }

    @Override
    public void onNoteClickTrain(int position) {

        Intent shop = new Intent(trainerPage.this, trainerBookingPage.class);
        shop.putExtra("position",list.get(position));
        startActivity(shop);
        finish();

    }
}
