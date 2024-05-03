package com.example.elitecommerce.Interface;

import com.example.elitecommerce.Model.ProductsModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApi {
    @GET("/api/products")
    Call<ArrayList<ProductsModel>> getProducts();
}