package com.example.loi.trivia;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Asynchronous task to cycle through question objects using the Arraylist of Questions
 */
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


    /**
     *
     * @param textQuestion - Textview Object of the current question
     * @param triviaImage - ImageView object of the stock image
     * @param questionNumber - TextView object of the current question number
     * @param categoryText - TextView object of the category field
     * @param rb1 - Radiobutton object of radio button 1
     * @param rb2 - Radiobutton object of radio button 2
     * @param rb3 - Radiobutton object of radio button 3
     * @param rb4 - Radiobutton object of radio button 4
     * @param c - Context of activity
     */
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
        rb1.setVisibility(View.VISIBLE);
        rb2.setVisibility(View.VISIBLE);
        rb3.setVisibility(View.VISIBLE);
        rb4.setVisibility(View.VISIBLE);



    }

    /**
     *
     * @param aVoid - output from doInBackground if applicable
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        textQuestion.setText(question.getQuestion());
        category.setText(question.getCategory());
        questionNumber.setText("Q" + (QuestionActivity.position + 1));

        ArrayList <String> choices = new ArrayList<>();

        choices.add(question.getChoices(0));
        choices.add(question.getChoices(1));
        choices.add(question.getChoices(2));
        choices.add(question.getAnswer());

        Collections.shuffle(choices);

        rb1.setText(choices.get(0));
        rb2.setText(choices.get(1));
        rb3.setText(choices.get(2));
        rb4.setText(choices.get(3));


        if(question.getCategory().contains("Anime") ){
            triviaImage.setImageResource(R.drawable.anime_category);
            Log.d("Category: ", question.getCategory());
        }
        else if(question.getCategory().contains("Vehicles") ){
            triviaImage.setImageResource(R.drawable.car_category);
            Log.d("Category: ", question.getCategory());
        }
        else if(question.getCategory().contains("History") ){
            triviaImage.setImageResource(R.drawable.history_category);
            Log.d("Category: ", question.getCategory());
        }
        else if(question.getCategory().contains("Sports") ){
            triviaImage.setImageResource(R.drawable.sports_category);
            Log.d("Category: ", question.getCategory());
        }
        else if(question.getCategory().contains("Video Games") ){
            triviaImage.setImageResource(R.drawable.videogames_category);
            Log.d("Category: ", question.getCategory());
        }
        else if(question.getCategory().contains("Computers") ){
            triviaImage.setImageResource(R.drawable.computerscience_category);
            Log.d("Category: ", question.getCategory());
        }


        if(rb1.getText() == ""){
            rb1.setVisibility(View.INVISIBLE);
        }
        if(rb2.getText() == ""){
            rb2.setVisibility(View.INVISIBLE);
        }
        if(rb3.getText() == ""){
            rb3.setVisibility(View.INVISIBLE);
        }
        if(rb4.getText() == ""){
            rb4.setVisibility(View.INVISIBLE);
        }





    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    /**
     *
     * @param questions - Takes current question object and sets it to this.question object in this async task
     * @return
     */
    @Override
    protected Void doInBackground(Question... questions) {

        question = questions[0];

        return null;

    }





}
