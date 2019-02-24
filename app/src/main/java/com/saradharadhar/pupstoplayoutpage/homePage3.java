package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
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
import android.widget.ViewFlipper;


public class homePage3 extends AppCompatActivity
{
ViewFlipper viewFlipper;
 DrawerLayout drawerLayout;
 ActionBarDrawerToggle actionBarDrawerToggle;

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

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //
        Intent home = new Intent(homePage3.this, restaurantPage.class);

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;

        }
        super.onOptionsItemSelected(item);
        switch (item.getItemId())

        {
            case R.id.nav_home:
                startActivity(home);
                return true;
            case R.id.nav_spa:
                startActivity(home);
                return true;
            case R.id.nav_rest:
                startActivity(home);
                return true;
            case R.id.nav_shop:
                startActivity(home);
                return true;
            case R.id.nav_vet:
                startActivity(home);
                return true;
            case R.id.nav_trainer:
                startActivity(home);
            case R.id.nav_lodge:
                startActivity(home);
                return true;
            default:
                return false;

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

    public void move(View view)
    {
        Intent home = new Intent(homePage3.this, restaurantPage.class);
        startActivity(home);
    }


}
