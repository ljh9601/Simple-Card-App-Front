package com.publicpart.bapayapp.Card;

import com.publicpart.bapayapp.Login.PostLoginModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CardAPI {


    @POST(value = "card/modifyCard/")
    Call<ModifyCardModel> postModify(@Body ModifyCardModel post);

    @POST(value = "card/addCard/")
    Call<AddCardModel> postAdd(@Body AddCardModel post);
}
