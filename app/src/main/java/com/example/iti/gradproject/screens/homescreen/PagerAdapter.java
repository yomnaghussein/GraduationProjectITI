package com.example.iti.gradproject.screens.homescreen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.iti.gradproject.screens.homescreen.DoneScreen.DoneTabFragment;
import com.example.iti.gradproject.screens.homescreen.InProcessScreen.InProcessTabFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int noOfTabs;

    public PagerAdapter(FragmentManager fm, int noOfTabs) {
        super(fm);
        this.noOfTabs = noOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                InProcessTabFragment inProcessTabFragment=new InProcessTabFragment();
                return inProcessTabFragment;
            case 1:
                DoneTabFragment doneTabFragment=new DoneTabFragment();
                return doneTabFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
