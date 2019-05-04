//https://demonuts.com/retrofit-android-get-json/

package com.example.learnafruit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    String JSONURL = "https://damp-sea-11322.herokuapp.com/question/";

    @GET("questionlist")
    Call<String> getString();

}
