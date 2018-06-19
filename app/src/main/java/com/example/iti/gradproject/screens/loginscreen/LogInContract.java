package com.example.iti.gradproject.screens.loginscreen;

public interface LogInContract {
    public static interface LogInPresenter {
        void loginWithUsernameOrPhoneNumber(String emailOrPhoneNumber, String password);

    }
    public static interface LogInScreen {
        void navigateToOrdersActivity();

        void showErrorDialogue(String message);

        void showLoadingIndicator();

        void dismissLoadingIndicator();

    }
}
