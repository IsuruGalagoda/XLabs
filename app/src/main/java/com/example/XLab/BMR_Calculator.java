package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMR_Calculator extends AppCompatActivity {
    Button Wcal;
    Button Mcal;
    EditText Mh;
    EditText Mw;
    EditText Mage;
    EditText Wh;
    EditText Ww;
    EditText Wage;
    TextView finM;
    TextView finW;
    String MenCal,WomenCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmr_calculator);


        Wcal=findViewById(R.id.Wcalcbtn);
        Mcal=findViewById(R.id.Mcalcbtn);
        Mh=findViewById(R.id.mheight);
        Mw=findViewById(R.id.mweight);
        Mage=findViewById(R.id.mage);
        Wh=findViewById(R.id.wheight);
        Ww=findViewById(R.id.wweight);
        Wage=findViewById(R.id.wage);
        finM=findViewById(R.id.Mvalue);
        finW=findViewById(R.id.Wvalue);

        Mcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String menheight=Mh.getText().toString();
                String menweight=Mw.getText().toString();
                String menage=Mage.getText().toString();
                float Mheight=Float.parseFloat(menheight);
                float Mweight=Float.parseFloat(menweight);
                float Mage=Float.parseFloat(menage);
                float BMRmen;
                BMRmen=calMenBMR(Mheight,Mweight,Mage);
                finM.setText(String.valueOf(BMRmen));

            }
        });
        Wcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String womenheight=Mh.getText().toString();
                String womenweight=Mw.getText().toString();
                String womenage=Mage.getText().toString();
                float Wheight=Float.parseFloat(womenheight);
                float Wweight=Float.parseFloat(womenweight);
                float Wage=Float.parseFloat(womenage);
                float BMRwomen= (float) (651.1+(9.563 * Wweight)+(1.85 * Wheight)-(4.676 * Wage));
                finW.setText(String.valueOf(BMRwomen));
            }
        });
    }
    protected Float calMenBMR(Float Mheight,Float Mweight,Float Mage)
    {   float value=0;
        value= (float) (66.47+(13.75 * Mweight)+(5.003 * Mheight)-(6.77 * Mage));

        return value;
    }


}