package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class DisplayLabBkDetails extends AppCompatActivity {

    private EditText namePatient,testID,date,time,email,contactNumb;
    private Button update,delete,datePick,timePick;

    private int year,month,day,hour,minute;

    private String key;
    private String name,dateLab,timeLab,emailLab,labChem,contactN;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatedelete_lab_bk_details);

        //get values
        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        labChem = getIntent().getStringExtra("chemId");
        dateLab = getIntent().getStringExtra("date");
        timeLab = getIntent().getStringExtra("time");
        emailLab = getIntent().getStringExtra("email");
        contactN = getIntent().getStringExtra("contactNumb");


        namePatient =(EditText)findViewById(R.id.patientName);
        testID =(EditText)findViewById(R.id.labTestID);
        date =(EditText)findViewById(R.id.selectDate);
        time =(EditText)findViewById(R.id.selectTime);
        email =(EditText)findViewById(R.id.enterEmail);
        contactNumb =(EditText)findViewById(R.id.enterPhone);



        //set values
        namePatient.setText(name);
        testID.setText(labChem);
        date.setText(dateLab);
        time.setText(timeLab);
        email.setText(emailLab);
        contactNumb.setText(contactN);



        update = (Button)findViewById(R.id.btnUpdate);
        delete = (Button)findViewById(R.id.btnDelete);
        datePick = (Button)findViewById(R.id.btnDatePickerShowDetails);
        timePick = (Button)findViewById(R.id.btnTimePickerShowDetails);



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

        //UPDATE
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appointmentlab aLab = new Appointmentlab();
                aLab.setPatientName(namePatient.getText().toString());
                aLab.setTestId(Integer.parseInt(testID.getText().toString()));
                aLab.setDate(date.getText().toString());
                aLab.setTime(time.getText().toString());
                aLab.setEmail(email.getText().toString());
                aLab.setContactNumber(Integer.parseInt(contactNumb.getText().toString()));

                new FirebaseDatabaseHelper().updateLabApp(key, aLab, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Appointmentlab> appointmentlabs, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(DisplayLabBkDetails.this, "Lab Appoinment Updated Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });


        //DELETE
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDatabaseHelper().deleteLabApp(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<Appointmentlab> appointmentlabs, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(DisplayLabBkDetails.this, "Lab Appoinment Deleted Sccessfully ", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                });
            }
        });

    }


    //DATE PICKER
    public void datePick() {

        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        },day, month,year );

        datePickerDialog.show();
    }


    //TIME PICKER
    public void timePick(){
        final Calendar c= Calendar.getInstance();
        hour= c.get(Calendar.HOUR_OF_DAY);

        minute=c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minuite) {
                time.setText(hourOfDay+":"+minuite);
            }
        },hour,minute,false);
        timePickerDialog.show();
    }
}