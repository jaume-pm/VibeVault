package com.example.vibevault.interfaces;

import com.example.vibevault.albums.Album;
import com.example.vibevault.albums.api.ApiResponseGetAlbums;
import com.example.vibevault.albums.api.ApiResponseSearchAlbum;
import com.example.vibevault.artists.api.ApiResponseGetArtists;
import com.example.vibevault.artists.api.ApiResponseSearchArtist;
import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.api.ApiResponseGetSongs;
import com.example.vibevault.songs.api.ApiResponseSearchSong;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpotifyAPIService {

    // SONGS //////////////////////////////////////////////////////////////////
    @GET("v1/playlists/37i9dQZEVXbJwoKy8qKpHG/tracks/") // El numero es el id de la playlist Top50España
    Call<ApiResponseGetSongs> getAllGlobalSongs(
            @Header("Authorization") String authToken
    );

    @GET("v1/tracks/{id}") // Pide una canción a la api de spotify por ID
    Call<Song> getSongByID(
            @Path("id") String songId, // Use @Path to specify the album ID as a path parameter
            @Header("Authorization") String authToken
    );

    @GET("v1/search") // Pide una canción a la api de spotify por NOMBRE
    Call<ApiResponseSearchSong> getSongByName(
            @Query("q") String trackName,
            @Query("type") String type,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("include_external") String includeExternal,
            @Header("Authorization") String authToken
    );
    // END SONGS //////////////////////////////////////////////////////////////////

    // ARTISTS //////////////////////////////////////////////////////////////////
    @GET("v1/artists") // Pide un album a la api de spotify
    Call<ApiResponseGetArtists> getArtists(
            @Query("ids") String artistsIds,
            @Header("Authorization") String authToken
    );

    @GET("v1/search") // Pide una canción a la api de spotify
    Call<ApiResponseSearchArtist> getArtist(
            @Query("q") String trackName,
            @Query("type") String type,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Query("include_external") String includeExternal,
            @Header("Authorization") String authToken
    );
    // END ARTISTS //////////////////////////////////////////////////////////////////


    // ALBUMS //////////////////////////////////////////////////////////////////
    @GET("v1/search") // Pide una canción a la api de spotify
    Call<ApiResponseSearchAlbum> getAlbumByName(
            @Query("q") String albumName,
            @Query("type") String type,
            @Query("limit") int limit,
            @Query("offset") int offset,
            @Header("Authorization") String authToken
    );

    @GET("v1/albums/{id}") // Define the endpoint for retrieving an album by its ID
    Call<Album> getAlbum(
            @Path("id") String albumId, // Use @Path to specify the album ID as a path parameter
            @Query("market") String market,
            @Header("Authorization") String authToken
    );

    // END ALBUMS //////////////////////////////////////////////////////////////////
}
