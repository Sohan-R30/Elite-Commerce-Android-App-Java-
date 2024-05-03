package com.example.elitecommerce.Interface;

import com.example.elitecommerce.Model.UsersModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsersApi {
    @POST("/api/users/add-user")
    Call<UsersModel> postUser(@Body UsersModel usersModel);
}
