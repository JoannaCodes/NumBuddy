package com.example.numbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MiniGamesActivity extends AppCompatActivity {
    Button MiniEasy_Btn, MiniMid_Btn, MiniHard_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_games);

        MiniEasy_Btn = findViewById(R.id.miniEasy_Btn);
        MiniMid_Btn = findViewById(R.id.miniMid_Btn);
        MiniHard_Btn = findViewById(R.id.miniHard_Btn);

        MiniEasy_Btn.setOnClickListener(v -> openEasyGame());
        MiniMid_Btn.setOnClickListener(v -> openMediumGame());

    }

    public void openEasyGame() {
        Intent intent;
        intent = new Intent(this, MiniEasyGamesActivity.class);
        startActivity(intent);
    }

    public void openMediumGame() {
        Intent intent = new Intent(this, MiniMedGamesActivity.class);
        startActivity(intent);
    }
}