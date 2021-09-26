package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import static com.example.XLab.R.id.listww;

public class LabTestList extends AppCompatActivity {

    SearchView searchTest;
    ListView listView;
    Button addAppoin,viewAppoi;

    String item[]= new String[] {" 01 ECG "," 02 Lipid ", " 03 TSH ",};

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_list);

        listView=(ListView) findViewById(listww);
        addAppoin = (Button)findViewById(R.id.btnGo);
        viewAppoi = (Button)findViewById(R.id.btnPreLabApp);
        searchTest=(SearchView)findViewById(R.id.searchView);



        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,item);
        listView.setAdapter(adapter);

        searchTest.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        addAppoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddAppointment();
            }
        });

        viewAppoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seePreviousLabApp();
            }
        });



    }

    public void openAddAppointment(){
        Intent intent = new Intent(this, LabTestAppointment.class);
        startActivity(intent);
    }

    public void seePreviousLabApp(){
        Intent intent = new Intent(this, MyLabBookings.class);
        startActivity(intent);
    }



}
