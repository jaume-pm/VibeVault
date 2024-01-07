package com.example.vibevault.albums;

import static com.example.vibevault.firebase.Favorites.deleteFavoriteAlbum;
import static com.example.vibevault.firebase.Favorites.saveFavoriteAlbum;
import static com.example.vibevault.utilities.SpotifyAPI.getAuthToken;

import com.example.vibevault.firebase.Favorites;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.albums.api.ApiResponseSearchAlbum;
import com.example.vibevault.firebase.Favorites;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.utilities.ImageBlur;

import io.grpc.Context;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumViewSolo extends AppCompatActivity {

    private Album album;

    private TextView name, artistsTxt, date, popularity, type, total_tracks;

    private ImageView albumCover, artistsImg;

    private ImageButton back, addFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solo_album); // Use the correct layout resource ID

        String ID = getIntent().getStringExtra("ID");
        boolean isFavorite = getIntent().getBooleanExtra("isFavorite", false);
        if (ID == null || ID.isEmpty()) {
            Toast.makeText(this, "No hay resultados", Toast.LENGTH_SHORT).show();
            finish(); // Close this activity and return to MainActivity
        } else {
            name = findViewById(R.id.soloAlbumName_txt);
            artistsTxt = findViewById(R.id.soloAlbumArtist_txt);
            date = findViewById(R.id.soloAlbumRelease_txt);
            type = findViewById(R.id.soloAlbumType_txt);
            total_tracks = findViewById(R.id.soloAlbumTotalTracks_txt);


            albumCover = findViewById(R.id.soloAlbum_img);
            artistsImg = findViewById(R.id.soloAlbumArtist_img);

            popularity = findViewById(R.id.soloAlbumPopularity_txt);

            back = findViewById(R.id.soloAlbumGoBack_btn);
            addFav = findViewById(R.id.soloAlbumAddFav_btn);

            back.setOnClickListener(buttonAction);
            addFav.setOnClickListener(buttonAction);

            Retrofit retrofitAPI = new Retrofit.Builder()
                    .baseUrl("https://api.spotify.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SpotifyAPIService spotifyAPIServiceAlbum = retrofitAPI.create(SpotifyAPIService.class);

            spotifyAPIServiceAlbum.getAlbum(ID, "ES", getAuthToken()).enqueue(new Callback<Album>() {
                @Override
                public void onResponse(Call<Album> call, Response<Album> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        album = response.body();

                        // Now you can work with the 'album' object directly
                        // For example, access album properties like album.getId(), album.getName(), etc.

                        if (album != null) {
                            Glide.with(AlbumViewSolo.this).load(album.getImage(0)).into(albumCover);
                            ImageBlur.loadBlurredImage(artistsImg, album.getImage(0), AlbumViewSolo.this, 8.0f);
                            name.setText(album.getName());
                            date.setText(album.getRelease_date());
                            total_tracks.setText(String.valueOf(album.getTotal_tracks()));
                            type.setText(album.getAlbum_type());
                            String popuString = "Según Spotify, este álbum tiene una valoración de <b>" + album.getPopularity() + "</b> sobre 100, donde 100 representa la máxima popularidad. La popularidad se calcula mediante un algoritmo que considera el número de reproducciones del álbum y lo reciente que es.";
                            popularity.setText(Html.fromHtml(popuString));
                            album.setFavourite(isFavorite);
                            if(album.isFavourite()) addFav.setImageResource(R.drawable.filledheart_icon);
                            else addFav.setImageResource(R.drawable.favorite_icon);
                        }
                    }
                }

                @Override
                public void onFailure(Call<Album> call, Throwable throwable) {
                    Toast.makeText(AlbumViewSolo.this, "No hay resultados", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        }
    }

    View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == back.getId()) finish();
            else if (id == addFav.getId()) {
                if (album != null) {
                    if (album.isFavourite()) {
                        deleteFavoriteAlbum(album);
                        album.setFavourite(false);
                        addFav.setImageResource(R.drawable.favorite_icon);
                    } else {
                        saveFavoriteAlbum(album);
                        album.setFavourite(true);
                        addFav.setImageResource(R.drawable.filledheart_icon);
                    }
                }
            }
        }
    };
}
