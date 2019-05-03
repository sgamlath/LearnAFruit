package com.example.learnafruit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void loadLearn(View view) {
        Intent learnIntent = new Intent(this, LearnActivity.class);
        startActivity(learnIntent);
    }

    public void loadQuiz(View view) {
        Intent quizIntent = new Intent(this, QuizActivity.class);
        startActivity(quizIntent);
    }
}
