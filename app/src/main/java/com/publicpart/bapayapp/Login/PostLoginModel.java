package com.publicpart.bapayapp.Login;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PostLoginModel implements Serializable {
    @SerializedName(value = "user_id")
    private String user_id;
    @SerializedName(value="name")
    private String name;
    @SerializedName(value="company")
    private String company;
    @SerializedName(value="num")
    private String num;
    private String code;
    private String cardInfo_id;

    public String getCardInfo_id() {
        return cardInfo_id;
    }

    public void setCardInfo_id(String cardInfo_id) {
        this.cardInfo_id = cardInfo_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "PostLoginModel{" +
                "user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", num='" + num + '\'' +
                '}';
    }
}
