package com.example.vibevault.interfaces;

import com.example.vibevault.albums.api.ApiResponseGetAlbums;
import com.example.vibevault.albums.api.ApiResponseSearchAlbum;
import com.example.vibevault.songs.api.ApiResponseGetSongs;
import com.example.vibevault.songs.api.ApiResponseSearchSong;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface SpotifyAPIService {

    // SONGS //////////////////////////////////////////////////////////////////
    @GET("v1/playlists/37i9dQZEVXbJwoKy8qKpHG/tracks/") // El numero es el id de la playlist Top50España
    Call<ApiResponseGetSongs> getAllGlobalSongs(
            @Header("Authorization") String authToken
    );

    @GET("v1/search") // Pide una canción a la api de spotify
    Call<ApiResponseSearchSong> getSong(
            @Query("q") String trackName,
            @Query("type") String type,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("include_external") String includeExternal,
            @Header("Authorization") String authToken
    );
    // END SONGS //////////////////////////////////////////////////////////////////


    // ALBUMS //////////////////////////////////////////////////////////////////
    String popular_album_ids = "490JPsy57LAprfYGjEDBr3,3RQQmkQEvNCY4prGKE6oc5,4yP0hdKOZPNshxUOjY0cZj,6E7Awtyx6opYTbPXx6ApzB,6ZG5lRT77aJ3btmArcykra,1HjSyGjmLNjRAKgT9t1cna,32iAEBstCjauDhyKpGjTuq,7f6xPqyaolTiziKf5R5Z0c";
    @GET("v1/playlists/37i9dQZEVXbJwoKy8qKpHG/tracks/") // El numero es el id de la playlist Top50España
    Call<ApiResponseGetAlbums> getAllGlobalAlbums(
            @Header("Authorization") String authToken
    );

    @GET("v1/search") // Pide un album a la api de spotify
    Call<ApiResponseSearchAlbum> getAlbum(
            @Query("q") String trackName,
            @Query("type") String type,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Header("Authorization") String authToken
    );
    // END ALBUMS //////////////////////////////////////////////////////////////////
}
