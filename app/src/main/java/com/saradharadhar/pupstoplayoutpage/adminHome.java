package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class adminHome extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Admin Home");

    }

    public void adminRest(View view) {
        Intent intent = new Intent(adminHome.this, orderPage.class);
        startActivity(intent);
        finish();
    }
    public void adminSpa(View view) {
        Intent intent = new Intent(adminHome.this, spaAdmin.class);
        startActivity(intent);
        finish();
    }
    public void adminLodge(View view) {
        Intent intent = new Intent(adminHome.this, lodgeAdmin.class);
        startActivity(intent);
        finish();
    }
    public void adminVet(View view) {
        Intent intent = new Intent(adminHome.this, vetAdmin.class);
        startActivity(intent);
        finish();
    }
    public void adminTrainer(View view) {
        Intent intent = new Intent(adminHome.this, trainerAdmin.class);
        startActivity(intent);
        finish();
    }
}
