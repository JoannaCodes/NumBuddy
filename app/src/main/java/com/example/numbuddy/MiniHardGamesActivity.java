package com.example.numbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class MiniHardGamesActivity extends AppCompatActivity {
    private ImageView hImageView;
    private Button hButton;
    Button showHardQ_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_hard_games);

        hImageView = (ImageView) findViewById(R.id.hardprobView);
        hButton = (Button) findViewById((R.id.nxtQuest_Btn3));
        showHardQ_Btn = (Button) findViewById(R.id.showAns_Btn3);

        hButton.setOnClickListener( v -> showRandomHardQuestions());
        showHardQ_Btn.setOnClickListener(v -> showHardAnswer());

        showRandomHardQuestions();
    }

    public void showRandomHardQuestions(){
        shuffleHrdQuestions();

        hImageView.setImageResource(hquestionsArray[0].gethImage());
    }

    HardQuestions h01 = new HardQuestions(R.drawable.problem_hard_1);
    HardQuestions h02 = new HardQuestions(R.drawable.problem_hard_2);
    HardQuestions h03 = new HardQuestions(R.drawable.problem_hard_3);
    HardQuestions h04 = new HardQuestions(R.drawable.problem_hard_4);
    HardQuestions h05 = new HardQuestions(R.drawable.problem_hard_5);

    HardQuestions[] hquestionsArray = new HardQuestions[]{
            h01, h02, h03, h04, h05
    };

    public void shuffleHrdQuestions() {
        Collections.shuffle(Arrays.asList(hquestionsArray));
    }

    //Pop Up Window
    public void showHardAnswer(){
        Intent intent = new Intent(this, hardPopWin.class);
        startActivity(intent);

    }
}