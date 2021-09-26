package com.example.XLab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDelete_doc_appoin extends AppCompatActivity {

    Button btnEnter, btnDelete, btnUpdate;
    EditText appoinNo,contact, PName, age, appoinDate, appoinTime, NIC, email;
    TextView id;
    DatabaseReference doctorDbRef;

    DoctorAppointments dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedelete_doc_appointment);

        //id = findViewById(R.id.id);
        btnEnter = findViewById(R.id.btnEnter);
        btnDelete = findViewById(R.id.btnDalate);
        btnUpdate = findViewById(R.id.btnUpdate);
        appoinNo = findViewById(R.id.apEnter);
        contact = findViewById(R.id.conName);
        PName = findViewById(R.id.paName);
        age = findViewById(R.id.age);
        appoinDate = findViewById(R.id.appDate);
        appoinTime = findViewById(R.id.appTime);
        NIC = findViewById(R.id.NIC);
        email = findViewById(R.id.eMail);

        dp = new DoctorAppointments();

        doctorDbRef = FirebaseDatabase.getInstance().getReference().child("Doctor_Appoinment");

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();

                database.getReference("Doctor_Appoinment").orderByChild("appointmentNo").equalTo(appoinNo.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            DoctorAppointments d = data.getValue(DoctorAppointments.class);
                            String ID;
                            ID = data.getKey().toString();
                            id.setText(ID);

                            appoinNo.setText(d.getAppointmentNo().toString());
                            contact.setText(d.getContactNo().toString());
                            PName.setText(d.getPatientName().toString());
                            age.setText(d.getAge().toString());
                            appoinDate.setText(d.getAppointmentData().toString());
                            appoinTime.setText(d.getAppointmentTime().toString());
                            NIC.setText(d.getNicNo().toString());
                            email.setText(d.getEmail().toString());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //UPDATE
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(PName.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Name is Emplty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(NIC.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "NIC is Emplty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(appoinDate.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "AppointmentData is Emplty", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(appoinTime.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "AppointmentTime is Emplty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Update Success!", Toast.LENGTH_SHORT).show();
                        doctorDbRef = FirebaseDatabase.getInstance().getReference();
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("patientName").setValue(PName.getText().toString().trim());
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("contactNo").setValue(contact.getText().toString().trim());
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("age").setValue(age.getText().toString().trim());
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("appointmentData").setValue(appoinDate.getText().toString().trim());
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("appointmentTime").setValue(appoinTime.getText().toString().trim());
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("appointmentNo").setValue(appoinNo.getText().toString().trim());
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("nicNo").setValue(NIC.getText().toString().trim());
                        doctorDbRef.child("Doctor_Appoinment").child(id.getText().toString()).child("email").setValue(email.getText().toString().trim());
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error!"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        //DELETE
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorDbRef = FirebaseDatabase.getInstance().getReference().child("Doctor_Appoinment").child(id.getText().toString());
                doctorDbRef.removeValue();
                PName.setText("");
                contact.setText("");
                age.setText("");
                appoinDate.setText("");
                appoinTime.setText("");
                NIC.setText("");
                email.setText("");

                id.setText("");
                Toast.makeText(getApplicationContext(), "Succesfull!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}