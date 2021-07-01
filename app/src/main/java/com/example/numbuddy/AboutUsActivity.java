package com.example.numbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class AboutUsActivity extends AppCompatActivity {
    TextView textView;
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        textView = findViewById(R.id.developers);
        homeButton = findViewById(R.id.home_button);

        textView.setText("Developers\n\nChristine Mae Arendain\nJoanna Marie Lara\nQueennie Marie Llido");
        homeButton.setOnClickListener(v -> openMainActivity());
    }

    private void openMainActivity() {
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
    }
}