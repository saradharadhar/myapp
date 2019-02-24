package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText user, pass;
    TextView t,d,s;
    Button a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t=(TextView)findViewById(R.id.login);
        a=(Button)findViewById(R.id.signIn);
        b=(Button)findViewById(R.id.signUp);
        d=(TextView)findViewById(R.id.createmsg);
        s=(TextView)findViewById(R.id.skip);

        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Amatic-Bold.ttf");
        t.setTypeface(myCustomFont);
        a.setTypeface(myCustomFont);
        b.setTypeface(myCustomFont);
        d.setTypeface(myCustomFont);
        s.setTypeface(myCustomFont);
    }
    public void homePage(View view)
    {
        user= (EditText)findViewById(R.id.Uname);
        pass=(EditText)findViewById(R.id.pass);

        String  us=user.getText().toString();
        String ps=pass.getText().toString();


        if (us.equals("user123") && ps.equals("bailey"))
        {

            Intent home = new Intent(Main2Activity.this, homePage3.class);
            startActivity(home);

        } else {
            Toast.makeText(Main2Activity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();

        }
    }

    public void move(View view)
    {
        Intent signUp = new Intent(Main2Activity.this, signUpPage.class);
        startActivity(signUp);

    }

    public void skip(View view)
    {
        Intent home = new Intent(Main2Activity.this, homePage3.class);
        startActivity(home);
    }
    }
