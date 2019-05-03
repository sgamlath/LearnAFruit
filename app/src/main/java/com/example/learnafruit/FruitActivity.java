package com.example.learnafruit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class FruitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        ImageView imageView = findViewById(R.id.image_fruit);

        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f4/Honeycrisp.jpg/220px-Honeycrisp.jpg";

        Picasso.get().load(url).into(imageView);

    }
}
