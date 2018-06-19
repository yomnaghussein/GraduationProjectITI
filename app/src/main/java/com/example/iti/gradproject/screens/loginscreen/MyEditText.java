package com.example.iti.gradproject.screens.loginscreen;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/**
 * Created by Hasnaa on 03/06/2018.
 */

public class MyEditText extends android.support.v7.widget.AppCompatEditText {

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        setCompoundDrawables(null, null, icon, null);
    }

}