package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
//import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class Order_List extends AppCompatActivity {


    private RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_order_list);

        mRecycleView=(RecyclerView) findViewById(R.id.recycleViewOr);
        new FirebaseDBHelper().readOrders(new FirebaseDBHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<PharmacyD> orders, List<String> keys) {
                new View_Config().setConfig(mRecycleView,Order_List.this,orders, keys);
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


}