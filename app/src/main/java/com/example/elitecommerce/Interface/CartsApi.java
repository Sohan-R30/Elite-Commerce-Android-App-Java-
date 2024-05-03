package com.example.elitecommerce.Interface;

import com.example.elitecommerce.Model.CartProductModel;
import com.example.elitecommerce.Model.UsersModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartsApi {
    @POST("/api/carts/add-to-cart")
    Call<CartProductModel> postCarts(@Body CartProductModel cartProductModel);

    @GET("/api/carts/{email}")
    Call<ArrayList<CartProductModel>> getCarts(@Path("email") String userEmail);

    @DELETE("/api/carts/{id}")
    Call<CartProductModel> deleteCarts(@Path("id") String _id);

    @DELETE("/api/carts/delete/{email}")
    Call<CartProductModel> deleteAllCarts(@Path("email") String email);

//    @PATCH("/api/carts/add-to-cart/{}")
//    Call<CartProductModel> updateCarts(@Path("user") String productId);
}
