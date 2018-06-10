package com.example.iti.gradproject.screens.loginscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.iti.gradproject.R;

public class LogInScreen extends AppCompatActivity implements LogInContract.LogInScreen{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
    }
}
