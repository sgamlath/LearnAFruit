package com.example.learnafruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * QuizActivity
 *
 * The activity displaying the quiz menu
 */
public class QuizActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
    }

    /**
     * Method overridden to update username
     */
    @Override
    protected void onStop() {
        super.onStop();

        EditText txtName = (EditText) findViewById(R.id.txtName);
        String username = txtName.getText().toString();
        Log.i("TEST", "Stoped - " + username);

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.changeUsername(username);
    }

    /**
     * Method overridden to retrieve username
     */
    @Override
    protected void onResume() {
        super.onResume();

        EditText txtName = (EditText) findViewById(R.id.txtName);

        DBHelper dbHelper = new DBHelper(this);
        String username = dbHelper.readUsername();

        txtName.setText(username);
    }

    /**
     * Method to navigate to Question Activity
     */
    public void loadQuestion(View view) {
        Intent questionIntent = new Intent(this, QuestionActivity.class);
        startActivity(questionIntent);
    }

    /**
     * Method to navigate to Scorecard Activity
     */
    public void loadScorecard(View view) {
        Intent scorecardIntent = new Intent(this, ScorecardActivity.class);
        startActivity(scorecardIntent);
    }
}
