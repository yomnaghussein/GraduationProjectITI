package com.example.iti.gradproject.models.domain.networkservices.userprofile;

import android.util.Log;

import com.example.iti.gradproject.models.App;
import com.example.iti.gradproject.models.Utilities;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.entities.UserProfile;
import com.example.iti.gradproject.models.entities.UserProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileServiceImpl {
    private UserProfileService userProfileService;

    public UserProfileServiceImpl() {
        userProfileService = RetrofitClient.getsInstance().create(UserProfileService.class);
    }
    public void getUserProfile(String token) {


        userProfileService.getUserProfile(token)
                .enqueue(new Callback<UserProfileResponse>() {
                    @Override
                    public void onResponse(Call<UserProfileResponse> call, Response<UserProfileResponse> response) {

                        Log.i("CHECKING_Profile","Code: "+response.code());
                        if (response.isSuccessful() && response.body().getSuccess() &&  response.body() != null && response.body().getUserProfile() != null) {

                            UserProfile userProfile = response.body().getUserProfile();
                            Log.i("CHECKING_Profile","Email");
                            Log.i("CHECKING_Profile",userProfile.getEmail());
                            Utilities.saveUserPref(App.getApplication(), userProfile);
                            //callBack.onSuccess(response.body().getUserProfile());
                        } else {
                            Log.i("CHECKING_Profile", "Error");

                        }
                    }

                    @Override
                    public void onFailure(Call<UserProfileResponse> call, Throwable t) {
                        Log.i("Onfailure", "error");
                    }
                });
    }
}
