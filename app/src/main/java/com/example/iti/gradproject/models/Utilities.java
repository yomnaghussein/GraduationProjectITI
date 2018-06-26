package com.example.iti.gradproject.models;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

import com.example.iti.gradproject.R;
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

    public static boolean isConnectedToInternet(Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm != null ? cm.getActiveNetworkInfo() : null;

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    public static void showInternetErrorDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Internet Connection Error")
                .setMessage("Please check your internet connection...")
                .setPositiveButton(R.string.str_ok_btn_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
