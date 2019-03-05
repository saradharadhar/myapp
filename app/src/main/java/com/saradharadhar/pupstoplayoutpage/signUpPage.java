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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signUpPage extends AppCompatActivity {
    TextView t,a,signUp_email;
    Button signUp_button;
    EditText signUp_pass,signUpc_pass;

    private FirebaseAuth sauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        t=(TextView)findViewById(R.id.Register);
        signUp_button=(Button)findViewById(R.id.signUp_button) ;
        a=(TextView)findViewById(R.id.account);
        signUp_email=(EditText)findViewById(R.id.signUp_email);
        signUp_pass=(EditText)findViewById(R.id.signUp_pass);
        signUpc_pass=(EditText)findViewById(R.id.signUpC_pass);

        sauth=FirebaseAuth.getInstance();
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
        t.setTypeface(myCustomFont);
        signUp_button.setTypeface(myCustomFont);
        a.setTypeface(myCustomFont);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(signUpPage.this, Main2Activity.class);
                startActivity(login);
                finish();

            }
        });

        signUp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=signUp_email.getText().toString();
                String password=signUp_pass.getText().toString();
                String cpassword=signUpc_pass.getText().toString();

                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password)||!TextUtils.isEmpty(cpassword))
                {
                    if(password.equals(cpassword))
                    {
                        sauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    create();
                                }
                                else
                                {
                                    String e=task.getException().getMessage();
                                    Toast.makeText(signUpPage.this, "Error : "+e, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(signUpPage.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public void create()
    {
        Intent home = new Intent(signUpPage.this, homePage3.class);
        startActivity(home);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser= sauth.getInstance().getCurrentUser();

        if(currentUser!=null)
        {
            create();

        }
    }
}
