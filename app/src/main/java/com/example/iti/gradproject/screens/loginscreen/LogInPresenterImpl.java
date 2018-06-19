package com.example.iti.gradproject.screens.loginscreen;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.models.domain.basenetworkservice.BaseService;
import com.example.iti.gradproject.models.domain.network.RetrofitClient;
import com.example.iti.gradproject.models.domain.networkservices.login.LoginService;
import com.example.iti.gradproject.models.domain.networkservices.login.LoginServiceImpl;
import com.example.iti.gradproject.models.entities.LoginResponse;

import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
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
        view.dismissLoadingIndicator();
        view.showErrorDialogue(context.getResources().getString(R.string.str_an_error_happend));
        Log.i(LOG_TAG, "onError is called");
    }

    @Override
    public void onSuccess(Response<LoginResponse> response) {
        view.dismissLoadingIndicator();
        switch (response.code()) {
            case 200:
                LoginResponse successfulResponse = response.body();
                Log.i(LOG_TAG, "accessToken: " + successfulResponse.getAccessToken());
                view.navigateToOrdersActivity();
                break;

            default:
                Converter<ResponseBody, LoginResponse> converter = RetrofitClient.getsInstance().responseBodyConverter(LoginResponse.class, new Annotation[0]);
                try {
                    LoginResponse error = converter.convert(response.errorBody());
                    Log.i(LOG_TAG, "Response code: "+response.code()+ " message: "+error.getMessage());
                    view.showErrorDialogue(error.getMessage());

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

    }
}
