package com.publicpart.bapayapp.Card;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.publicpart.bapayapp.Login.LoginAPI;
import com.publicpart.bapayapp.Login.PostLoginModel;
import com.publicpart.bapayapp.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCardActivity extends AppCompatActivity implements View.OnClickListener{

    EditText card_no;
    EditText cvc;
    EditText card_thru;
    EditText pw;
    EditText card_nick;
    Button register;
    private Spinner spinner1;
    private CardAPI cardAPI;
    private AddCardModel addResult;

    final String[] bankList = {"신한은행", "우리은행", "하나은행", "카카오뱅크", "기업은행", "외환은행", "케이뱅크", "국민은행", "수협", "농협"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        register = findViewById(R.id.regist);
        register.setOnClickListener(this);

        spinner1 = (Spinner) findViewById(R.id.card_comp);
        spinner1.setOnItemSelectedListener(new ItemSelectedListener());

        card_no = findViewById(R.id.card_no);
        card_thru = findViewById(R.id.card_thru);
        pw = findViewById(R.id.pw);
        card_nick = findViewById(R.id.card_nick);
        cvc = findViewById(R.id.cvc);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.basic_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cardAPI = retrofit.create(CardAPI.class);
        Log.d("dddd", cardAPI.toString());
    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(spinner1.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "You have selected : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

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
                        Intent intent = new Intent(getApplicationContext(), CardDetailActivity.class);
                        startActivity(intent);
                        AddCardModel item = new AddCardModel();
                        item.setCard_cvc(cvc.getText().toString());
                        item.setCard_company(spinner1.getSelectedItem().toString());
                        item.setCard_name(card_nick.getText().toString());
                        item.setCard_num(card_no.getText().toString());
                        item.setCardInfo_id(spinner1.getSelectedItem().toString() + cvc.getText().toString());
                        Call<AddCardModel> postCall = cardAPI.postAdd(item);
                        Log.d("AddCardModel :", postCall.toString());
                        postCall.enqueue(new Callback<AddCardModel>() {
                            @Override
                            public void onResponse(Call<AddCardModel> call, Response<AddCardModel> response) {
                                Log.d("??", "?????????????");
                                if (response.isSuccessful()) {
                                    Log.d("response", response.toString());

                                    addResult = response.body();
                                    String t = response.body().toString();
                                    Log.d("response body", t);
                                    //List<PostLoginModel> result = null;
                                    Log.d("addResult", addResult.toString());
                                    //Toast.makeText(this, "성공적으로 등록되었습니다.", Toast.LENGTH_LONG);
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
                            public void onFailure(Call<AddCardModel> call, Throwable t) {

                            }
                        });


                       /* Toast.makeText(alt_bld.getContext(), "등록이 완료되었습니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(this, CardDetailActivity.class);
                        startActivity(intent);*/
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
