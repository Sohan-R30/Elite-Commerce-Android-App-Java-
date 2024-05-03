package com.example.elitecommerce.Services;

import com.example.elitecommerce.Interface.UsersApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceUsers {
    public static RetrofitInstanceUsers instance;
    public UsersApi usersApi;

    public RetrofitInstanceUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://elite-commerce-server-admin.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        usersApi = retrofit.create(UsersApi.class);
    }

    public static RetrofitInstanceUsers getInstance()
    {
        if (instance == null)
        {
            instance = new RetrofitInstanceUsers();
        }
        return  instance;
    }
}
