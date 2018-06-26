package com.example.iti.gradproject.screens.editprofilescreen;

import com.example.iti.gradproject.models.entities.UserProfile;

public interface EditProfileContract {

    public static interface EditProfilePresenter {
        public void getUserProfile(String accessToken);

    }
    public static interface EditProfileScreen {
        public void setUserProfile(UserProfile userProfile);

    }
}
