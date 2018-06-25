package com.example.iti.gradproject.screens.homescreen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.iti.gradproject.R;
import com.example.iti.gradproject.screens.editprofilescreen.EditProfileScreen;
import com.example.iti.gradproject.screens.homescreen.DoneScreen.DoneTabFragment;
import com.example.iti.gradproject.screens.homescreen.InProcessScreen.InProcessTabFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeScreen extends AppCompatActivity implements InProcessTabFragment.OnFragmentInteractionListener,DoneTabFragment.OnFragmentInteractionListener{

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.profile_img)
    ImageView profileImgV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);

        tabLayout.addTab(tabLayout.newTab().setText("In-process"));
        tabLayout.addTab(tabLayout.newTab().setText("Done"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PagerAdapter pagerAdapter=new PagerAdapter(getSupportFragmentManager(),2);
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        profileImgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeScreen.this, EditProfileScreen.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
