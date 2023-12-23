package com.example.vibevault.interfaces;

import com.example.vibevault.APIServices.ApiResponse;
import com.example.vibevault.APIServices.ApiTokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SpotifyAPIService {

    @FormUrlEncoded
    @POST("api/token")
    Call<ApiTokenResponse> getToken(
            @Field("grant_type") String grantType,
            @Header("Authorization") String authHeader
    );

    @GET("tracks")
    Call<ApiResponse> getAllGlobalSongs();
}
