package com.example.learnafruit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * MainActivity
 *
 * The main activity of the app
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_app_icon);

    }

    /**
     * Method to navigate to Learn Activity
     */
    public void loadLearn(View view) {
        Intent learnIntent = new Intent(this, LearnActivity.class);
        startActivity(learnIntent);
    }

    /**
     * Method to navigate to Quiz Activity
     */
    public void loadQuiz(View view) {
        Intent quizIntent = new Intent(this, QuizActivity.class);
        startActivity(quizIntent);
    }
}
