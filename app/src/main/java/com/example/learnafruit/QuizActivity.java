package com.example.learnafruit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

//        final EditText txtName = (EditText) findViewById(R.id.txtName);
//
//        txtName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    Log.i("TEST", "Lost focus - "+txtName.getText());
//                    //TODO: Save username to db
//                }
//            }
//        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        EditText txtName = (EditText) findViewById(R.id.txtName);
        Log.i("TEST", "Stoped - "+txtName.getText());
    }

    public void changeName(View view) {
    }

    public void loadQuestion(View view) {
        Intent questionIntent = new Intent(this, QuestionActivity.class);
        startActivity(questionIntent);
    }

    public void loadScorecard(View view) {
        Intent scorecardIntent = new Intent(this, ScorecardActivity.class);
        startActivity(scorecardIntent);
    }
}
