package com.example.elitecommerce.Services;

import com.example.elitecommerce.Interface.CartsApi;
import com.example.elitecommerce.Interface.ProductsApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstanceCart {
    public static RetrofitInstanceCart instance;
    public CartsApi cartsApi;

    public RetrofitInstanceCart() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://elite-commerce-server-admin.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cartsApi = retrofit.create(CartsApi.class);
    }

    public static RetrofitInstanceCart getInstance()
    {
        if (instance == null)
        {
            instance = new RetrofitInstanceCart();
        }
        return  instance;
    }
}
