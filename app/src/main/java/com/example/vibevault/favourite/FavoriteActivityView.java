package com.example.vibevault.favourite;

import static com.example.vibevault.firebase.Favorites.deleteFavoriteSong;
import static com.example.vibevault.firebase.Favorites.saveFavoriteSong;
import static com.example.vibevault.utilities.SpotifyAPI.getAuthToken;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.SongViewSolo;
import com.example.vibevault.songs.api.ApiResponseSearchSong;
import com.example.vibevault.utilities.ImageBlur;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteActivityView extends AppCompatActivity {

    private ImageButton back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solo_song);

        back = findViewById(R.id.soloSongGoBack_btn);
        back.setOnClickListener(buttonAction);

        String Item = getIntent().getStringExtra("ITEM");

        if (Item == "Songs") {

        } else if (Item == "Albums") {

        } else if (Item == "Artists") {

        }
    }

    View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
