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

import com.publicpart.bapayapp.Login.LoginAPI;
import com.publicpart.bapayapp.Login.PostLoginModel;
import com.publicpart.bapayapp.R;
import com.publicpart.bapayapp.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CardDetailActivity extends AppCompatActivity implements View.OnClickListener{
    ViewPager viewPager;
    Adapter adapter;
    List<CardsModel> cardsModels;
    List<PostLoginModel> cardList;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    Button modify_btn;
    Button add_btn;
    TextView titleView;
    Singleton sing;

    TextView detailTitle;
    private CardAPI cardAPI;

    private ModifyCardModel modCardModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.basic_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cardAPI = retrofit.create(CardAPI.class);

        //initLoginAPI(getResources().getString(R.string.basic_url));

        add_btn = findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCardActivity.class);
                startActivity(intent);
            }

        });

        Intent intent2 = getIntent();
        ArrayList<PostLoginModel> resList = (ArrayList<PostLoginModel>) intent2.getSerializableExtra("cardList");
        modify_btn = findViewById(R.id.btn_modify);
        modify_btn.setOnClickListener(this);

        cardsModels = new ArrayList<>();

/*        for (int i = 0; i < resList.size(); i++){
            switch (resList.get(i).getCompany()){
                case "shinhan":
                    cardsModels.add(new CardsModel(R.drawable.shinhan, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;
                case "KB":
                    cardsModels.add(new CardsModel(R.drawable.kb, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;
                case "Woori":
                    cardsModels.add(new CardsModel(R.drawable.woori, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;
                case "Hyundai":
                    cardsModels.add(new CardsModel(R.drawable.hyundai, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;
                case "lotte":
                    cardsModels.add(new CardsModel(R.drawable.lotte, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;
                case "nh":
                    cardsModels.add(new CardsModel(R.drawable.nh, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;
                case "samsung":
                    cardsModels.add(new CardsModel(R.drawable.samsung, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;
                case "bc":
                    cardsModels.add(new CardsModel(R.drawable.bc, resList.get(i).getName(), resList.get(i).getNum().substring(0,4) + "- **** - **** - ****"));
                    break;

            }
        }*/

        cardsModels.add(new CardsModel(R.drawable.bc, "비씨", "1254 - **** - **** - ****"));
        cardsModels.add(new CardsModel(R.drawable.shinhan, "신한", "1937 - **** - **** - ****"));
        cardsModels.add(new CardsModel(R.drawable.woori, "이동수단", "8373 - **** - **** - ****"));
        cardsModels.add(new CardsModel(R.drawable.hana, "이동수단", "0172 - **** - **** - ****"));

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

    private void initCardAPI(String baseUrl) {
        //Log.d(TAG,"initMyAPI : " + baseUrl);
        Log.d("url : ", baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cardAPI = retrofit.create(CardAPI.class);
        Log.d("dddd", cardAPI.toString());
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
                    Intent tempIntent = getIntent();

                    Log.d("?", "??????????");

                    String value = dialogEdit.getText().toString();
                    ModifyCardModel item = new ModifyCardModel();
                    Log.d("value", value);
                    item.setNew_name(value);

                    ArrayList<PostLoginModel> resList = (ArrayList<PostLoginModel>) tempIntent.getSerializableExtra("cardList");
                    item.setCard_num(resList.get(0).getNum());
                    Log.d("item", resList.get(0).getNum());
                    Call<ModifyCardModel> postCall = cardAPI.postModify(item);
                    Log.d("ModCardModel :", postCall.toString());
                    postCall.enqueue(new Callback<ModifyCardModel>() {
                        @Override
                        public void onResponse(Call<ModifyCardModel> call, Response<ModifyCardModel> response) {
                            Log.d("??", "?????????????");
                            String modifyRes;
                            if (response.isSuccessful()) {
                                Log.d("response", response.toString());

                                modCardModel = response.body();
                                String t = response.body().toString();
                                Log.d("response body", t);
                                //List<PostLoginModel> result = null;
                                Log.d("addResult", modCardModel.toString());
                                Toast.makeText(getApplicationContext(), "성공적으로 변경되었습니다.", Toast.LENGTH_LONG);
                                Intent intent = new Intent(getApplicationContext(), CardDetailActivity.class);
                                startActivity(intent);
                            } else {
                                Log.d("", "Status Code : " + response.code());
                                Log.d("", response.errorBody().toString());
                                Log.d("", call.request().body().toString());
                                Toast.makeText(getApplicationContext(), "key를 확인하시기 바랍니다.", Toast.LENGTH_SHORT);
                                Intent intent = new Intent(getApplicationContext(), CardDetailActivity.class);
                                startActivity(intent);

                            }
                        }

                        @Override
                        public void onFailure(Call<ModifyCardModel> call, Throwable t) {

                        }
                    });
                    Log.d("value", value);
                    if (value.equals("")) {
                        Toast.makeText(alt_bld.getContext(), "닉네임을 입력해주세요", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("취소",
                (dialog, which) -> {

                });
        Log.d("dd", "dsafsdfdfs");
        AlertDialog alert = alt_bld.create();
        alert.show();
    }
}