package com.publicpart.bapayapp.Card;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.publicpart.bapayapp.R;

public class AddCardActivity extends AppCompatActivity implements View.OnClickListener{
    EditText card_no;
    EditText cvc;
    EditText card_thru;
    EditText pw;
    EditText card_nick;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        register = findViewById(R.id.regist);
        register.setOnClickListener(this);

        card_no = findViewById(R.id.card_no);
        card_thru = findViewById(R.id.card_thru);
        pw = findViewById(R.id.pw);
        card_nick = findViewById(R.id.card_nick);
        cvc = findViewById(R.id.cvc);
    }
    @Override
    public void onClick(View v) {
        FrameLayout container = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        //dialogEdit.setLayoutParams(params);
        //container.addView(dialogEdit);

        final AlertDialog.Builder alt_bld = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        alt_bld.setTitle("카드등록").setMessage("정말 등록하시겠습니까?").setIcon(R.drawable.modify).
                setView(container).setPositiveButton("확인",
                (dialog, id) -> {
                    try {
                        //Log.d("value", value);
                        Toast.makeText(alt_bld.getContext(), "등록이 완료되었습니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, CardDetailActivity.class);
                        startActivity(intent);
                    }
                    catch (Exception e){
                        Toast.makeText(alt_bld.getContext(), "올바른 값이 아닙니다.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("취소",
                (dialog, which) -> {

                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }
}
