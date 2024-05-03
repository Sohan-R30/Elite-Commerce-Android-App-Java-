package com.example.elitecommerce.Services;

import com.example.elitecommerce.Interface.ProductsApi;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static RetrofitInstance instance;
    public ProductsApi productsApi;

    public RetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://elite-commerce-server-admin.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productsApi = retrofit.create(ProductsApi.class);
    }

    public static RetrofitInstance getInstance()
    {
        if (instance == null)
        {
            instance = new RetrofitInstance();
        }
        return  instance;
    }
}
