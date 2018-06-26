package com.example.iti.gradproject.screens.editprofilescreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.models.App;
import com.example.iti.gradproject.models.Utilities;
import com.example.iti.gradproject.models.entities.UserProfile;
import com.example.iti.gradproject.screens.loginscreen.LogInScreen;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditProfileScreen extends AppCompatActivity implements EditProfileContract.EditProfileScreen {

    EditProfilePresenterImpl editProfilePresenter;

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
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_fname)
    TextView tvFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_screen);
        ButterKnife.bind(this);
        editProfilePresenter = new EditProfilePresenterImpl(App.getApplication(),this);
        final String accesstoken = Utilities.getTokenFromPref(App.getApplication());
        editProfilePresenter.getUserProfile(accesstoken);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.logUserOutAndRemoveAllStoredData(EditProfileScreen.this);
                Intent intent = new Intent(EditProfileScreen.this, LogInScreen.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void setUserProfile(UserProfile userProfile) {
        tvFname.setText(userProfile.getFirstName());
        tvLname.setText(userProfile.getLastName());
        tvEmail.setText(userProfile.getEmail());
        tvMobile.setText(userProfile.getPhoneNumber());
    }
}
