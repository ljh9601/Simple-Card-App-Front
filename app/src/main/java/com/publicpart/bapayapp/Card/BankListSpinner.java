package com.publicpart.bapayapp.Card;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.publicpart.bapayapp.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class BankListSpinner extends AppCompatActivity{
    String[] bankList = {"신한은행", "우리은", "하나은행", "카카오뱅크", "기업은행", "외환은행", "케이뱅크", "국민은행", "수협", "농협"};

    @Override
    protected void onCreate(Bundle savedInstanceState)

    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, bankList);
        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.card_comp);
        materialDesignSpinner.setAdapter(arrayAdapter);
    }
}
