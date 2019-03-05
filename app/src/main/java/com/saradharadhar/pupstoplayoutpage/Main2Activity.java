package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {
    private EditText user, pass;
    private TextView loginText,d,skip;
    private Button signIn,signUp;
    private FirebaseAuth auth;
    private ProgressBar loginProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth=FirebaseAuth.getInstance();
        loginText=(TextView)findViewById(R.id.login);
        signIn=(Button)findViewById(R.id.signIn);
        signUp=(Button)findViewById(R.id.signUp);
        d=(TextView)findViewById(R.id.createmsg);
        skip=(TextView)findViewById(R.id.skip);
        user=(EditText)findViewById(R.id.Uname);
        pass=(EditText)findViewById(R.id.pass);
        loginProgress=(ProgressBar)findViewById(R.id.loginProgress);

        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
        loginText.setTypeface(myCustomFont);
        signIn.setTypeface(myCustomFont);
        signUp.setTypeface(myCustomFont);
        d.setTypeface(myCustomFont);
        skip.setTypeface(myCustomFont);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email=user.getText().toString();
                String password=pass.getText().toString();

                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password))
                {
                    loginProgress.setVisibility(View.VISIBLE);

                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful())
                            {
                                skip();
                            }else
                            {
                                String e=task.getException().getMessage();
                                Toast.makeText(Main2Activity.this, "Error : "+e, Toast.LENGTH_SHORT).show();
                            }
                            loginProgress.setVisibility(View.INVISIBLE);
                        }
                    });
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser currentUser= auth.getInstance().getCurrentUser();

        if(currentUser!=null)
        {
            skip();

        }
    }

    public void move(View view)
    {
        Intent signUp = new Intent(Main2Activity.this, signUpPage.class);
        startActivity(signUp);
        finish();

    }

    public void skip()
    {
        Intent home = new Intent(Main2Activity.this, homePage3.class);
        startActivity(home);
        finish();
    }
    }
