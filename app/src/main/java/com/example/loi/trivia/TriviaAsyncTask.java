package com.example.loi.trivia;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Asynchronous task to parse Trivia API and grab Json data from their database.
 * Question type and number of questions depends on user input.
 */
public class TriviaAsyncTask extends AsyncTask<String, Integer, ArrayList<Question>> {

    /**
     * Runs on the UI thread before {@link #doInBackground}.
     *
     * @see #onPostExecute
     * @see #doInBackground
     *
     */

    ProgressDialog dialog;
    TextView textView;
    Button button;
    ImageView imageView;
    Context context;

    /**
     * Default constructor for async task
     * @param context - context of activity
     */
    public TriviaAsyncTask(Context context) {


        this.context = context;

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     *
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param result Arraylist result from API parsing.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(ArrayList<Question> result) {
        super.onPostExecute(result);
        MainActivity.triviaArrayList = result;


        Log.d("tr", "Results array is empty : " + result.isEmpty() );

        for(int i = 0; MainActivity.triviaArrayList.size() > i; i ++) {

            Log.d("trv" + i + " ",  MainActivity.triviaArrayList.get(i).getQuestion());

        }

        Intent i = new Intent(context, QuestionActivity.class);
        context.startActivity(i);


    }


    /**
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params html url to trivia API.
     * @return result, an arraylist containing many question objects, each holding question data from api.
     */
    @Override
    protected ArrayList<Question> doInBackground(String... params) {


        HttpURLConnection connection = null;
        ArrayList<Question> result = new ArrayList<Question>();

        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = IOUtils.toString(connection.getInputStream(), "UTF8");


                JSONObject root = new JSONObject(json);

                JSONArray questions = root.optJSONArray("results"); // root object
                Log.d("tr", "question length : " + questions.length());

                for (int i = 0; i < questions.length(); i++) {
                    JSONObject questionsJSONObject = questions.optJSONObject(i);
                    JSONArray choicesJSONObject = questionsJSONObject.optJSONArray("incorrect_answers"); //sub objects

                    Question t = new Question();

                    t.setQuestion(questionsJSONObject.getString("question"));
                    t.setAnswer(questionsJSONObject.getString("correct_answer"));
                    t.setChoices(choicesJSONObject);
                    t.setCategory(questionsJSONObject.getString("category"));


                    t.setChoices(choicesJSONObject);

                    Log.d("tr", "" + questionsJSONObject.getString("type"));
                    Log.d("tr", "" + questionsJSONObject.getString("question"));
                    Log.d("tr", "" + questionsJSONObject.getString("category"));
                    Log.d("tr", "" + questionsJSONObject.getString("correct_answer"));
                    Log.d("tr", "" + choicesJSONObject.getString(0));


                    result.add(t);

                }
            }
        } catch (Exception e) {
            //Handle Exceptions
        } finally {
            //Close the connections
        }
        return result;
    }
}
