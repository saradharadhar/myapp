package com.saradharadhar.pupstoplayoutpage;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class restaurantPage extends AppCompatActivity {
    TextView t,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_page);
        t=(TextView)findViewById(R.id.textViewTitle);
        b=(TextView)findViewById(R.id.textViewTitle2);
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Amatic-Bold.ttf");
        t.setTypeface(myCustomFont);
        b.setTypeface(myCustomFont);
    }
}
