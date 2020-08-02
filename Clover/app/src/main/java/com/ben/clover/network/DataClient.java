package com.ben.clover.network;

import com.ben.clover.request.CheckActiveRequest;
import com.ben.clover.request.ForgotPasswordRequest;
import com.ben.clover.request.LoginRequest;
import com.ben.clover.request.RegisterRequest;
import com.ben.clover.request.SearchFriendRequest;
import com.ben.clover.request.SearchStrangerRequest;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DataClient {

    @POST("/register")
    Call<JsonObject> register(@Body RegisterRequest request);

    @POST("/login")
    Call<JsonObject> login(@Body LoginRequest request);

    @POST("/forgot_password")
    Call<JsonObject> forgotPassword(@Body ForgotPasswordRequest request);

    @POST("/friends")
    Call<JsonObject> searchFriend(@Body SearchFriendRequest request);

    @POST("/active")
    Call<JsonObject> checkActive(@Body CheckActiveRequest request);

    @POST("/strangers")
    Call<JsonObject> searchStrangers(@Body SearchStrangerRequest request);
}
