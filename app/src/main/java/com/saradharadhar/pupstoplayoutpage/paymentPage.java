package com.saradharadhar.pupstoplayoutpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class paymentPage extends AppCompatActivity {

    Button cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_page);
        cod=(Button)findViewById(R.id.codbutton);
        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(paymentPage.this, "Order Confirmed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
