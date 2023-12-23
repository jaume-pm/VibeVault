package com.example.vibevault;

import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.vibevault.APIServices.ApiTokenResponse;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.songs.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DataHolder {
    private static final DataHolder INSTANCE = new DataHolder();
    private int savedFragment;
    private Song holdedSong;

    private List<Song> topSongsLoaded;

    private List<Song> favSongsLoaded;

    private String access_token;

    private String token_type;

    // Se podria usar para comprobar si el token ha caducado, si es asi hacer un refresh
    // private int expires_in; (de momento no se usa)

    private DataHolder() {
        savedFragment = 0;
        topSongsLoaded = new ArrayList<>();
        favSongsLoaded = new ArrayList<>();

        String clientId = "683fff68e09f4b97a5ded29474b883e2";
        String clientSecret = "a0ab2c15aa684a7287a993468c13ce17";

        String authHeader = "Basic " + Base64.encodeToString((clientId + ":" + clientSecret).getBytes(), Base64.NO_WRAP);
        String grantType = "client_credentials";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(("https://accounts.spotify.com/"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyAPIService service = retrofit.create(SpotifyAPIService.class);
        Call<ApiTokenResponse> call = service.getToken(grantType, authHeader);

        call.enqueue(new Callback<ApiTokenResponse>() {
            @Override
            public void onResponse(Call<ApiTokenResponse> call, retrofit2.Response<ApiTokenResponse> response) {
                if (response.isSuccessful()) {
                    ApiTokenResponse tokenResponse = response.body();
                    access_token = tokenResponse.getAccess_token();
                    token_type = tokenResponse.getToken_type();
                    // expires_in = tokenResponse.getExpires_in();
                } else {
                    Log.e("API_ERROR", "Llamada para obtener token fallida (DataHolder/else)");
                }
            }

            @Override
            public void onFailure(Call<ApiTokenResponse> call, Throwable t) {
                Log.e("API_ERROR", "Llamada para obtener token fallida (DataHolder/onFailure) " + t);
            }
        });

    }
    public static DataHolder getInstance() {
        return INSTANCE;
    }

    public int getSavedFragment() {
        return savedFragment;
    }

    public void setSavedFragment(int savedFragment) {
        this.savedFragment = savedFragment;
    }

    public Song getHoldedSong() {
        return holdedSong;
    }

    public void setHoldedSong(Song holdedSong) {
        this.holdedSong = holdedSong;
    }

    public List<Song> getTopSongsLoaded() {
        return topSongsLoaded;
    }

    public void setTopSongsLoaded(List<Song> topSongsLoaded) {
        this.topSongsLoaded = topSongsLoaded;
    }

    public List<Song> getFavSongsLoaded() {
        return favSongsLoaded;
    }

    public void setFavSongsLoaded(List<Song> favSongsLoaded) {
        this.favSongsLoaded = favSongsLoaded;
    }
}
