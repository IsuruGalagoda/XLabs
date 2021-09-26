package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class LabTestAppointment extends AppCompatActivity {

    EditText timePicker,datePicker,name,testId,email,contactNumber;
    Button timePick,datePick,submitApp;
    DatabaseReference dbRef;
    Appointmentlab aptl;


    private int Year,Month,Day,Hour,Minuite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_appointment);

        timePick=(Button)findViewById(R.id.btnTimePicker);
        datePick=(Button)findViewById(R.id.btnDatePicker);
        submitApp=(Button)findViewById(R.id.btnSbAppoinmnt);


        timePicker=(EditText)findViewById(R.id.edttxtTimePicker);
        datePicker=(EditText)findViewById(R.id.edtextdatePicker);
        name=(EditText)findViewById(R.id.etxtPname);
        testId=(EditText)findViewById(R.id.edtTextLabChemId);
        email=(EditText)findViewById(R.id.etxtEmailAddress);
        contactNumber=(EditText)findViewById(R.id.editTextPhone);

        aptl = new Appointmentlab();

        datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePick();
            }
        });

        timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePick();
            }
        });

        submitApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef= FirebaseDatabase.getInstance().getReference().child("Appointmentlab");

                try{

                    if(TextUtils.isEmpty(name.getText().toString()))
                        Toast.makeText(LabTestAppointment.this, "Please Enter Patient Name", Toast.LENGTH_SHORT).show();
                    
                    else if(TextUtils.isEmpty(testId.getText().toString()))
                        Toast.makeText(LabTestAppointment.this, "Please Enter Selected Test ID", Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(datePicker.getText().toString()))
                        Toast.makeText(LabTestAppointment.this, "Please Select a Date", Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(timePicker.getText().toString()))
                        Toast.makeText(LabTestAppointment.this, "Please Select Time", Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(LabTestAppointment.this, "Please Enter Email", Toast.LENGTH_SHORT).show();

                    //validation for email pattern
                    else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                        email.setError("please Enter Valid Email");
                    }

                    else if(TextUtils.isEmpty(contactNumber.getText().toString()))
                        Toast.makeText(LabTestAppointment.this, "Please Enter Contact Number", Toast.LENGTH_SHORT).show();

                    //contact number validation for enter 10 digits
                    else if(!contactNumber.getText().toString().matches("[0-9]{10}")){
                        contactNumber.setError("Enter 10 digit Contact Number ");
                    }

                    else{

                       // String id =dbRef.push().getKey();

                        //Take inputs from the user and assigning them to the instance of the Appointmentlab class
                        aptl.setPatientName(name.getText().toString().trim());
                        aptl.setTestId(Integer.parseInt(testId.getText().toString().trim()));
                        aptl.setDate(datePicker.getText().toString().trim());
                        aptl.setTime(timePicker.getText().toString().trim());
                        aptl.setEmail(email.getText().toString().trim());
                        aptl.setContactNumber(Integer.parseInt(contactNumber.getText().toString().trim()));

                       // dbRef.child(id).setValue(aptl);
                        dbRef.push().setValue(aptl);

                        Toast.makeText(getApplicationContext(), "Appointment added successfully", Toast.LENGTH_SHORT).show();
                        clearControls();

                    }

                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void clearControls(){

       name.setText("");
       testId.setText("");
       datePicker.setText("");
       timePicker.setText("");
       email.setText("");
       contactNumber.setText("");
    }


    public void datePick() {

        final Calendar calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                datePicker.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        },Day, Month,Year );

        datePickerDialog.show();
    }

    public void timePick(){
        final Calendar c= Calendar.getInstance();
        Hour= c.get(Calendar.HOUR_OF_DAY);

        Minuite=c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minuite) {
                timePicker.setText(hourOfDay+":"+minuite);
            }
        },Hour,Minuite,false);
        timePickerDialog.show();
    }
}