package com.example.iti.gradproject.models.domain.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static final String LOG_TAG = RetrofitClient.class.getSimpleName();
    private static final String BASE_URL = "http://41.41.99.145:8080/api/";
    private static Retrofit retrofit;

    synchronized public static Retrofit getsInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    private RetrofitClient() {
    }
}
