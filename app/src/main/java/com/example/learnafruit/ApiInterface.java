//https://demonuts.com/retrofit-android-get-json/

package com.example.learnafruit;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * ApiInterface
 *
 * The ApiInterface sets the interface for communication between REST API and application
 */
public interface ApiInterface {

    String JSONURL = "https://nameless-bastion-94748.herokuapp.com/question/";

    /**
     * getFruitList method to get fruit list from the server
     */
    @GET("fruitlist")
    Call<String> getFruitList();

    /**
     * getQuestionList method to get questions list from the server
     */
    @GET("getquestionlist")
    Call<String> getQuestionList();

}
