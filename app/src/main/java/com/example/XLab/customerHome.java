package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class customerHome extends AppCompatActivity {

    Button signout,pharmacyOrder,docAppointmnt,labAppointmnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        signout = findViewById(R.id.signout);
        pharmacyOrder = findViewById(R.id.pharmacy);
        docAppointmnt = findViewById(R.id.doc
        );
        labAppointmnt = findViewById(R.id.lab);


        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });


        pharmacyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Order_List.class);
                startActivity(i);
            }
        });

        docAppointmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),UpdateDelete_doc_appoin.class);
                startActivity(i);
            }
        });
        labAppointmnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MyLabBookings.class);
                startActivity(i);
            }
        });
    }
}