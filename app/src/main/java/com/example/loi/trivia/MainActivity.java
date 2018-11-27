package com.example.loi.trivia;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Question> triviaArrayList = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView start = findViewById(R.id.imageView7);




        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate = AnimationUtils.loadAnimation(MainActivity.this , R.anim.rotate );
                Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this , R.anim.fadeout );
                start.startAnimation(rotate);

                if( isConnected() ){
                    new TriviaAsyncTask(MainActivity.this).execute("https://opentdb.com/api.php?amount=20&category=31&difficulty=easy");
                }
                else{
                    Toast.makeText(MainActivity.this, "Not connected to internet", Toast.LENGTH_LONG).show();
                }

                Log.d("ArrayList Is empty", "-" + triviaArrayList.isEmpty());


            }
        });



    }

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


}
