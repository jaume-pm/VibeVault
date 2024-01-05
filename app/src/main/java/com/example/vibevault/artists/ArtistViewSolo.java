package com.example.vibevault.artists;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.artists.api.ApiResponseSearchArtist;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.songs.SongViewSolo;
import com.example.vibevault.songs.api.ApiResponseSearchSong;
import com.example.vibevault.utilities.ImageBlur;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistViewSolo extends AppCompatActivity {
    private Artist artist;
    private TextView name, followers, genres, popularity;
    private ImageView artistImg, artistBackgroundImg;
    private ImageButton back, addFav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solo_artist);

        String NAME = getIntent().getStringExtra("NAME");
        Log.e("ERROR", NAME);
        if (NAME == null || NAME.isEmpty()) {
            Toast.makeText(this, "No hay resultados", Toast.LENGTH_SHORT).show();
            finish(); // Cierra esta activity y regresa a MainActivity
        }
        else {
            name = findViewById(R.id.soloArtistName_txt);
            followers = findViewById(R.id.soloArtist_txt);
            genres = findViewById(R.id.soloArtistGenres_txt);
            popularity = findViewById(R.id.soloArtistPopularity_txt);

            artistImg = findViewById(R.id.soloArtist_img);
            artistBackgroundImg = findViewById(R.id.soloArtistBckg_img);

            back = findViewById(R.id.soloArtistGoBack_btn);
            addFav = findViewById(R.id.soloArtistAddFav_btn);

            back.setOnClickListener(buttonAction);
            addFav.setOnClickListener(buttonAction);

            Retrofit retrofitAPI = new Retrofit.Builder()
                    .baseUrl("https://api.spotify.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SpotifyAPIService spotifyAPIServiceArtists = retrofitAPI.create(SpotifyAPIService.class);

            // Realiza la llamada a la API de Spotify para obtener las canciones
            String authToken = "Bearer " + DataHolder.getInstance().getAccess_token();

            spotifyAPIServiceArtists.getArtist(NAME, "artist", 1, 0, "", authToken).enqueue(new Callback<ApiResponseSearchArtist>() {

                @Override
                public void onResponse(Call<ApiResponseSearchArtist> call, Response<ApiResponseSearchArtist> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ApiResponseSearchArtist apiResponseSearchArtist = response.body();
                        artist = apiResponseSearchArtist.getArtist();
                        Glide.with(ArtistViewSolo.this).load(artist.getArtistProfilePic(1)).into(artistImg);
                        ImageBlur.loadBlurredImage(artistBackgroundImg, artist.getArtistProfilePic(1), ArtistViewSolo.this, 8.0f);
                        name.setText(artist.getName());
                        followers.setText("Seguidores: " + String.valueOf(artist.getFollowersCount()));

                        String aux = artist.getGenres().get(0).toUpperCase();
                        for (int i = 1; i < artist.getGenres().size(); i++){
                            aux = aux + ", " + artist.getGenres().get(i).toUpperCase();
                        }
                        genres.setText(aux);

                        String popuString = "Según Spotify, este artista tiene una valoración de <b>" + String.valueOf(artist.getPopularity()) + "</b> sobre 100, donde 100 representa la máxima popularidad. La popularidad se calcula mediante un algoritmo que considera el número de reproducciones de la canción y lo recientes que són.";
                        popularity.setText((Html.fromHtml(popuString)));
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseSearchArtist> call, Throwable t) {
                    Toast.makeText(ArtistViewSolo.this, "No hay resultados", Toast.LENGTH_SHORT).show();
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
                if(artist.isFavourite()){
                    artist.setFavourite(false);
                    addFav.setImageResource(R.drawable.favorite_icon);
                } else{
                    artist.setFavourite(true);
                    addFav.setImageResource(R.drawable.filledheart_icon);
                }
            }
        }
    };
}
