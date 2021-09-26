package com.example.XLab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthCal extends AppCompatActivity {

    private Button btnBMI,btnBMR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_cal);

        btnBMI = (Button)findViewById(R.id.btnCalBMI);
        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBMIcalculation();
            }
        });
        btnBMR=findViewById(R.id.btnBMR);
        btnBMR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBMRcalculattion();
            }
        });
    }

    public void openBMIcalculation(){
        Intent intent = new Intent(this, BMI_calculator.class);
        startActivity(intent);
    }
    public void openBMRcalculattion(){
        Intent intent = new Intent(this, BMR_Calculator.class);
        startActivity(intent);
    }
}