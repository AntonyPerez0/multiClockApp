package com.multiclock.app;

import static org.junit.Assert.assertNotNull;

import com.codecademy.myandroiddiary.api.WeatherApiService;
import com.codecademy.myandroiddiary.models.WeatherResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiServiceTest {

    private MockWebServer mockWebServer;
    private WeatherApiService apiService;

    @Before
    public void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        apiService = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService.class);
    }

    @After
    public void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    public void testGetCurrentWeather() throws IOException {
        String mockResponse = "{ \"name\": \"San Jose\", \"main\": { \"temp\": 20.0 } }";
        mockWebServer.enqueue(new MockResponse().setBody(mockResponse));

        Call<WeatherResponse> call = apiService.getCurrentWeather("San Jose", "fakeApiKey", "metric");
        Response<WeatherResponse> response = call.execute();

        assertNotNull(response.body());
        assertEquals("San Jose", response.body().getCityName());
        assertEquals(20.0, response.body().getMain().getTemp(), 0.0);
    }
}