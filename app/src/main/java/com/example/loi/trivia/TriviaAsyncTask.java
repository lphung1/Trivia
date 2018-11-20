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

        this.button = button;


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
     * @param result The result of the operation computed by {@link #doInBackground}.
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
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
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
                    JSONObject choicesJSONObject = questionsJSONObject.optJSONObject("incorrect_answers"); //sub objects

                    Question t = new Question();

                    t.setId(questionsJSONObject.optString("type"));
                    t.setText(questionsJSONObject.optString("question"));
                    t.setText(questionsJSONObject.optString("question"));
                    t.setAnswer(choicesJSONObject.optString("correct_answer"));
                    if(questionsJSONObject.optString("image").startsWith("http"))
                    {
                        t.setImageUrl(questionsJSONObject.optString("image"));
                    }
                    t.setQuestions(choicesJSONObject.getJSONArray("choice"));

                    Log.d("JsonName", "" + questionsJSONObject.getString("type"));
                    Log.d("Jsonid", "" + questionsJSONObject.getString("question"));
                    Log.d("JsonChoice", "" + choicesJSONObject.getString("correct_answer"));
                    Log.d("Answer", "" + choicesJSONObject.getString("answer"));


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
