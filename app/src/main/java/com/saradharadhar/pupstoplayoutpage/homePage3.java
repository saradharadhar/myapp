package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class homePage3 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
ViewFlipper viewFlipper;
 DrawerLayout drawerLayout;
 ActionBarDrawerToggle actionBarDrawerToggle;
 FirebaseAuth auth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page3);

        auth=FirebaseAuth.getInstance();
        int images[]={R.drawable.pupstop,R.drawable.beach,R.drawable.yorkie,R.drawable.shihtzu};
        viewFlipper=(ViewFlipper)findViewById(R.id.flipper);
        /*for(int i=0;i<images.length;i++)
        {
            flipperImages(images[i]);
        }*/

        for(int image :images)
        {
            flipperImages(image);
        }

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_id);
        actionBarDrawerToggle=new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // method invoked only when the actionBar is not null.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {


        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }

        return super.onOptionsItemSelected(item);


    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser currentUser= auth.getInstance().getCurrentUser();

        if(currentUser==null)
        {
            Intent loginPage = new Intent(homePage3.this, Main2Activity.class);
            startActivity(loginPage);
            finish();

        }
    }

    public void flipperImages(int image)
    {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(6000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }

    public void RestPage()
    {
        Intent home = new Intent(homePage3.this, restaurantPage.class);
        startActivity(home);
        finish();
    }
    public void RestPage(View view)
    {
        Intent home = new Intent(homePage3.this, restaurantPage.class);
        startActivity(home);
        finish();
    }

    public void SpaPage()
    {
        Intent home = new Intent(homePage3.this, spaPage.class);
        startActivity(home);
        finish();
    }

    public void SpaPage(View view)
    {
        Intent home = new Intent(homePage3.this, spaPage.class);
        startActivity(home);
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.nav_logout)
        {
            auth.signOut();
            Intent login = new Intent(homePage3.this, Main2Activity.class);
            startActivity(login);
            finish();

        }
        if(menuItem.getItemId()==R.id.nav_rest)
        {
            RestPage();
        }
        if(menuItem.getItemId()==R.id.nav_spa)
        {
            SpaPage();
        }

        return false;
    }
}
