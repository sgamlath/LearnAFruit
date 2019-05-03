package com.example.learnafruit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LearnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
    }

    public void loadFruit(View view) {
        Intent fruitIntent = new Intent(this, FruitActivity.class);
        startActivity(fruitIntent);
    }
}
