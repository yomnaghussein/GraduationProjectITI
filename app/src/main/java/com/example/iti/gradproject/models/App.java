package com.example.iti.gradproject.models;

import android.app.Application;

public class App extends Application{


        public static  App application;

        @Override
        public void onCreate() {
            super.onCreate();
            application = this;
        }

        public static App getApplication() {
            return application;
        }
}


