package com.example.iti.gradproject.screens.loginscreen;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iti.gradproject.R;

import butterknife.BindView;

public class LogInScreen extends AppCompatActivity implements LogInContract.LogInScreen{

    EditText userEmail,userPassword;
    Button loginBtn;
    TextView errorText;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
        bindViews();
        actionLogin();

    }

    private void actionLogin() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validateInput();
            }
        });
    }

    private boolean validateInput() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String password = userPassword.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        boolean trueEmail = false;
        boolean truePassword = false;


        // email validation
        if(email.isEmpty()){
            errorText.setText(R.string.emptyEmail);
            userEmail.setError("");
        }
        else{
            if (!email.matches(emailPattern)) {
                errorText.setText(R.string.invalidEmail);
                userEmail.setError("");
            } else {
                userEmail.setError(null);
                trueEmail = true;
            }
        }
        // password validation
        if(password.isEmpty()){
            errorText.setText(R.string.emptyPassword);
            userPassword.setError("");
        }
        else{

            userPassword.setError(null);
            truePassword = true;

        }

        if(trueEmail && truePassword){
            return true;
        }
        else{
            return false;

        }
    }

    private void bindViews() {
        userEmail = findViewById(R.id.emailText);
        userPassword = findViewById(R.id.passwordText);
        loginBtn = findViewById(R.id.loginButton);
        errorText = findViewById(R.id.errorText);
    }


    @Override
    public void navigateToOrdersActivity() {
        Intent i = new Intent(this, com.example.iti.gradproject.screens.homescreen.HomeScreen.class);
        startActivity(i);

    }

    @Override
    public void showErrorDialogue(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.str_login_failed)
                .setMessage(message)
                .setPositiveButton(R.string.str_ok_btn_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    public void showLoadingIndicator() {
        progressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.str_loading), true);
    }

    @Override
    public void dismissLoadingIndicator() {
        progressDialog.cancel();
    }
}
