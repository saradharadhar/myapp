package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    //splash time out for slash screen
    private static int SPLASH_TIME_OUT=3000;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        t= (TextView)findViewById(R.id.textView2);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
        t.setTypeface(myCustomFont);
        //t.animate().alpha(1).setDuration(4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

}
