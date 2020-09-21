package com.publicpart.bapayapp;

import com.publicpart.bapayapp.Login.PostLoginModel;

import java.util.ArrayList;

public class Singleton {
    public Singleton shared;


    private ArrayList<PostLoginModel> per = new ArrayList<>();

    Singleton(Singleton shared){
        this.shared = shared;
    }

    public void json_getter(ArrayList ll) {
        this.per = ll;
        System.out.println(ll);
    }
}
