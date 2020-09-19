package com.publicpart.bapayapp.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

import com.publicpart.bapayapp.R;

public class EditText_Roboto_Medium extends androidx.appcompat.widget.AppCompatEditText {
    public EditText_Roboto_Medium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditText_Roboto_Medium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditText_Roboto_Medium(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/roboto_medium.ttf");
            setTypeface(tf);
        }
    }

}
