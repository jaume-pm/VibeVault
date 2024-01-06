package com.example.vibevault.songs;

import static com.example.vibevault.firebase.Favorites.deleteFavoriteSong;
import static com.example.vibevault.firebase.Favorites.saveFavoriteSong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.songs.api.ApiResponseGetSongs;
import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.songs.api.ApiResponseSearchSong;
import com.example.vibevault.utilities.ImageBlur;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongViewSolo extends AppCompatActivity {

    private Song song;

    private TextView name, albumTxt, artistsTxt, duration, date, popularity;

    private ImageView albumCover, artistsImg;

    private ImageButton back, addFav, play;

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false, isPLayable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solo_song);

        String NAME = getIntent().getStringExtra("NAME");
        boolean isFavorite = getIntent().getBooleanExtra("isFavorite", false);
        if (NAME == null || NAME.isEmpty()) {
            Toast.makeText(this, "No hay resultados", Toast.LENGTH_SHORT).show();
            finish(); // Cierra esta activity y regresa a MainActivity
        }
        else {

            name = findViewById(R.id.soloSongName_txt);
            albumTxt = findViewById(R.id.soloSongAlbum_txt);
            artistsTxt = findViewById(R.id.soloSongArtist_txt);
            duration = findViewById(R.id.soloSongDuration_txt);
            date = findViewById(R.id.soloSongRelease_txt);
            popularity = findViewById(R.id.soloSongPopularity_txt);

            albumCover = findViewById(R.id.soloSongAlbum_img);
            artistsImg = findViewById(R.id.soloSongArtist_img);

            back = findViewById(R.id.soloSongGoBack_btn);
            addFav = findViewById(R.id.soloSongAddFav_btn);
            play = findViewById(R.id.soloSongPlay_btn);

            back.setOnClickListener(buttonAction);
            addFav.setOnClickListener(buttonAction);
            play.setOnClickListener(buttonAction);

            mediaPlayer = new MediaPlayer();

            Retrofit retrofitAPI = new Retrofit.Builder()
                    .baseUrl("https://api.spotify.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SpotifyAPIService spotifyAPIServiceSongs = retrofitAPI.create(SpotifyAPIService.class);

            // Realiza la llamada a la API de Spotify para obtener las canciones
            String authToken = "Bearer " + DataHolder.getInstance().getAccess_token();

            NAME = NAME.replace(" ", "+");
            spotifyAPIServiceSongs.getSong(NAME, "track", 1, 0, "audio", authToken).enqueue(new Callback<ApiResponseSearchSong>() {
                @Override
                public void onResponse(Call<ApiResponseSearchSong> call, Response<ApiResponseSearchSong> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ApiResponseSearchSong apiResponseSearchSong = response.body();
                        song = apiResponseSearchSong.getTrack();
                        Glide.with(SongViewSolo.this).load(song.getAlbum().getImage(1)).into(albumCover);
                        ImageBlur.loadBlurredImage(artistsImg, song.getAlbum().getImage(1), SongViewSolo.this, 8.0f);
                        name.setText(song.getName());
                        albumTxt.setText(song.getAlbum().getName());
                        date.setText(song.getAlbum().getRelease_date());
                        String popuString = "Según Spotify, esta canción tiene una valoración de <b>" + String.valueOf(song.getPopularity()) + "</b> sobre 100, donde 100 representa la máxima popularidad. La popularidad se calcula mediante un algoritmo que considera el número de reproducciones de la canción y lo recientes que són.";
                        popularity.setText(Html.fromHtml(popuString));

                        song.setFavourite(isFavorite);
                        if(song.isFavourite()) addFav.setImageResource(R.drawable.filledheart_icon);
                        else addFav.setImageResource(R.drawable.favorite_icon);

                        String aux = song.getArtists().get(0).getName();
                        for(int i = 1; i < song.getArtists().size(); i++){
                            aux = aux + ", " + song.getArtists().get(i).getName();
                        }
                        artistsTxt.setText(aux);

                        duration.setText(convertMillisToMinSec(song.getDuration_ms()));
                        // Falta fer el mateix d'album, pero amb artists

                        try {
                            if(song.getPreview_url() != null) {
                                mediaPlayer.setDataSource(song.getPreview_url()); // URL
                                mediaPlayer.prepareAsync();
                                isPLayable = true;

                                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                    @Override
                                    public void onPrepared(MediaPlayer mp) {
                                        // El MediaPlayer está listo, pero no iniciamos la reproducción aquí
                                    }
                                });
                                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mp) {
                                        isPlaying = false;
                                        play.setImageResource(R.drawable.play_icon); // Restableix el botó
                                    }
                                });
                            } else throw new IOException();
                        } catch (IOException e) {
                            isPLayable = false;
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseSearchSong> call, Throwable throwable) {
                    Toast.makeText(SongViewSolo.this, "No hay resultados", Toast.LENGTH_SHORT).show();
                    finish(); // Cierra esta activity y regresa a MainActivity
                }
            });
        }
    }

    View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == back.getId()) finish();
            else if(id == addFav.getId()){
                if(song.isFavourite()){
                    deleteFavoriteSong(song);
                    song.setFavourite(false);
                    addFav.setImageResource(R.drawable.favorite_icon);
                } else{
                    saveFavoriteSong(song);
                    song.setFavourite(true);
                    addFav.setImageResource(R.drawable.filledheart_icon);
                }

                //Toast.makeText(SongViewSolo.this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
            }
            else {
                if(isPLayable){
                    if (!isPlaying) {
                        mediaPlayer.start();
                        isPlaying = true;
                        play.setImageResource(R.drawable.pause_icon);
                    } else{
                        mediaPlayer.pause();
                        isPlaying = false;
                        play.setImageResource(R.drawable.play_icon);
                    }
                } else Toast.makeText(SongViewSolo.this, "Esta canción no se puede reproducir", Toast.LENGTH_SHORT).show();
            }
        }
    };



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private String convertMillisToMinSec(int millis) {
        int totalSeconds = millis / 1000;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }


}