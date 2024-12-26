package com.multiclock.app;

import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

    @SerializedName("name")
    private String cityName;

    @SerializedName("main")
    private Main main;

    public String getCityName() {
        return cityName;
    }

    public Main getMain() {
        return main;
    }

    public static class Main {

        @SerializedName("temp")
        private float temp;

        public float getTemp() {
            return temp;
        }
    }
}