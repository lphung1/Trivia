package com.example.loi.trivia;

import android.app.ProgressDialog;
import android.content.Context;
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

    //constructor
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
        Toast t = Toast.makeText(context, "Loaded", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.BOTTOM, 0 , 180);
        t.show();

        Log.d("Result Arraylist is Empty:", " -" + result.isEmpty() );

        for(int i = 0; MainActivity.triviaArrayList.size() > i; i ++) {

            Log.d("Arraylist Contents: " + i + " ",  MainActivity.triviaArrayList.get(i).getQuestion());

        }


    }

    /**
     * Runs on the UI thread after {@link #publishProgress} is invoked.
     * The specified values are the values passed to {@link #publishProgress}.
     *
     * @param values The values indicating progress.
     * @see #publishProgress
     * @see #doInBackground
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);



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

                for (int i = 0; i < questions.length(); i++) {
                    JSONObject questionsJSONObject = questions.optJSONObject(i);
                    JSONArray choicesJSONObject = questionsJSONObject.optJSONArray("incorrect_answers"); //sub objects

                    Question t = new Question();

                    t.setQuestion(questionsJSONObject.getString("question"));
                    t.setAnswer(questionsJSONObject.getString("correct_answer"));
                    t.setChoices(choicesJSONObject);
                    t.setCategory(questionsJSONObject.getString("category"));

                    /*

                    if(questionsJSONObject.optString("image").startsWith("http"))
                    {
                        t.setImageUrl(questionsJSONObject.optString("image"));
                    }
                    */

                    t.setChoices(choicesJSONObject);

                    Log.d("Question Type ", "" + questionsJSONObject.getString("type"));
                    Log.d("Json Question", "" + questionsJSONObject.getString("question"));
                    Log.d("Json category", "" + questionsJSONObject.getString("category"));
                    Log.d("Json answer", "" + questionsJSONObject.getString("correct_answer"));
                    Log.d("Json choices", "" + choicesJSONObject.getString(0));


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
