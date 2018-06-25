package com.example.iti.gradproject.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.iti.gradproject.models.entities.UserProfile;
import com.google.gson.Gson;

public class Utilities {
    public static void saveTokenPref(Context context, String savedUserToken) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("AccessToken", "Bearer " + savedUserToken).apply();
    }

    public static String getTokenFromPref(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getString("AccessToken", "");
    }
    public static void saveUserPref(Context context, UserProfile savedUser) {
        Gson gson = new Gson();
        String data = gson.toJson(savedUser);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString("user_obj", data).apply();
    }

    public static UserProfile getUserFromPref(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userAsString = preferences.getString("user_obj", "");
        Gson gson = new Gson();

        return gson.fromJson(userAsString, UserProfile.class);
    }
}
