package com.example.vibevault.favourite;

import static com.example.vibevault.firebase.Favorites.deleteFavoriteSong;
import static com.example.vibevault.firebase.Favorites.saveFavoriteSong;
import static com.example.vibevault.utilities.SpotifyAPI.getAuthToken;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.albums.Album;
import com.example.vibevault.albums.AlbumViewAdapter;
import com.example.vibevault.albums.AlbumViewSolo;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.artists.ArtistViewAdapter;
import com.example.vibevault.artists.ArtistViewSolo;
import com.example.vibevault.firebase.Favorites;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.SongViewAdapter;
import com.example.vibevault.songs.SongViewFragment;
import com.example.vibevault.songs.SongViewHolder;
import com.example.vibevault.songs.SongViewSolo;
import com.example.vibevault.songs.api.ApiResponseSearchSong;
import com.example.vibevault.utilities.ImageBlur;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteActivityView extends AppCompatActivity implements SelectListener {

    private Context context;
    private ImageButton back;

    private SelectListener listener;

    private RecyclerView recyclerView;

    private int item;

    private TextView favTxt;


    public FavoriteActivityView() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_favorites);
        recyclerView = findViewById(R.id.favItemRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(FavoriteActivityView.this, 1));
        item = getIntent().getIntExtra("ITEM", 0);
        favTxt = findViewById(R.id.favorite_txt);
        setUpAdapter();

        back = findViewById(R.id.favGoBack_btn);
        back.setOnClickListener(buttonAction);


    }

    private void setUpAdapter (){
        if (item == 0) {
            favTxt.setText("Tus Canciones Favoritas");
            SongViewAdapter songViewAdapter = new SongViewAdapter(FavoriteActivityView.this, Favorites.getFavoriteSongs(), FavoriteActivityView.this);
            recyclerView.setAdapter(songViewAdapter);
        } else if (item == 1) {
            favTxt.setText("Tus Artistas Favoritos");
            ArtistViewAdapter artistViewAdapter = new ArtistViewAdapter(FavoriteActivityView.this, Favorites.getFavoriteArtists(), FavoriteActivityView.this);
            recyclerView.setAdapter(artistViewAdapter);
        } else { // Albums
            favTxt.setText("Tus Albums Favoritos");
            AlbumViewAdapter albumViewAdapter = new AlbumViewAdapter(FavoriteActivityView.this, Favorites.getFavoriteAlbums(), FavoriteActivityView.this);
            recyclerView.setAdapter(albumViewAdapter);
        }
    }

    @Override
    public void OnItemClicked(Context context, String id, boolean isFavorite) {
        Intent intent;
        if (item == 0) {
            intent = new Intent(context, SongViewSolo.class);
        } else if (item == 1) {
            intent = new Intent(context, ArtistViewSolo.class);
        } else { // Albums
            intent = new Intent(context, AlbumViewSolo.class);
        }
        intent.putExtra("ID", id);
        intent.putExtra("isFavorite", isFavorite);
        intent.putExtra("searchingById", true);
        context.startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpAdapter();
    }

    View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}
