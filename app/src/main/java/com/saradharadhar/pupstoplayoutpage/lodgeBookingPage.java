package com.saradharadhar.pupstoplayoutpage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class lodgeBookingPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Lodges entry = null;

    TextView name,hours,rating,type;
    ImageView imageView;

    ImageButton location,call;

    FirebaseAuth auth;
    String [] timings;
    String selectedTime;

    DatabaseReference mDatabase;

    Button book;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lodge_booking_page);

        auth=FirebaseAuth.getInstance();


        name=(TextView)findViewById(R.id.name_booking_lodge);
        type=(TextView)findViewById(R.id.type_booking_lodge);
        hours=(TextView)findViewById(R.id.hours_booking_lodge);
        rating=(TextView)findViewById(R.id.rating_booking_lodge);
        location=(ImageButton)findViewById(R.id.location_booking_lodge);
        call=(ImageButton)findViewById(R.id.call_booking_lodge);
        book=(Button)findViewById(R.id.button_booking_lodge);
        final String email=auth.getCurrentUser().getEmail();

        if(getIntent().hasExtra("position")) {
            entry = getIntent().getParcelableExtra("position");
        }

        String namez=entry.getName();
        //String typez=entry.getType();
        String hourz=entry.getHours();
        String ratez=entry.getRating();
        type.setVisibility(View.INVISIBLE);

        final String latitude=entry.getLatitude();
        final String longitute=entry.getLongitude();
        final String phone=entry.getPhone();

        name.setText(namez);
        //type.setText(typez);
        hours.setText(hourz);
        rating.setText(ratez);

        String image1=entry.getImage1();
        String image2=entry.getImage2();
        String image3=entry.getImage3();

        String images []={image1,image2,image3};


        ViewPager viewPager=findViewById(R.id.view_pager_booking_lodge);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this,images);
        viewPager.setAdapter(viewPagerAdapter);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr=" + 19.1387484 + "," + 72.8112805 + "&daddr=" + latitude + "," + longitute;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialContactPhone(phone);
            }
        });

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


        timings=getResources().getStringArray(R.array.restaurantBooking);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(lodgeBookingPage.this);
                builder.setTitle("select");
                builder.setSingleChoiceItems(timings, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        selectedTime=timings[which];

                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton("Book Table", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Orders c=new Orders();
                        c.setName(entry.getName());
                        c.setTimeAndDate(selectedTime);
                        c.setPlacedBy(email);
                        writeToOrders(c);
                        Toast.makeText(lodgeBookingPage.this, "Order Placed", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("Dissmiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation_menu, menu);

        return super.onCreateOptionsMenu(menu);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.nav_home)
        {
            Intent login = new Intent(lodgeBookingPage.this, homePage3.class);
            startActivity(login);
            finish();

        }
        if(menuItem.getItemId()==R.id.nav_rest)
        {
            Intent home = new Intent(lodgeBookingPage.this, restaurantPage.class);
            startActivity(home);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_spa)
        {
            Intent home = new Intent(lodgeBookingPage.this, spaPage.class);
            startActivity(home);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_vet)
        {
            Intent vet = new Intent(lodgeBookingPage.this, vetPage.class);
            startActivity(vet);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_trainer)
        {
            Intent trainer = new Intent(lodgeBookingPage.this, trainerPage.class);
            startActivity(trainer);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_lodge)
        {
            Intent lodge = new Intent(lodgeBookingPage.this, lodgePage.class);
            startActivity(lodge);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_logout)
        {
            auth.signOut();
            Intent login = new Intent(lodgeBookingPage.this, Main2Activity.class);
            startActivity(login);
            finish();

        }
        if(menuItem.getItemId()==R.id.nav_shop)
        {
            Intent shop = new Intent(lodgeBookingPage.this, shopPage.class);
            startActivity(shop);
            finish();
        }
        return false;
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
        mDatabase.child("Lodge Orders").child("order" +format).setValue(c);



    }

    public int getSize()
    {
        mDatabase= FirebaseDatabase.getInstance().getReference();
        final int[] count = {0};

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count[0] = (int) dataSnapshot.child("Lodge Orders").getChildrenCount();


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
