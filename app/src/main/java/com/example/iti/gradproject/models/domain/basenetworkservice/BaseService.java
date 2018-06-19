package com.example.iti.gradproject.models.domain.basenetworkservice;

import com.example.iti.gradproject.models.entities.LoginResponse;

import retrofit2.Response;

public interface BaseService {
    void onError();

    interface Login extends BaseService {
        void onSuccess(Response<LoginResponse> response);
    }


}
