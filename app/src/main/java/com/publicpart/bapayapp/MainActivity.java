package com.publicpart.bapayapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.publicpart.bapayapp.Card.CardDetailActivity;
import com.publicpart.bapayapp.Login.LoginAPI;
import com.publicpart.bapayapp.Login.PostLoginModel;
import com.publicpart.bapayapp.customfonts.EditText_Roboto_Regular;
import com.publicpart.bapayapp.customfonts.MyTextView_Roboto_Regular;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.login_view);
        login_btn.setOnClickListener(this);

        id_edit = findViewById(R.id.edit_pw);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.basic_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginAPI = retrofit.create(LoginAPI.class);
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
        Log.d("??", "??????");
         //임시 처리
        Intent intent = new Intent(getApplicationContext(), CardDetailActivity.class);
        startActivity(intent);

        //Log.d(TAG,"POST");
        PostLoginModel item = new PostLoginModel();
        item.setUser_id(id_edit.getText().toString());
        Log.d("id : ", id_edit.getText().toString());
        Log.d("newId", item.getUser_id());
        Call<PostLoginModel> postCall = loginAPI.postLogin(item);
        Log.d("postmodel :", postCall.toString());
        postCall.enqueue(new Callback<PostLoginModel>() {
            @Override
            public void onResponse(Call<PostLoginModel> call, Response<PostLoginModel> response) {
                if (response.isSuccessful()) {
                    //Log.d(TAG, "등록 완료");
                    Intent intent = new Intent(getApplicationContext(), CardDetailActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("", "Status Code : " + response.code());
                    Log.d("", response.errorBody().toString());
                    Log.d("", call.request().body().toString());
                    //Toast.makeText(, "로그인에 실패하였습니다.");
                }
            }

            @Override
            public void onFailure(Call<PostLoginModel> call, Throwable t) {
                Log.d("TAG", "Fail msg : " + t.getMessage());
            }
        });
    }
}

/*
if( v == login_btn){
            //Log.d(TAG,"GET");
            Call<List<PostLoginModel>> getCall = loginAPI.get();
            getCall.enqueue(new Callback<List<PostLoginModel>>() {
                @Override
                public void onResponse(Call<List<PostLoginModel>> call, Response<List<PostLoginModel>> response) {
                    if( response.isSuccessful()){
                        List<PostLoginModel> mList = response.body();
                        String result ="";
                        for( PostLoginModel item : mList){
                            result += "title : " + item.getTitle() + " text: " + item.getText() + "\n";
                        }
                        mListTv.setText(result);
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<PostLoginModel>> call, Throwable t) {
                    Log.d("Failure","Fail msg : " + t.getMessage());
                }
            });
        }else if(v == login_btn){
            //Log.d(TAG,"POST");


            PostLoginModel item = new PostLoginModel();
            item.setTitle("Android title");
            item.setText("Android text");
            Call<PostItem> postCall = mMyAPI.post_posts(item);
            postCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"등록 완료");
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                        Log.d(TAG,response.errorBody().toString());
                        Log.d(TAG,call.request().body().toString());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });
        }else if( v == mPatchButton){
            Log.d(TAG,"PATCH");
            PostItem item = new PostItem();
            item.setTitle("android patch title");
            item.setText("android patch text");
            //pk 값은 임의로 하드코딩하였지만 동적으로 setting 해서 사용가능
            Call<PostItem> patchCall = mMyAPI.patch_posts(1,item);
            patchCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"patch 성공");
                    }else{
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });


        }else if( v == mDeleteButton){
            Log.d(TAG,"DELETE");
            // pk 값은 임의로 변경가능
            Call<PostItem> deleteCall = mMyAPI.delete_posts(2);
            deleteCall.enqueue(new Callback<PostItem>() {
                @Override
                public void onResponse(Call<PostItem> call, Response<PostItem> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"삭제 완료");
                    }else {
                        Log.d(TAG,"Status Code : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PostItem> call, Throwable t) {
                    Log.d(TAG,"Fail msg : " + t.getMessage());
                }
            });
        }
    }


}
*/

