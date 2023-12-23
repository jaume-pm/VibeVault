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
    }

    public boolean setUpClientToken () {


        if(access_token == "not set") return false;
        return true;
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

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }
}
