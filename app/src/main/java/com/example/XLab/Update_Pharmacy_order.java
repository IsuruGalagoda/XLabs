package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Update_Pharmacy_order extends AppCompatActivity {

    private EditText Number;
    private EditText name;
    private EditText Addres;
    private EditText phno;
    private TextView date;

    public Button update;
    public Button delete;
    public Button back;

    private String key;
    private String Pnumber;
    private String Pname;
    private String Address;
    private String Phone;
    private String Date;

    //date picker
    private static final String Tag = "Update_pharmacy_order";
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pharmacy_order);

        key = getIntent().getStringExtra("key");

        Pnumber = getIntent().getStringExtra("Pnumber");
        Pname = getIntent().getStringExtra("Pname");
        Address = getIntent().getStringExtra("Address");
        Phone = getIntent().getStringExtra("Phonenum");
        Date = getIntent().getStringExtra("Date");

        Number=(EditText) findViewById(R.id.no);
        Number.setText(Pnumber);
        name=(EditText) findViewById(R.id.name);
        name.setText(Pname);
        Addres= findViewById(R.id.address);
        Addres.setText(Address);
        phno=(EditText) findViewById(R.id.num);
        phno.setText(Phone);
        date=(TextView) findViewById(R.id.date);
        date.setText(Date);


        update = (Button)findViewById(R.id.updaebtn);
        delete = (Button)findViewById(R.id.dltbtn);
        back = (Button)findViewById(R.id.backbtn);

        //UPDATE
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PharmacyD order = new PharmacyD();

                order.setPno(Number.getText().toString());
                order.setPname(name.getText().toString());
                order.setAddress(Addres.getText().toString());
                order.setPhno(Integer.parseInt(phno.getText().toString()));
                order.setDate(date.getText().toString());

                new FirebaseDBHelper().updateOrder(key, order, new FirebaseDBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<PharmacyD> orders, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {
                        Toast.makeText(Update_Pharmacy_order.this,"Details Updated",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });

                //openActivity();
            }
        });

        //DELETE
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FirebaseDBHelper().deleteOrder(key, new FirebaseDBHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<PharmacyD> orders, List<String> keys) {

                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(Update_Pharmacy_order.this,"Order Deleted",Toast.LENGTH_LONG).show();
                        finish(); return;
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();return;
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal= Calendar.getInstance();
                int year= cal.get(Calendar. YEAR);
                int month= cal.get(Calendar. MONTH);
                int day= cal.get(Calendar. DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Update_Pharmacy_order.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.show();

            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                Log.d(Tag,"onDateSet: date:" + i + "/" +i1+ "/" +i2);
                String daten= i + "/" +i1+ "/" +i2 ;
                date.setText(daten);
            }
        };

    }



    public void openActivity() {

        Intent intent = new Intent(this, Order_List.class);
        startActivity(intent);
    }
}