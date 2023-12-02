package com.example.vibevault;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton searchBtn, songsBtn, albumsBtn, artistsBtn, favsBtn;

    private TextView songsTxt, albumsTxt, artistsTxt, favsTxt;

    private EditText searchInp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBtn = (ImageButton) findViewById(R.id.main_search_btn);
        songsBtn = (ImageButton) findViewById(R.id.main_songs_btn);
        albumsBtn = (ImageButton) findViewById(R.id.main_albums_btn);
        artistsBtn = (ImageButton) findViewById(R.id.main_artists_btn);
        favsBtn = (ImageButton) findViewById(R.id.main_fav_btn);

        searchBtn.setOnClickListener(buttonAction);
        songsBtn.setOnClickListener(buttonAction);
        albumsBtn.setOnClickListener(buttonAction);
        artistsBtn.setOnClickListener(buttonAction);
        favsBtn.setOnClickListener(buttonAction);

        songsTxt = (TextView) findViewById(R.id.main_songs_txt);
        albumsTxt = (TextView) findViewById(R.id.main_albums_txt);
        artistsTxt = (TextView) findViewById(R.id.main_artists_txt);
        favsTxt = (TextView) findViewById(R.id.main_fav_txt);

        songsBtn.setSelected(true);
        songsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));

        // Utilitzarem això per guardar el fragrment que estem utilitzant
        // DataHolder.getInstance().getSavedFragment();
    }

    protected View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == searchBtn.getId()){

            } else if(id == songsBtn.getId()){
                songsBtn.setSelected(true);
                albumsBtn.setSelected(false);
                artistsBtn.setSelected(false);
                favsBtn.setSelected(false);
                songsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                albumsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                artistsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                favsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
            } else if (id == albumsBtn.getId()) {
                songsBtn.setSelected(false);
                albumsBtn.setSelected(true);
                artistsBtn.setSelected(false);
                favsBtn.setSelected(false);
                songsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                albumsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                artistsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                favsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
            } else if (id == artistsBtn.getId()) {
                songsBtn.setSelected(false);
                albumsBtn.setSelected(false);
                artistsBtn.setSelected(true);
                favsBtn.setSelected(false);
                songsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                albumsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                artistsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                favsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
            } else { // Boton de favoritos
                songsBtn.setSelected(false);
                albumsBtn.setSelected(false);
                artistsBtn.setSelected(false);
                favsBtn.setSelected(true);
                songsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                albumsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                artistsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.spotify_light_grey));
                favsTxt.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
            }
        }
    };
}