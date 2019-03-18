package com.saradharadhar.pupstoplayoutpage;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Main2Activity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 1;
    private EditText user, pass;
    private TextView loginText,d,skip;
    private Button signIn,signUp;
    private FirebaseAuth auth;
    private ProgressBar loginProgress;
    private SignInButton googleSignInButton;
    private GoogleApiClient mGoogleApiClient;
    private static final String TAG="Main_";
    private FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        auth=FirebaseAuth.getInstance();
        loginText=(TextView)findViewById(R.id.login);
        signIn=(Button)findViewById(R.id.signIn);
        signUp=(Button)findViewById(R.id.signUp);
        d=(TextView)findViewById(R.id.createmsg);
        skip=(TextView)findViewById(R.id.skip);
        user=(EditText)findViewById(R.id.Uname);
        pass=(EditText)findViewById(R.id.pass);
        loginProgress=(ProgressBar)findViewById(R.id.loginProgress);
        googleSignInButton=(SignInButton)findViewById(R.id.googleBtn);

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

                    if(email.equals("admin@gmail.com") && password.equals("admin"))
                    {
                        Intent intent =  new Intent(Main2Activity.this, orderPage.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
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
            }
        });

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("986996198327-rlii089ip0qcpk8mpo18rt8dv2758tdj.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient=new GoogleApiClient.Builder(getApplicationContext()).
                enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(Main2Activity.this, "Error", Toast.LENGTH_SHORT).show();

                    }
                }).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()!=null)
                {
                    skip();
                }

            }
        };
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess())
            {
                GoogleSignInAccount account=result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
            else
            {

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct)
    {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG,"signInWithCredential:onComplete"+task.isSuccessful());
                        if (!task.isSuccessful())
                        {
                            Log.w(TAG,"signInWithCredential",task.getException());
                            Toast.makeText(Main2Activity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(authStateListener);

        FirebaseUser currentUser= auth.getInstance().getCurrentUser();

        if(currentUser!=null)
        {
            Intent home = new Intent(Main2Activity.this, homePage3.class);
            startActivity(home);
            finish();

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
    public void skip(View view)
    {
        Intent home = new Intent(Main2Activity.this, homePage3.class);
        startActivity(home);
        finish();
    }
    }
