package com.example.loi.trivia;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {

    public static int position, correctAnswers = 0;
    Question question = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_question);
        setTitle("Trivia Time");
        position = 0;
        correctAnswers = 0;
        final ImageView triviaImage = findViewById(R.id.categoryIV);
        final TextView textQuestion = findViewById(R.id.questionTextView);
        final TextView timeText = findViewById(R.id.timerTV);
        final TextView categoryText = findViewById(R.id.categoryTV);
        final TextView textQuestionNumber = findViewById(R.id.questionNumberText);
        final RadioButton rb1 = findViewById(R.id.rb1);
        final RadioButton rb2 = findViewById(R.id.rb2);
        final RadioButton rb3 = findViewById(R.id.rb3);
        final RadioButton rb4 = findViewById(R.id.rb4);

        question = MainActivity.triviaArrayList.get(position);

        ProgressAsyncTask nextQuestion = new ProgressAsyncTask(textQuestion, triviaImage, textQuestionNumber, categoryText, rb1, rb2, rb3, rb4, QuestionActivity.this );
        nextQuestion.execute(MainActivity.triviaArrayList.get(position));

    }


}
