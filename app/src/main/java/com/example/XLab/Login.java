package com.example.XLab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button Log,Signup;
    EditText email,pwd;
    DatabaseReference dbf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Log = findViewById(R.id.btnLog);
        Signup = findViewById(R.id.btnSignup);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.password);
        dbf = FirebaseDatabase.getInstance().getReference().child("Customer");

        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbf.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot login:snapshot.getChildren()) {
                            String Email = login.child("email").getValue().toString();
                            String Password = login.child("password").getValue().toString();

                            if((email.getText().toString().equals(Email))&&(pwd.getText().toString().equals(Password))){
                                Toast.makeText(getApplicationContext(),"Login Complete",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login.this,PatientHome.class);
                                startActivity(intent);

                            }
                            else if((Email != email.getText().toString()) && (Password != pwd.getText().toString())){
                                Toast.makeText(getApplicationContext(),"Enter Valid Details",Toast.LENGTH_SHORT).show();
                            }
                        }

                        email.setText("");
                        pwd.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SignUp.class);
                startActivity(i);
            }
        });


    }
}
