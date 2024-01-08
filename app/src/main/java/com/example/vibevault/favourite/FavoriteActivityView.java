package com.example.vibevault.favourite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vibevault.R;
import com.example.vibevault.albums.AlbumViewAdapter;
import com.example.vibevault.albums.AlbumViewSolo;
import com.example.vibevault.artists.ArtistViewAdapter;
import com.example.vibevault.artists.ArtistViewSolo;
import com.example.vibevault.firebase.Favorites;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.songs.SongViewAdapter;
import com.example.vibevault.songs.SongViewSolo;

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
