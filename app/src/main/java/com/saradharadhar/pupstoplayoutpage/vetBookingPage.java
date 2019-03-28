package com.saradharadhar.pupstoplayoutpage;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.grpc.internal.LogExceptionRunnable;

public class vetBookingPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "vetBookingPage";

    Vets entry = null;

    TextView name, hours, rating, type;
    ImageView imageView;

    ImageButton location, call;

    FirebaseAuth auth;

    DatabaseReference mDatabase;

    Button book;

    String [] timings;
    String selectedTime;

    private FusedLocationProviderClient client;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_booking_page);

        auth = FirebaseAuth.getInstance();
        final String email=auth.getCurrentUser().getEmail();


        name = (TextView) findViewById(R.id.vet_name_booking);
        type = (TextView) findViewById(R.id.vet_type_booking);
        hours = (TextView) findViewById(R.id.vet_hours_booking);
        rating = (TextView) findViewById(R.id.vet_rating_booking);
        location = (ImageButton) findViewById(R.id.vet_location_booking);
        call = (ImageButton) findViewById(R.id.call_booking_vet);
        book = (Button) findViewById(R.id.vet_button_booking);
        requestPerm();

        timings=getResources().getStringArray(R.array.restaurantBooking);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(vetBookingPage.this);
                builder.setTitle("select");
                builder.setSingleChoiceItems(timings, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        selectedTime=timings[which];

                    }
                });
                builder.setCancelable(false);
                builder.setPositiveButton("Book Appointment", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Orders c=new Orders();
                        c.setName(entry.getName());
                        c.setTimeAndDate(selectedTime);
                        c.setPlacedBy(email);
                        writeToOrders(c);
                        Toast.makeText(vetBookingPage.this, "Order Placed", Toast.LENGTH_SHORT).show();
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

        client = LocationServices.getFusedLocationProviderClient(vetBookingPage.this);

        final Double[] lat = new Double[1];
        final Double[] longi = new Double[1];

        final String currentLat,currentLong;



        /*location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ActivityCompat.checkSelfPermission(vetBookingPage.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
                client.getLastLocation().addOnSuccessListener(vetBookingPage.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {

                        if(location!=null)
                        {
                            lat[0]=location.getLatitude();
                            longi[0]=location.getLongitude();
                        }

                    }
                });
            }
        });*/

        //currentLat=Double.toString(lat[0]);
        //currentLong=Double.toString(longi[0]);




        if(getIntent().hasExtra("position")) {
            entry = getIntent().getParcelableExtra("position");
            Log.d(TAG, "onCreate: "+entry.toString());
        }

        String image1=entry.getImage1();
        String image2=entry.getImage2();
        String image3=entry.getImage3();

        String images []={image1,image2,image3};


        ViewPager viewPager=findViewById(R.id.view_pager_booking_vet);
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(this,images);
        viewPager.setAdapter(viewPagerAdapter);

        String namez=entry.getName();
        String typez=entry.getType();
        String hourz=entry.getHours();
        String ratez=entry.getRating();

        final String latitude=entry.getLatitude();
        final String longitute=entry.getLongitude();
        final String phone=entry.getPhone();

        name.setText(namez);
        //type.setText(typez);
        hours.setText(hourz);
        //rating.setText(ratez);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "http://maps.google.com/maps?saddr=" + 19.0631598 + "," + 72.8227348 + "&daddr=" + latitude + "," + longitute;
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


    }

    private void requestPerm()
    {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
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
            Intent login = new Intent(vetBookingPage.this, homePage3.class);
            startActivity(login);
            finish();

        }
        if(menuItem.getItemId()==R.id.nav_rest)
        {
            Intent home = new Intent(vetBookingPage.this, restaurantPage.class);
            startActivity(home);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_spa)
        {
            Intent home = new Intent(vetBookingPage.this, spaPage.class);
            startActivity(home);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_vet)
        {
            Intent vet = new Intent(vetBookingPage.this, vetPage.class);
            startActivity(vet);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_trainer)
        {
            Intent trainer = new Intent(vetBookingPage.this, trainerPage.class);
            startActivity(trainer);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_lodge)
        {
            Intent lodge = new Intent(vetBookingPage.this, lodgePage.class);
            startActivity(lodge);
            finish();
        }
        if(menuItem.getItemId()==R.id.nav_logout)
        {
            auth.signOut();
            Intent login = new Intent(vetBookingPage.this, Main2Activity.class);
            startActivity(login);
            finish();

        }
        if(menuItem.getItemId()==R.id.nav_shop)
        {
            Intent shop = new Intent(vetBookingPage.this, shopPage.class);
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
        mDatabase.child("Vet Orders").child("order" +format).setValue(c);



    }

    public int getSize()
    {
        mDatabase= FirebaseDatabase.getInstance().getReference();
        final int[] count = {0};

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                count[0] = (int) dataSnapshot.child("Vet Orders").getChildrenCount();


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
