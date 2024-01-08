package com.example.vibevault.utilities;

import android.util.Base64;
import android.util.Log;

import com.example.vibevault.APIServicesToken.ApiTokenResponse;
import com.example.vibevault.DataHolder;
import com.example.vibevault.albums.Album;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.artists.api.ApiResponseGetArtists;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.interfaces.SpotifyAPIToken;
import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.api.ApiResponseGetSongs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpotifyAPI {
    private static boolean isSongsDownloaded = false, isArtistsDownloaded = false, isAlbumsDownloaded = false;
    private static final String clientId = "683fff68e09f4b97a5ded29474b883e2";
    private static final String clientSecret = "a0ab2c15aa684a7287a993468c13ce17";

    private static final String authHeader = "Basic " + Base64.encodeToString((clientId + ":" + clientSecret).getBytes(), Base64.NO_WRAP);
    private static final String grantType = "client_credentials";

    private static final Retrofit retrofitToken = new Retrofit.Builder()
            .baseUrl("https://accounts.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final Retrofit retrofitAPI = new Retrofit.Builder()
            .baseUrl("https://api.spotify.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static String getAuthToken() {
        return authToken;
    }

    private static String authToken;

    public static void getSpotifyToken(AuthTokenCallback callback) {
        SpotifyAPIToken spotifyAPIToken = retrofitToken.create(SpotifyAPIToken.class);
        Call<ApiTokenResponse> call = spotifyAPIToken.getToken(grantType, authHeader);
        call.enqueue(new Callback<ApiTokenResponse>() {
            @Override
            public void onResponse(Call<ApiTokenResponse> call, retrofit2.Response<ApiTokenResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiTokenResponse tokenResponse = response.body();
                    String access_token = tokenResponse.getAccess_token();
                    String token_type = tokenResponse.getToken_type();
                    authToken = token_type + " " + access_token;
                    callback.onAuthTokenReceived(authToken); // Notify the callback
                }
            }

            @Override
            public void onFailure(Call<ApiTokenResponse> call, Throwable t) {
                Log.e("API_ERROR", "Call for getting Spotify auth token failed. " + t.getMessage());
            }
        });
    }


    public static void downloadTopAlbums() {
        SpotifyAPIService spotifyAPIServiceSongs = retrofitAPI.create(SpotifyAPIService.class);
        Call<ApiResponseGetSongs> callSongs = spotifyAPIServiceSongs.getAllGlobalSongs(authToken);
        callSongs.enqueue(new Callback<ApiResponseGetSongs>() {
            @Override
            public void onResponse(Call<ApiResponseGetSongs> call, Response<ApiResponseGetSongs> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGetSongs apiResponseGetSongs = response.body();
                    List<Album> album_list = new ArrayList<>();

                    // Create a Set to store unique albums based on their ids
                    Set<String> uniqueAlbumIds = new HashSet<>();

                    for (ApiResponseGetSongs.ItemsSong i : apiResponseGetSongs.getTracks()) {
                        Album currentAlbum = i.track.getAlbum();

                        // Check if the album's id is already in the Set
                        if (!uniqueAlbumIds.contains(currentAlbum.getId())) {
                            // If not, add the album to the list and the id to the Set
                            album_list.add(currentAlbum);
                            uniqueAlbumIds.add(currentAlbum.getId());
                        }
                    }
                    DataHolder.getInstance().setTopAlbums(album_list);
                    isAlbumsDownloaded = true;
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGetSongs> call, Throwable throwable) {
                Log.e("API_ERROR", "Call for getting Spotify top albums failed. " + throwable.getMessage());
            }
        });
    }

    public static void downloadTopSongsAndArtists() {
        SpotifyAPIService spotifyAPIServiceSongs = retrofitAPI.create(SpotifyAPIService.class);
        Call<ApiResponseGetSongs> callSongs = spotifyAPIServiceSongs.getAllGlobalSongs(authToken);
        callSongs.enqueue(new Callback<ApiResponseGetSongs>() {
            @Override
            public void onResponse(Call<ApiResponseGetSongs> call, Response<ApiResponseGetSongs> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponseGetSongs apiResponseGetSongs = response.body();
                    List<Song> song_list = new ArrayList<>();

                    for (ApiResponseGetSongs.ItemsSong i : apiResponseGetSongs.getTracks()) {
                        song_list.add(i.track);
                    }

                    DataHolder.getInstance().setTopSongs(song_list);
                    isSongsDownloaded = true;
                    downloadTopArtists();
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGetSongs> call, Throwable throwable) {
                Log.e("API_ERROR", "Call for getting Spotify top songs failed. " + throwable.getMessage());
            }
        });
    }

    private static String getTopSongsArtistsIds(){
        List<Song> s = DataHolder.getInstance().getTopSongs();
        Set<String> notSame = new HashSet<>();
        String artistIds = "";
        for(int i = 0; i < 25; i++){
            for(Artist a : s.get(i).getArtists()){
                if (!notSame.contains(a.getId())) {
                    artistIds = artistIds + "," + a.getId();
                    notSame.add(a.getId());
                }
            }
        }
        artistIds = artistIds.substring(1);
        return artistIds;
    }

    public static void downloadTopArtists() {
        SpotifyAPIService spotifyAPIServiceArtists = retrofitAPI.create(SpotifyAPIService.class);
        String artistIds = getTopSongsArtistsIds();
        Call<ApiResponseGetArtists> callArtists = spotifyAPIServiceArtists.getArtists(artistIds, authToken);
        callArtists.enqueue(new Callback<ApiResponseGetArtists>() {
            @Override
            public void onResponse(Call<ApiResponseGetArtists> call, Response<ApiResponseGetArtists> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Artist> artist_list = new ArrayList<>();
                    ApiResponseGetArtists apiResponseGetArtists = response.body();

                    artist_list = apiResponseGetArtists.getArtists();

                    artist_list.sort(Comparator.comparingInt(Artist::getFollowersCount).reversed());

                    DataHolder.getInstance().setTopArtists(artist_list);
                    isArtistsDownloaded = true;
                }
            }

            @Override
            public void onFailure(Call<ApiResponseGetArtists> call, Throwable throwable) {
                Log.e("API_ERROR", "Call for getting Spotify top songs failed. " + throwable.getMessage());
            }
        });
    }
    public static boolean isSpotifyInfoDownloaded(){
        return isAlbumsDownloaded && isArtistsDownloaded && isSongsDownloaded;
    }

    public interface AuthTokenCallback {
        void onAuthTokenReceived(String authToken);
    }

}
