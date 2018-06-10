package com.example.iti.gradproject.screens.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iti.gradproject.R;


import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.iti.gradproject.screens.loginscreen.LogInContract.*;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.transitions);
        imageView.startAnimation(myanim);
        textView.startAnimation(myanim);
        final Intent loginIntent = new Intent(this, com.example.iti.gradproject.screens.loginscreen.LogInScreen.class);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(loginIntent);
                    finish();
                }
            }
        };
        timer.start();

    }
}
