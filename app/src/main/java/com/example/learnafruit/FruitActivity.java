package com.example.learnafruit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * FruitActivity
 *
 * The activity displaying each fruit
 */
public class FruitActivity extends AppCompatActivity {
    private FruitModel fruit;

    /**
     * Overridden method to initialize Fruit activity with data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        fruit = (FruitModel) getIntent().getSerializableExtra("fruit");

        TextView fruitName = findViewById(R.id.txtFruitName);
        fruitName.setText(fruit.getName());

        TextView description = findViewById(R.id.fruitDescription);
        description.setText(fruit.getDescription());

        ImageView imageView = findViewById(R.id.image_fruit);
        Picasso.get()
                .load("https://nameless-bastion-94748.herokuapp.com"+fruit.getImg_path())
                .placeholder(R.drawable.loading)
                .error(R.drawable.broken)
                .fit()
                .into(imageView);

    }
}
