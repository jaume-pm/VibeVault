package com.example.vibevault.interfaces;

import com.example.vibevault.APIServices.ApiResponse;
import com.example.vibevault.APIServices.ApiTokenResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SpotifyAPIService {

    @GET("v1/playlists/37i9dQZEVXbJwoKy8qKpHG/tracks/") // El numero es el id de la playlist Top50España
    Call<ApiResponse> getAllGlobalSongs(
            @Header("Authorization") String authToken
    );
}
