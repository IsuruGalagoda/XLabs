package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMI_calculator extends AppCompatActivity {

     Button btnCalc;
     EditText Height;
     EditText Weight;

    TextView resultBmi,BMIvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculate);

        btnCalc = (Button)findViewById(R.id.btnCalc);
        Height = (EditText)findViewById(R.id.edtTxtHgt);
        Weight = (EditText)findViewById(R.id.edtTxtWeight);
        resultBmi = (TextView)findViewById(R.id.textViewBMI);
        BMIvalue = (TextView)findViewById(R.id.textViewBMIValue);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Float weight,height;
                Float result = calcBMI( Float.parseFloat(Weight.getText().toString()),Float.parseFloat(Height.getText().toString()));
                resultBmi.setText(String.valueOf(result));
            }


        });
    }

    //BMI calculate
    protected float calcBMI(Float weight, Float height){


        float result = weight/(height * height);


        if(result>=25.0){
            BMIvalue.setText("Overweight");
        }
        else if(result<18.0) {
            BMIvalue.setText("...Thin...");
        }
        else{
          BMIvalue.setText("Healthy Weight");
        }
        return result;
    }


}