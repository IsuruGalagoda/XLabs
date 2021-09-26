package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class doctor_appointment extends AppCompatActivity {

    Button submitAppoin;
    EditText patientName,contactNo,age,appoinDate,appoinTime,appoinNo,NICno,email;
    DatabaseReference doctorDBref;
    DoctorAppointments docAppo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment);

        submitAppoin=findViewById(R.id.btnUpdate);
        patientName=findViewById(R.id.patientName);
        contactNo=findViewById(R.id.conName);
        age=findViewById(R.id.age);
        appoinDate=findViewById(R.id.appDate);
        appoinTime=findViewById(R.id.appTime);
        appoinNo=findViewById(R.id.uappointmentNo);
        NICno=findViewById(R.id.NIC);
        email=findViewById(R.id.eMail);

        docAppo = new DoctorAppointments();
        doctorDBref = FirebaseDatabase.getInstance().getReference().child("Doctor_Appoinment");

        submitAppoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientNameTXT = patientName.getText().toString();
                String contactNoTXT = contactNo.getText().toString();
                String ageTXT = age.getText().toString();
                String appointmentDataTXT = appoinDate.getText().toString();
                String appointmentTimeTXT = appoinTime.getText().toString();
                String appointmentNoTXT = appoinNo.getText().toString();
                String nicNoTXT = NICno.getText().toString();
                String emailTXT = email.getText().toString();

                docAppo.setPatientName(patientNameTXT);
                docAppo.setContactNo(contactNoTXT);
                docAppo.setAge(ageTXT);
                docAppo.setAppointmentData(appointmentDataTXT);
                docAppo.setAppointmentTime(appointmentTimeTXT);
                docAppo.setAppointmentNo(appointmentNoTXT);
                docAppo.setNicNo(nicNoTXT);
                docAppo.setEmail(emailTXT);

                doctorDBref.push().setValue(docAppo);

                patientName.setText("");
                contactNo.setText("");
                age.setText("");
                appoinDate.setText("");
                appoinTime.setText("");
                appoinNo.setText("");
                NICno.setText("");
                email.setText("");
            }
        });
    }

}