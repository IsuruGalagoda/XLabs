package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MyLabBookings extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    Button addNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lab_bookings);

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        addNew = (Button)findViewById(R.id.btnAddLabApp);

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labAppNew();
            }
        });

        new FirebaseDatabaseHelper().readLabApp(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Appointmentlab> appointmentlabs, List<String> keys) {

                findViewById(R.id.progressBar).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView,MyLabBookings.this,appointmentlabs,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });



    }

    public void labAppNew(){
        Intent intent = new Intent(this, LabTestAppointment.class);
        startActivity(intent);
    }
}