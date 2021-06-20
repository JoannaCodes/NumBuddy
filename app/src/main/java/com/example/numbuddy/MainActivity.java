package com.example.numbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button UniCon_Btn, MinGme_Btn, Frmla_Btn ;

        UniCon_Btn = findViewById(R.id.uniCon_Btn);
        MinGme_Btn = findViewById(R.id.minGme_Btn);
        Frmla_Btn = findViewById(R.id.formula_Btn);




        //transition from main screen to unit conversion screen -- lambda function
        UniCon_Btn.setOnClickListener(v -> openUnitConversion());
        MinGme_Btn.setOnClickListener(v -> openMiniGames());
        Frmla_Btn.setOnClickListener(v -> openFormula());

    }

    //activity trigger
    public void openUnitConversion() {
        Intent intent = new Intent(this, UnitConverterActivity.class);
        startActivity(intent);
    }

    public void openMiniGames() {
        Intent intent = new Intent( this, MiniGamesActivity.class);
        startActivity(intent);
    }

    public void openFormula() {
        Intent intent = new Intent( this, FormulaActivity.class);
        startActivity(intent);
    }
}