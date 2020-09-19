package com.publicpart.bapayapp.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class EditText_Roboto_Regular extends androidx.appcompat.widget.AppCompatEditText {

    public EditText_Roboto_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditText_Roboto_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditText_Roboto_Regular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "font/roboto_regular.ttf");
            setTypeface(typeface);
        }
    }
}