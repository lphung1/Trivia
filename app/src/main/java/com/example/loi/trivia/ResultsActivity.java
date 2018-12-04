package com.example.loi.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        TextView percentageCorrect = findViewById(R.id.percentageCorrectTV);
        Button playAgain = findViewById(R.id.playAgainButton);

        percentageCorrect.setText("" + QuestionActivity.correctAnswers + "/" + MainActivity.quizLength);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}
