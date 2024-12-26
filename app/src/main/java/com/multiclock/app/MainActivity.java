package com.multiclock.app;

import android.content.Intent;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button navigateButton = findViewById(R.id.navigateButton);
        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            startActivity(intent);
        });
    }

    @RequiresApi(api = VERSION_CODES.O)
    public void handleText(View v) {
        EditText t = findViewById(R.id.source);
        String input = t.getText().toString().toUpperCase();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
            LocalTime time = LocalTime.parse(input, formatter);
            LocalTime newTime = time.plusHours(8).plusMinutes(30);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("h:mm a");
            String formattedTime = newTime.format(outputFormatter);
            ((TextView) findViewById(R.id.output)).setText(formattedTime);
            Log.d("info,", input);
        } catch (DateTimeParseException e) {
            ((TextView) findViewById(R.id.output)).setText("Invalid time format");
            Log.e("error", "Invalid time format: " + input, e);
        }
    }

    @RequiresApi(api = VERSION_CODES.O)
    public void lunchTime(View v) {
        LocalTime now = LocalTime.now();
        DateTimeFormatter lunchFormatter = DateTimeFormatter.ofPattern("h:mm a");
        String formattedNow = now.format(lunchFormatter);
        LocalTime timeLunch = LocalTime.parse(formattedNow, lunchFormatter);
        LocalTime newTime = timeLunch.plusMinutes(30);
        String formattedTime = newTime.format(lunchFormatter);
        ((TextView) findViewById(R.id.outputLunch)).setText(formattedTime);
    }
}