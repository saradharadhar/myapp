package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class shopPage extends AppCompatActivity {

    ImageButton ball,food,jacket,jumper,hat,bone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_page);
        ball=(ImageButton)findViewById(R.id.dogballs);
        food=(ImageButton)findViewById(R.id.dogfood);
        jacket=(ImageButton)findViewById(R.id.dogjacket);
        jumper=(ImageButton)findViewById(R.id.dogjumper);
        hat=(ImageButton)findViewById(R.id.doghat);
        bone=(ImageButton)findViewById(R.id.dogbone);

        bone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cart = new Intent(shopPage.this, cartPage.class);
                startActivity(cart);
                finish();
            }
        });

    }
}
