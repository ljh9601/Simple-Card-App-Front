package com.publicpart.bapayapp.Login;

import com.google.gson.JsonObject;
import com.publicpart.bapayapp.R;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


public interface LoginAPI {

    @POST(value = "user/login/")
    Call<List<PostLoginModel>> postLogin(@Body PostLoginModel post);


}
