package com.example.numbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FormulaActivity extends AppCompatActivity {
    Button geometry_Btn, trigonometry_Btn, algebra_Btn, arithmetic_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula);



        geometry_Btn = findViewById(R.id.Geometry_Btn);
        trigonometry_Btn = findViewById(R.id.Trigonometry_Btn);
        algebra_Btn = findViewById(R.id.Algebra_Btn);
        arithmetic_Btn = findViewById(R.id.Arithmetic_Btn);

        geometry_Btn.setOnClickListener(v -> openGeometry());
        trigonometry_Btn.setOnClickListener(v -> openTrigonometry());

    }

    public void openGeometry(){
        Intent intent = new Intent(this, GeometryActivity.class);
        startActivity(intent);
    }

    public void openTrigonometry(){
        Intent intent = new Intent(this, TrigoActivity.class);
        startActivity(intent);
    }


}

