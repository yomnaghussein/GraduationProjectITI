package com.example.iti.gradproject.models.domain.networkservices.login;

import com.example.iti.gradproject.models.entities.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface LoginService {
    @Headers("Content-Type: application/json")
    @POST("auth/signin")
    Call<LoginResponse> loginUserWithEmailOrPhoneNumber(@Body String requestBody);
}
