package com.example.vibevault.interfaces;

import com.example.vibevault.APIServices.ApiResponse;
import com.example.vibevault.APIServices.ApiTokenResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SpotifyAPIService {

    String popular_album_ids = "490JPsy57LAprfYGjEDBr3,3RQQmkQEvNCY4prGKE6oc5,4yP0hdKOZPNshxUOjY0cZj,6E7Awtyx6opYTbPXx6ApzB,6ZG5lRT77aJ3btmArcykra,1HjSyGjmLNjRAKgT9t1cna,32iAEBstCjauDhyKpGjTuq,7f6xPqyaolTiziKf5R5Z0c";

    @GET("v1/playlists/37i9dQZEVXbJwoKy8qKpHG/tracks/") // El numero es el id de la playlist Top50España
    Call<ApiResponse> getAllGlobalSongs(
            @Header("Authorization") String authToken
    );

}
