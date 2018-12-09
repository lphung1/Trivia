package com.example.loi.trivia;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    public static int position, correctAnswers = 0;
    Question question = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        Button nextButton = findViewById(R.id.nextButton);
        setContentView(R.layout.activity_question);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        setContentView(R.layout.activity_question);
        position = 0;
        correctAnswers = 0;

        if (MainActivity.triviaArrayList.isEmpty()){
            Intent i = new Intent(QuestionActivity.this, MainActivity.class);
            Toast.makeText(QuestionActivity.this, "Error Getting Data", Toast.LENGTH_LONG).show();
            startActivity(i);
        }

        question = MainActivity.triviaArrayList.get(position);

    }//end oncreate

    @Override
    protected void onResume() {
        super.onResume();

        final RadioGroup rg = findViewById(R.id.radioGroup);

        Log.d("button", "next button clicked");
        Log.d("Correct Answer this", "is-" + question.getAnswer());
        final ImageView triviaImage = findViewById(R.id.categoryIV);
        final TextView textQuestion = findViewById(R.id.questionTextView);
        final TextView timeText = findViewById(R.id.timerTV);
        final TextView categoryText = findViewById(R.id.categoryTV);
        final TextView textQuestionNumber = findViewById(R.id.questionNumberText);
        final RadioButton rb1 = findViewById(R.id.rb1);
        final RadioButton rb2 = findViewById(R.id.rb2);
        final RadioButton rb3 = findViewById(R.id.rb3);
        final RadioButton rb4 = findViewById(R.id.rb4);

        final CountDownTimer timer = new CountDownTimer(120000, 1000) {

            @Override
            public void onTick(long l) {
                timeText.setText((l/ 1000) + " seconds left");
            }

            @Override
            public void onFinish() {
                //intent to take to stats screen
                Intent i = new Intent(QuestionActivity.this, ResultsActivity.class);
                startActivity(i);
            }
        }.start();

        ProgressAsyncTask nextQuestion = new ProgressAsyncTask(textQuestion, triviaImage, textQuestionNumber, categoryText, rb1, rb2, rb3, rb4, QuestionActivity.this );
        nextQuestion.execute(MainActivity.triviaArrayList.get(position));

        //Log.d("Selected answer", "" + getAnswer(rb1, rb2, rb3, rb4));
        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       findViewById(R.id.nextButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String checkedAnswer = getAnswer(rb1, rb2, rb3, rb4);
                Log.d("Checked Answer :", checkedAnswer);
                Log.d("Correct Answer :", question.getAnswer());

                if(checkedAnswer == question.getAnswer()){
                    Toast t = Toast.makeText(QuestionActivity.this, "Correct!", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.BOTTOM, 400, 340);
                    t.show();
                    correctAnswers += 1;

                }
                else{
                    Toast t = Toast.makeText(QuestionActivity.this, "Incorrect!", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.BOTTOM, 400, 340);
                    t.show();
                }

                position += 1;
                question = MainActivity.triviaArrayList.get(position);


                ProgressAsyncTask nextQuestion = new ProgressAsyncTask(textQuestion, triviaImage, textQuestionNumber, categoryText, rb1, rb2, rb3, rb4, QuestionActivity.this );

                nextQuestion.execute(question);


                rg.clearCheck();

                if(position == MainActivity.triviaArrayList.size() - 1){
                    Intent i = new Intent(QuestionActivity.this, ResultsActivity.class);
                    startActivity(i);
                }

            }
        });

    }

    private String getAnswer(RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4 ){

        if(rb1.isChecked()){
            return rb1.getText().toString();
        }
        else if(rb2.isChecked()){
            return rb2.getText().toString();
        }
        else if(rb3.isChecked()){
            return rb3.getText().toString();
        }
        else if (rb4.isChecked()){
            return rb4.getText().toString();
        }
        else
            return "-";

    }

    public int getCorrectAnswers(){
        return correctAnswers;
    }

}
