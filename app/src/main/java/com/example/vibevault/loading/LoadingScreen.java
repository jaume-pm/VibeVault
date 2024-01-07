package com.example.vibevault.loading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vibevault.MainActivity;
import com.example.vibevault.R;

import com.example.vibevault.firebase.Favorites;
import com.example.vibevault.utilities.SpotifyAPI;

public class LoadingScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
        ImageView gif = findViewById(R.id.gifimg);
        Glide.with(this).load(R.drawable.loading).into(gif);

        Favorites.downloadFavoritesAlbums();
        Favorites.downloadFavoritesArtists();
        Favorites.downloadFavoritesSongs();

        SpotifyAPI.getSpotifyToken(new SpotifyAPI.AuthTokenCallback() {
            @Override
            public void onAuthTokenReceived(String authToken) {
                // Auth token received, continue with other operations
                SpotifyAPI.downloadTopSongsAndArtists();
                SpotifyAPI.downloadTopAlbums();
            }
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // Check if all data has been downloaded
                if (Favorites.isFavoritesDownloaded() && SpotifyAPI.isSpotifyInfoDownloaded()) {
                    startActivity(new Intent(LoadingScreen.this, MainActivity.class));
                    finish();
                } else {
                    // Schedule the next execution
                    handler.postDelayed(this, 200);
                }
            }
        };
// Initial schedule to start checking after 1 second
        handler.postDelayed(runnable, 2000);
    }
}
