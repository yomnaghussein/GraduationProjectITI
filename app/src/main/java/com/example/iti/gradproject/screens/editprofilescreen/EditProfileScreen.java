package com.example.iti.gradproject.screens.editprofilescreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.iti.gradproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileScreen extends AppCompatActivity implements EditProfileContract.EditProfileScreen {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_screen);
        ButterKnife.bind(this);
    }
}
