package com.publicpart.bapayapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.publicpart.bapayapp.Card.CardDetailActivity;
import com.publicpart.bapayapp.Login.LoginAPI;
import com.publicpart.bapayapp.Login.PostLoginModel;
import com.publicpart.bapayapp.customfonts.EditText_Roboto_Regular;
import com.publicpart.bapayapp.customfonts.MyTextView_Roboto_Regular;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //TextView login_btn;
    EditText_Roboto_Regular id_edit;               // id 에디트
    MyTextView_Roboto_Regular login_btn;
    private LoginAPI loginAPI;
    private ArrayList<PostLoginModel> cardList =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login_view);
        login_btn.setOnClickListener(this);

        id_edit = findViewById(R.id.edit_pw);
        initLoginAPI(getResources().getString(R.string.basic_url));

    }

    private void initLoginAPI(String baseUrl) {
        //Log.d(TAG,"initMyAPI : " + baseUrl);
        Log.d("url : ", baseUrl);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginAPI = retrofit.create(LoginAPI.class);
        Log.d("dddd", loginAPI.toString());
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), CardDetailActivity.class);
        startActivity(intent);

        //Log.d(TAG,"POST");
        PostLoginModel item = new PostLoginModel();
        item.setUser_id(id_edit.getText().toString());
        Call<List<PostLoginModel>> postCall = loginAPI.postLogin(item);
        postCall.enqueue(new Callback<List<PostLoginModel>>() {
            @Override
            public void onResponse(Call<List<PostLoginModel>> call, Response<List<PostLoginModel>> response) {
                if (response.isSuccessful()) {
                    Log.d("response", response.toString());
                    Log.d("Response", response.body().toString());

                    cardList = (ArrayList)response.body();

                    Log.d("cardList", cardList.get(0).getName());

                    String t = response.body().toString();
                    Log.d("response body", t);
                    ArrayList<PostLoginModel> res = new ArrayList<PostLoginModel>(cardList.size());

                    for(int i = 0; i < cardList.size(); i++){
                        res.add(cardList.get(i));
                        Log.d("res.get(i)", res.get(i).getName());

                        Log.d(Integer.toString(i), cardList.get(i).toString());
                        Log.d(Integer.toString(i), cardList.get(i).getUser_id());
                        Log.d(Integer.toString(i),cardList.get(i).getCode());
                    }

                    Log.d("name", res.get(0).getName());
                    String mes = res.get(0).getName() + "님 환영합니다.";
                    Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG);
                    Intent intent = new Intent(getApplicationContext(), CardDetailActivity.class);
                    intent.putExtra("cardList", res);
                    startActivity(intent);
                } else {
                    Log.d("", "Status Code : " + response.code());
                    Log.d("", response.errorBody().toString());
                    Log.d("", call.request().body().toString());
                    Toast.makeText(getApplicationContext(), "key를 확인하시기 바랍니다.", Toast.LENGTH_SHORT);

                }
            }

            @Override
            public void onFailure(Call<List<PostLoginModel>> call, Throwable t) {

            }
        });
    }
}