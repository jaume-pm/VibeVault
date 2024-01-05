package com.example.vibevault.albums;

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
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.utilities.ImageBlur;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumViewSolo extends AppCompatActivity {

    private Album album;

    private TextView name, artistsTxt, date, popularity;

    private ImageView albumCover, artistsImg;

    private ImageButton back, addFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solo_album); // Use the correct layout resource ID

        String ID = getIntent().getStringExtra("ID");
        if (ID == null || ID.isEmpty()) {
            Toast.makeText(this, "No hay resultados", Toast.LENGTH_SHORT).show();
            finish(); // Close this activity and return to MainActivity
        } else {
            name = findViewById(R.id.soloAlbumName_txt);
            artistsTxt = findViewById(R.id.soloAlbumArtist_txt);
            date = findViewById(R.id.soloAlbumRelease_txt);

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

            // Make the call to the Spotify API to get album details
            String authToken = "Bearer " + DataHolder.getInstance().getAccess_token();
            spotifyAPIServiceAlbum.getAlbum(ID, authToken).enqueue(new Callback<ApiResponseSearchAlbum>() {
                @Override
                public void onResponse(Call<ApiResponseSearchAlbum> call, Response<ApiResponseSearchAlbum> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ApiResponseSearchAlbum apiResponseSearchAlbum = response.body();
                        album = apiResponseSearchAlbum.getAlbum();

                        if (album != null) {
                            Glide.with(AlbumViewSolo.this).load(album.getImage(0)).into(albumCover); // Use the first image
                            ImageBlur.loadBlurredImage(artistsImg, album.getImage(0), AlbumViewSolo.this, 8.0f);
                            name.setText(album.getName());
                            date.setText(album.getRelease_date());
                            String popuString = "Según Spotify, este álbum tiene una valoración de <b>" + album.getPopularity() + "</b> sobre 100, donde 100 representa la máxima popularidad. La popularidad se calcula mediante un algoritmo que considera el número de reproducciones del álbum y lo reciente que es.";
                            popularity.setText(Html.fromHtml(popuString));
                        }
                    }
                }

                @Override
                public void onFailure(Call<ApiResponseSearchAlbum> call, Throwable throwable) {
                    Toast.makeText(AlbumViewSolo.this, "No hay resultados", Toast.LENGTH_SHORT).show();
                    finish(); // Close this activity and return to MainActivity
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
                        album.setFavourite(false);
                        addFav.setImageResource(R.drawable.favorite_icon);
                    } else {
                        album.setFavourite(true);
                        addFav.setImageResource(R.drawable.filledheart_icon);
                    }
                }
            }
        }
    };
}
