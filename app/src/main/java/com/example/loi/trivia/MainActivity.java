package com.example.loi.trivia;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

/**
 * Home screen of App. This is where user will be able to select category type and number of questions.
 * Once confirmed, TriviaAsync will execute and fetch data from trivia database.
 */
public class MainActivity extends AppCompatActivity {

    public static ArrayList<Question> triviaArrayList = new ArrayList<Question>();
    public static int quizLength = 20;

    private ImageView start;
    private Spinner category;
    private Spinner difficulty;
    private Spinner questions;

    private String api = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("a1", "OnCreate Started");
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        category = findViewById(R.id.spinner_category);
        difficulty = findViewById(R.id.spinner_difficulty);
        questions = findViewById(R.id.spinner_questions);

        start = findViewById(R.id.imageView7);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cat = "&category=" + convertToNum(category.getSelectedItem().toString());
                Log.d("a1", "category : " + cat);
                String diff = "&difficulty=" + difficulty.getSelectedItem().toString().toLowerCase();
                String ques = "amount=" + questions.getSelectedItem().toString();

                api = "https://opentdb.com/api.php?" + ques + cat + diff;

                Log.d("a1", "api : " + api);

                if( isConnected() ){
                    new TriviaAsyncTask(MainActivity.this).execute(api);
                }
                else{
                    Toast.makeText(MainActivity.this, "Not connected to internet", Toast.LENGTH_LONG).show();
                }

                Log.d("ArrayList Is empty", "-" + triviaArrayList.isEmpty());
                Animation rotate = AnimationUtils.loadAnimation(MainActivity.this , R.anim.rotate );
                Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this , R.anim.fadeout );
                start.startAnimation(rotate);


            }
        });

    }//end on create


    /**
     * Class to check if device is connected to internet. Returns true or false
     * @return
     */
    private boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    } //end isConnected method

    /**
     * Converts category to number representation of the category to be used in API
     * @param param - String of category
     * @return - int - number representing category to use in API
     */
    private String convertToNum(String param)
    {
        Log.d("a1", "convertToNum hit with param : " + param);
        String temp = "";
        switch (param)
        {
            case "Computer Science" :
                temp = "18";
                break;

            case "Film" :
                temp = "11";
                break;

            case "Sports" :
                temp = "21";
                break;

            case "History" :
                temp = "23";
                break;

            case "Video Games" :
                temp = "15";
                break;

            case "Anime/Manga" :
                temp = "31";
                break;

            case "Celebrities" :
                temp = "26";
                break;

            case "Vehicles" :
                temp = "28";
                break;

            default :
                temp = "18";
        }

        Log.d("a1", "convertToNum done with temp : " + temp);
        return temp;
    }


}
