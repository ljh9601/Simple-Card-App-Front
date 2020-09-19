package com.publicpart.bapayapp.Login;


import com.google.gson.annotations.SerializedName;

public class PostLoginModel {
    @SerializedName(value = "user_id")
    private String user_id;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }
}
