package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Doctor_List extends AppCompatActivity {

    Button b14,b15,b16,b17,b18,b4,b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);


        b14 = findViewById(R.id.button14);
        b15 = findViewById(R.id.button15);
        b16 = findViewById(R.id.button16);
        b17 = findViewById(R.id.button17);
        b18 = findViewById(R.id.button18);


        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), doctor_appointment.class);
                startActivity(i);
            }
        });

        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), doctor_appointment.class);
                startActivity(i);
            }
        });

        b16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), doctor_appointment.class);
                startActivity(i);
            }
        });

        b17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), doctor_appointment.class);
                startActivity(i);
            }
        });
        b18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), doctor_appointment.class);
                startActivity(i);
            }
        });


    }
}