package com.example.learnafruit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ScorecardActivity
 *
 * The activity displaying list of scores
 */
public class ScorecardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);
    }

    /**
     * Method overridden to refresh score list each time the activity resumes
     */
    @Override
    protected void onResume() {
        super.onResume();

        refreshList();
    }

    /**
     * Method to load score list
     */
    private void refreshList() {
        DBHelper dbHelper = new DBHelper(this);

        List<ScoreModel> scoreModelList = dbHelper.readAllScores();
        List<String> scoreList = new ArrayList<>();
        for (ScoreModel scoreModel : scoreModelList) {
            scoreList.add(scoreModel.getName() + "         " + scoreModel.getScore());
        }
        //TODO: Modify score_item.xml as required (Name - left aligned | Score - right aligned)
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.score_item, scoreList);

        ListView listView = findViewById(R.id.scoreList);
        listView.setAdapter(adapter);
    }

    /**
     * Method to clear score list
     */
    public void clearScoreList(View view) {
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.deleteAllScores();
        refreshList();
    }
}
