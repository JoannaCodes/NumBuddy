package com.example.numbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class MiniEasyGamesActivity extends AppCompatActivity {
    private ImageView qImageView;
    private Button qButton;
    Button show_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_easy_games);

        qImageView = (ImageView) findViewById(R.id.easyProbview);
        qButton = (Button)findViewById(R.id.nxtQuest_Btn) ;
        show_btn = (Button) findViewById(R.id.showAns_Btn);


        qButton.setOnClickListener(v -> showRandomEasyQuestion());
        show_btn.setOnClickListener(v -> showAnswer());

        showRandomEasyQuestion();

    }

    public void showRandomEasyQuestion(){
        shuffleEasyQuestions();

        qImageView.setImageResource(questionsArray[0].getqImage());
    }

    EasyQuestions q01 = new EasyQuestions(R.drawable.problem_easy_1);
    EasyQuestions q02 = new EasyQuestions(R.drawable.problem_easy_2);
    EasyQuestions q03 = new EasyQuestions(R.drawable.problem_easy_3);
    EasyQuestions q04 = new EasyQuestions(R.drawable.problem_easy_4);
    EasyQuestions q05 = new EasyQuestions(R.drawable.problem_easy_5);

    EasyQuestions[] questionsArray = new EasyQuestions[]{
            q01, q02, q03, q04, q05
    };

    public void shuffleEasyQuestions() {
        Collections.shuffle(Arrays.asList(questionsArray));
    }
    //Pop Up Window
    public  void showAnswer(){
        Intent intent = new Intent(this, easyPopWin.class);
        startActivity(intent);
    }
}