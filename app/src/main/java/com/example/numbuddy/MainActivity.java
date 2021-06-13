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

        Button UniCon_Btn, MinGme_Btn;

        UniCon_Btn = findViewById(R.id.uniCon_Btn);
        MinGme_Btn = findViewById(R.id.minGme_Btn);

        //transition from main screen to unit conversion screen -- lambda function
        UniCon_Btn.setOnClickListener(v -> openUnitConversion());
        MinGme_Btn.setOnClickListener(v -> openMiniGames());

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
}