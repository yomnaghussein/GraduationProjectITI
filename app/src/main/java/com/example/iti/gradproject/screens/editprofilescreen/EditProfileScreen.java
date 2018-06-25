package com.example.iti.gradproject.screens.editprofilescreen;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iti.gradproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileScreen extends AppCompatActivity implements EditProfileContract.EditProfileScreen {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.back_imageView)
    ImageView backImageView;
    @BindView(R.id.tv_lname)
    TextView tvLname;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.btn_signout)
    Button btnSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_screen);
        ButterKnife.bind(this);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
