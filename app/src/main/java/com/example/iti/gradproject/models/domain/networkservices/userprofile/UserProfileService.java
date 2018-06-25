package com.example.iti.gradproject.models.domain.networkservices.userprofile;

import com.example.iti.gradproject.models.entities.UserProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface UserProfileService {
    @GET("users/profile")
    Call<UserProfileResponse> getUserProfile(@Header("Authorization") String token);
}
