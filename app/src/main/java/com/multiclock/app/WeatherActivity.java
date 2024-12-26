package com.multiclock.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {

    private EditText cityEditText;
    private TextView weatherTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        cityEditText = findViewById(R.id.cityEditText);
        Button fetchWeatherButton = findViewById(R.id.fetchWeatherButton);
        weatherTextView = findViewById(R.id.weatherTextView);
        Button backButton = findViewById(R.id.backButton);

        fetchWeatherButton.setOnClickListener(view -> {
            String cityName = cityEditText.getText().toString().trim();
            if (!cityName.isEmpty()) {
                fetchWeatherData(cityName);
            } else {
                weatherTextView.setText("Please enter a city name.");
            }
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(WeatherActivity.this, com.multiclock.app.MainActivity.class);
            startActivity(intent);
        });
    }

    private void fetchWeatherData(String cityName) {
        String apiKey = BuildConfig.WEATHER_API_KEY;
        WeatherApiService apiService = RetrofitClient.getClient().create(WeatherApiService.class);
        Call<WeatherResponse> call = apiService.getCurrentWeather(cityName, apiKey, "metric");

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        WeatherResponse weatherResponse = response.body();
                        String city = weatherResponse.getCityName();
                        float temperatureC = weatherResponse.getMain().getTemp();
                        float temperatureF = (temperatureC * 9 / 5) + 32;
                        String weatherInfo = "City: " + city + "\nTemperature: " + temperatureF + "°F";
                        weatherTextView.setText(weatherInfo);
                        Log.d("WeatherInfo", weatherInfo);
                    } else {
                        String errorBody = response.errorBody().string();
                        weatherTextView.setText("Failed to retrieve weather data.");
                        Log.e("WeatherError", "Response unsuccessful: " + errorBody);
                    }
                } catch (IOException e) {
                    Log.e("WeatherError", "Error reading error body", e);
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                weatherTextView.setText("Network request failed.");
                Log.e("NetworkFailure", "Network Call Failed: " + t.getMessage());
            }
        });
    }
}