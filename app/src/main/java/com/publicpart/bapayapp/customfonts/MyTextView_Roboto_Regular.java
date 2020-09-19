package com.publicpart.bapayapp.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.publicpart.bapayapp.R;

public class MyTextView_Roboto_Regular extends androidx.appcompat.widget.AppCompatButton {
    public MyTextView_Roboto_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextView_Roboto_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView_Roboto_Regular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            //Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/roboto_regular.ttf");
            Typeface tf = ResourcesCompat.getFont(getContext(), R.font.roboto_regular);
            setTypeface(tf);
        }
    }


}
