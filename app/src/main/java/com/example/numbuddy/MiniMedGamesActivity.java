package com.example.numbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Arrays;
import java.util.Collections;

public class MiniMedGamesActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Button mButton;
    Button show_Btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_med_games);

        mImageView = (ImageView) findViewById(R.id.midProbview);
        mButton = (Button) findViewById(R.id.nxtQuest_Btn2);
        show_Btn2 = (Button) findViewById(R.id.showAns_Btn2);

        mButton.setOnClickListener(v -> showRandomMidQuestions());
        show_Btn2.setOnClickListener(v -> showMedAnswer());

        showRandomMidQuestions();
    }

    public void showRandomMidQuestions(){
        shuffleMidQuestions();

        mImageView.setImageResource(mquestionsArray[0].getmImage());
    }

    MediumQuestions m01 = new MediumQuestions(R.drawable.problem_medium_1);
    MediumQuestions m02 = new MediumQuestions(R.drawable.problem_medium_2);
    MediumQuestions m03 = new MediumQuestions(R.drawable.problem_medium_3);
    MediumQuestions m04 = new MediumQuestions(R.drawable.problem_medium_4);
    MediumQuestions m05 = new MediumQuestions(R.drawable.problem_medium_5);

    MediumQuestions[] mquestionsArray = new MediumQuestions[]{
            m01, m02, m03, m04, m05
    };


    public void shuffleMidQuestions() {
        Collections.shuffle(Arrays.asList(mquestionsArray));
    }

    //Pop Up Window
    public void showMedAnswer(){
        Intent intent = new Intent(this, medPopWin.class);
        startActivity(intent);

    }
}
