package com.example.iti.gradproject.screens.loginscreen;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.networkservices.login.LoginService;
import com.example.iti.gradproject.models.domain.networkservices.login.LoginServiceImpl;
import com.example.iti.gradproject.models.entities.LoginResponse;

import retrofit2.Call;
import retrofit2.Response;

public class LogInPresenterImpl implements LogInContract.LogInPresenter, BaseService.Login {

    private static final String LOG_TAG = LogInPresenterImpl.class.getSimpleName();
    private final Context context;
    private final LogInContract.LogInScreen view;
    private LoginServiceImpl loginService;

    LogInPresenterImpl(Context context, LogInContract.LogInScreen view) {
        this.context = context;
        this.view = view;
        loginService =  new LoginServiceImpl();

    }

    @Override
    public void loginWithUsernameOrPhoneNumber(String emailOrPhoneNumber, String password) {
        if (!isConnectedToInternet()) {
            view.showErrorDialogue(context.getResources().getString(R.string.connectionError));

        } else {
            view.showLoadingIndicator();
            loginService.loginWithUsernameOrPhoneNumber(emailOrPhoneNumber, password, this);
        }
    }

    private boolean isConnectedToInternet() {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    @Override
    public void onError() {

    }

    @Override
    public void onSuccess(Response<LoginResponse> response) {

    }
}
