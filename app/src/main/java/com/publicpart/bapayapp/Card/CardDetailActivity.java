package com.publicpart.bapayapp.Card;

import android.animation.ArgbEvaluator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.publicpart.bapayapp.R;

import java.util.ArrayList;
import java.util.List;

public class CardDetailActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager viewPager;
    Adapter adapter;
    List<CardsModel> cardsModels;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button modify_btn;
    Button add_btn;
    TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        titleView = findViewById(R.id.detail_title);
        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCardActivity.class);
                startActivity(intent);
            }
        });
        modify_btn = findViewById(R.id.btn_modify);
        modify_btn.setOnClickListener(this);



        cardsModels = new ArrayList<>();
        cardsModels.add(new CardsModel(R.drawable.ski, "목적지", "어디로 여행을 갈지 골라보자"));
        cardsModels.add(new CardsModel(R.drawable.home, "숙소", "근처 숙소들을 찾아보고 숙소 가격을 알아보자"));
        cardsModels.add(new CardsModel(R.drawable.emart, "장보기", "무엇을 사고 대충 비용이 얼마나 드는지 알아보자"));
        cardsModels.add(new CardsModel(R.drawable.rent, "이동수단", "어떤 이동수단을 사용하고 비용이 얼마나 드는지 알아보자"));

        adapter = new Adapter(cardsModels,this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 360, 130, 0);

        Integer[] colors_temp = {
                ContextCompat.getColor(this, R.color.color1),
                ContextCompat.getColor(this, R.color.color2),
                ContextCompat.getColor(this, R.color.color3),
                ContextCompat.getColor(this, R.color.color4)
        };
        colors = colors_temp;

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                    //viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                }

                else {
                    //viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        FrameLayout container = new FrameLayout(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        params.rightMargin = getResources().getDimensionPixelSize(R.dimen.dialog_margin);
        EditText dialogEdit = new EditText(getApplicationContext());
        dialogEdit.setLayoutParams(params);
        container.addView(dialogEdit);

        final AlertDialog.Builder alt_bld = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
        alt_bld.setTitle("카드 닉네임 변경").setMessage("변경할 닉네임을 입력하세요").setIcon(R.drawable.modify).
                setView(container).setPositiveButton("확인",
                (dialog, id) -> {
                    String value = dialogEdit.getText().toString();

                    Log.d("value", value);
                    Intent intent = new Intent(this, CardDetailActivity.class);
                    startActivity(intent);
                    if (value.equals("")) {
                        Toast.makeText(alt_bld.getContext(), "닉네임을 입력해주세요", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("취소",
                (dialog, which) -> {

                });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }
}