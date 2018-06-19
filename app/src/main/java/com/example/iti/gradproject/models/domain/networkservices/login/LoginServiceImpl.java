package com.example.iti.gradproject.models.domain.networkservices.login;

import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.entities.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginServiceImpl {
    private LoginService loginService;

    public LoginServiceImpl() {
        loginService = RetrofitClient.getsInstance().create(LoginService.class);
    }
    public void loginWithUsernameOrPhoneNumber(String emailOrPhoneNumber, String password, final BaseService.Login baseService) {

        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("emailOrPhoneNumber", emailOrPhoneNumber);
            requestBody.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        loginService.loginUserWithEmailOrPhoneNumber(requestBody.toString())
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        baseService.onSuccess(response);
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        baseService.onError();
                    }
                });
    }
}
