package com.example.loi.trivia;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ProgressAsyncTask extends AsyncTask<Question, Void, Void> {

    ImageView triviaImage;
    TextView textQuestion;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;
    Question question;
    TextView questionNumber;
    Context context;
    TextView category;


    public ProgressAsyncTask(TextView textQuestion, ImageView triviaImage, TextView questionNumber, TextView categoryText, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4, Context c) {
        super();

        this.triviaImage = triviaImage;
        this.textQuestion = textQuestion;
        this.rb1 = rb1;
        this.rb2 = rb2;
        this.rb3 = rb3;
        this.rb4 = rb4;
        this.questionNumber = questionNumber;
        context = c;
        this.category = categoryText;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        textQuestion.setText(question.getQuestion());
        category.setText(question.getCategory());
        questionNumber.setText("Q" + (QuestionActivity.position + 1));
        rb1.setText(question.getChoices(0));
        rb2.setText(question.getChoices(1));
        rb3.setText(question.getChoices(2));
        rb4.setText(question.getAnswer());



    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Void doInBackground(Question... questions) {

        question = questions[0];

        return null;

    }
}
