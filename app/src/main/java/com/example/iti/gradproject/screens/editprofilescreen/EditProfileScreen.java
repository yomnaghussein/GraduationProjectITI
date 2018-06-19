package com.example.iti.gradproject.screens.editprofilescreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.iti.gradproject.R;

public class EditProfileScreen extends AppCompatActivity implements EditProfileContract.EditProfileScreen{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_screen);
    }
}
