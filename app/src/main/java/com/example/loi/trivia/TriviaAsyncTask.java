package com.example.loi.trivia;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
     * @param questions The result of the operation computed by {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(ArrayList<Question> questions) {
        super.onPostExecute(questions);
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

                JSONArray questions = root.optJSONArray("questions"); // root object

                for (int i = 0; i < questions.length(); i++) {
                    JSONObject questionsJSONObject = questions.optJSONObject(i);
                    JSONObject choicesJSONObject = questionsJSONObject.optJSONObject("choices"); //sub objects

                    Question t = new Question();

                    t.setId(questionsJSONObject.optString("id"));
                    t.setText(questionsJSONObject.optString("text"));
                    t.setText(questionsJSONObject.optString("text"));
                    t.setAnswer(choicesJSONObject.optString("answer"));
                    if(questionsJSONObject.optString("image").startsWith("http"))
                    {
                        t.setImageUrl(questionsJSONObject.optString("image"));
                    }
                    t.setQuestions(choicesJSONObject.getJSONArray("choice"));

                    Log.d("JsonName", "" + questionsJSONObject.getString("text"));
                    Log.d("Jsonid", "" + questionsJSONObject.getString("id"));
                    Log.d("JsonChoice", "" + choicesJSONObject.getString("choice"));
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
