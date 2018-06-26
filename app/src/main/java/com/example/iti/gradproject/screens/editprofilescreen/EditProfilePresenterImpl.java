package com.example.iti.gradproject.screens.editprofilescreen;

import android.content.Context;
import android.util.Log;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.domain.networkservices.userprofile.UserProfileServiceImpl;
import com.example.iti.gradproject.models.entities.UserProfile;
import com.example.iti.gradproject.models.entities.UserProfileResponse;
//import com.example.iti.gradproject.screens.homescreen.InProcessScreen.InProcessContract;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class EditProfilePresenterImpl implements EditProfileContract.EditProfilePresenter,BaseService.ViewUserProfile {
    private UserProfileServiceImpl userProfileService;
    private final Context context;
    private final EditProfileContract.EditProfileScreen view;

    public EditProfilePresenterImpl(Context context,EditProfileContract.EditProfileScreen view) {
        this.context = context;
        this.view = view;

        userProfileService = new UserProfileServiceImpl();


    }

    @Override
    public void getUserProfile(String accessToken) {
        userProfileService.getUserProfile(accessToken,this);
    }

    @Override
    public void onError() {

    }

    @Override
    public void onSuccessView(Response<UserProfileResponse> response) {
        switch (response.code()) {
            case 200:
                UserProfileResponse successfulResponse = response.body();
                UserProfile userProfile =  successfulResponse.getUserProfile();
                view.setUserProfile(userProfile);



                break;

            default:
                Converter<ResponseBody, UserProfileResponse> converter = RetrofitClient.getsInstance().responseBodyConverter(UserProfileResponse.class, new Annotation[0]);
                try {
                    UserProfileResponse error = converter.convert(response.errorBody());
                    Log.i("Response", "Response code: "+response.code()+ " message: "+error.getMessage());


                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
