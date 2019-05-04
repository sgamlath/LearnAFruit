//https://demonuts.com/retrofit-android-get-json/

package com.example.learnafruit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * LearnActivity
 *
 * The activity displaying list of fruits
 */
public class LearnActivity extends AppCompatActivity {
    public ArrayList<FruitModel> fruitModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        getData();

    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiInterface api = retrofit.create((ApiInterface.class));

        Call<String> call = api.getString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("TEST", response.body().toString());

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("TEST", response.body().toString());
                        String jsonResponse = response.body().toString();

                        fruitModels = jsonToModel(jsonResponse);

                        modelToListView(fruitModels);

                    } else {
                        Log.i("TEST", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void modelToListView(ArrayList<FruitModel> modelList) {
        CustomAdaptor customAdaptor = new CustomAdaptor(this, modelList);

        ListView fruitListView = findViewById(R.id.fruitListView);
        fruitListView.setAdapter(customAdaptor);
    }

    private ArrayList<FruitModel> jsonToModel(String jsonresponse) {
        ArrayList<FruitModel> fruitModels = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonresponse);

            if(jsonObject.optString("isSuccess").equals("true")){
                JSONArray jsonQuestionList = jsonObject.getJSONArray("questionList");
                for (int i=0;i<jsonQuestionList.length(); i++) {
                    FruitModel fruit = new FruitModel();
                    JSONObject fruitObject = jsonQuestionList.getJSONObject(i);
                    fruit.setId(fruitObject.getInt("id"));
                    fruit.setName(fruitObject.getString("name"));
                    fruit.setImg_path(fruitObject.getString("img_path"));
                    fruit.setDescription(fruitObject.getString("description"));

                    fruitModels.add(fruit);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fruitModels;
    }


    public void loadFruit(View view) {
        Intent fruitIntent = new Intent(this, FruitActivity.class);
        fruitIntent.putExtra("fruit", fruitModels.get(Integer.parseInt(view.getTag().toString())-1));
        Log.i("TEST", "Hi bae: " + view.getTag());
        startActivity(fruitIntent);
    }


}

