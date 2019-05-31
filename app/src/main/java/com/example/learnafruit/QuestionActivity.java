package com.example.learnafruit;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * QuestionActivity
 *
 * The activity displaying each question
 */
public class QuestionActivity extends AppCompatActivity {

    private MediaPlayer player;

    public ArrayList<QuestionModel> questionModels;
    int round = 1;
    int score = 0;

    /**
     * Overridden method to initialize Question activity with data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView txtViewRound = (TextView) findViewById(R.id.txtRound);
        TextView txtViewScore = (TextView) findViewById(R.id.txtScore);

        txtViewRound.setText(Integer.toString(round));
        txtViewScore.setText(Integer.toString(score));

        getData();

    }

    /**
     * Method to get question data from the server
     */
    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create((ApiInterface.class));

        Call<String> call = api.getQuestionList();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        String jsonResponse = response.body().toString();
                        questionModels = jsonToModel(jsonResponse);
                        displayQuestion(0);
                    } else {
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    /**
     * Method to display the question
     */
    private void displayQuestion(int qIndex) {
        ImageView imageView = findViewById(R.id.imageView);
        Picasso.get()
                .load("https://nameless-bastion-94748.herokuapp.com"+questionModels.get(qIndex).getImg())
                .placeholder(R.drawable.loading)
                .error(R.drawable.broken)
                .fit()
                .into(imageView);

        Button ans1 = findViewById(R.id.ans1);
        Button ans2 = findViewById(R.id.ans2);
        Button ans3 = findViewById(R.id.ans3);
        Button ans4 = findViewById(R.id.ans4);

        ans1.setText(questionModels.get(qIndex).getAns1());
        ans2.setText(questionModels.get(qIndex).getAns2());
        ans3.setText(questionModels.get(qIndex).getAns3());
        ans4.setText(questionModels.get(qIndex).getAns4());
    }

    /**
     * Method to convert json data to questionModel
     */
    private ArrayList<QuestionModel> jsonToModel(String jsonresponse) {
        ArrayList<QuestionModel> questionModels = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonresponse);

            if(jsonObject.optString("isSuccess").equals("true")){
                JSONArray jsonQuestionList = jsonObject.getJSONArray("questionList");
                for (int i=0;i<jsonQuestionList.length(); i++) {
                    QuestionModel question = new QuestionModel();
                    JSONObject questionObject = jsonQuestionList.getJSONObject(i);
                    question.setCorrectAns(questionObject.getInt("correctAns"));
                    question.setAns1(questionObject.getString("ans1"));
                    question.setAns2(questionObject.getString("ans2"));
                    question.setAns3(questionObject.getString("ans3"));
                    question.setAns4(questionObject.getString("ans4"));
                    question.setImg(questionObject.getString("img"));

                    questionModels.add(question);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questionModels;
    }

    /**
     * Method to handle answer-click-event
     */
    public void ansClick(View view) {
        int correctAns = questionModels.get(round-1).getCorrectAns();
        int input = Integer.parseInt(view.getTag().toString());
        if (input == correctAns) {
            score++;
            TextView txtViewScore = (TextView) findViewById(R.id.txtScore);
            txtViewScore.setText(Integer.toString(score));
        }
        round++;

        if(round>10){
            DBHelper dbHelper = new DBHelper(this);
            String username = dbHelper.readUsername();
            dbHelper.saveScore(username,Integer.toString(score));

            Toast.makeText(this, username + ", You scored " + score + " out of 10", Toast.LENGTH_LONG);
            String msg = username + ", You scored " + score + " out of 10";

            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage(msg);
            dlgAlert.setTitle("Quiz Result");
            dlgAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dlgAlert.setCancelable(false);
            dlgAlert.create().show();

            int toneRes = R.raw.applause;
            if (player != null && player.isPlaying()) {
                player.stop();
            }
            player = MediaPlayer.create(QuestionActivity.this, toneRes);
            player.setLooping(false);

            player.start();


        }else {

            TextView txtViewRound = (TextView) findViewById(R.id.txtRound);
            txtViewRound.setText(Integer.toString(round));
            displayQuestion(round-1);
        }
    }
}
