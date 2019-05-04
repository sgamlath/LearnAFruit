package com.example.learnafruit;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * QuestionActivity
 *
 * The activity displaying each question
 */
public class QuestionActivity extends AppCompatActivity {

    int round = 1;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        TextView txtViewRound = (TextView) findViewById(R.id.txtRound);
        TextView txtViewScore = (TextView) findViewById(R.id.txtScore);

        txtViewRound.setText(Integer.toString(round));
        txtViewScore.setText(Integer.toString(score));

    }

    public void ansClick(View view) {


        //test code start
        int randChoise = new Random().nextInt(4)+1;
        //TODO: Get the correct choice

        int input = Integer.parseInt(view.getTag().toString());
        if (input == randChoise) {
            score++;
        }
        round++;

        if(round>10){
            //TODO: Display a result message

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
                    //dismiss the dialog
                    finish();
                }
            });
            dlgAlert.setCancelable(false);
            dlgAlert.create().show();


        }else {

            TextView txtViewRound = (TextView) findViewById(R.id.txtRound);
            TextView txtViewScore = (TextView) findViewById(R.id.txtScore);

            txtViewRound.setText(Integer.toString(round));
            txtViewScore.setText(Integer.toString(score));

            //TODO: Update image and 4 choices
        }


        //test code end

        Log.i("TEST", "clicked on - " + view.getTag());
    }
}
