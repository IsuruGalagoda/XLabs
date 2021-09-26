package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    Button submitbtn;
    EditText name,email,pwd,contact;
    DatabaseReference dbf;
    CusAcc cus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        submitbtn = findViewById(R.id.subbtn);
        name = findViewById(R.id.name);
        email= findViewById(R.id.email);
        pwd = findViewById(R.id.password);
        contact = findViewById(R.id.phone);

        cus = new CusAcc();
        dbf = FirebaseDatabase.getInstance().getReference().child("Customer");

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Password = pwd.getText().toString();
                String Phone = contact.getText().toString();

                try {
                    if (TextUtils.isEmpty(Name)) {

                    } else if (TextUtils.isEmpty(Email)) {

                    } else if (TextUtils.isEmpty(Password)) {

                    } else if (TextUtils.isEmpty(Phone)) {

                    } else if ((Name != null) && (Email != null) && (Password != null) && (Phone != null)) {
                        cus.setName(Name);
                        cus.setEmail(Email);
                        cus.setPassword(Password);
                        cus.setPhone(Phone);

                        System.out.println("check1");

                        dbf.push().setValue(cus);

                        System.out.println("check2");
                        Intent intent = new Intent(SignUp.this, Login.class);
                        System.out.println("check3");
                        startActivity(intent);
                        System.out.println("check4");

                        name.setText("");
                        email.setText("");
                        pwd.setText("");
                        contact.setText("");
                    }
                } catch (Exception e) {


                }
            }
        });


    }
}





