package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.Arrays;

public class Pharmacy_List extends AppCompatActivity {

    ListView lv;

    public Button plceorder,orderlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_list);

        lv =(ListView) findViewById(R.id.listviewnn);
        String values[] ={"P001 - Nilantha", "P002 - Keshana","P003 - Jude", "P004 - Dilhan"};


        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, Arrays.asList(values));

        lv.setAdapter(arrayAdapter);

        plceorder = (Button)findViewById(R.id.neworderbtn);
        plceorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity();
            }
        });

        orderlist=findViewById(R.id.orderListbtn);
        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
    }
    public void openActivity(){
        Intent intent = new Intent(this,place_pharmacy_order.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent1 = new Intent(this,Order_List.class);
        startActivity(intent1);
    }
}