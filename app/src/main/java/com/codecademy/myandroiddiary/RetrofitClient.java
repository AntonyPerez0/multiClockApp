package com.multiclock.app.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// Singleton class to provide Retrofit instance
public class RetrofitClient {

    // Base URL for the Weather API
    private static final String BASE_URL = "https://api.openweathermap.org/";

    private static Retrofit retrofit = null;

    // Method to get the Retrofit instance
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}