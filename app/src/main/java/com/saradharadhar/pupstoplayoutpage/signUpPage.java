package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class signUpPage extends AppCompatActivity {
    TextView t;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        t=(TextView)findViewById(R.id.Register);
        b=(Button)findViewById(R.id.signUp) ;
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Amatic-Bold.ttf");
        t.setTypeface(myCustomFont);
        b.setTypeface(myCustomFont);
    }
    public void create(View view)
    {
        Intent home = new Intent(signUpPage.this, homePage3.class);
        startActivity(home);
    }
}
