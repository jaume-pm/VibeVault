package com.example.vibevault.artists;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vibevault.R;

public class ArtistViewSolo extends AppCompatActivity {
    private Artist artist;
    private TextView name, followers, genres, popularity;
    private ImageView artistImg;
    private ImageButton back, addFav;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_solo_artist);

        String NAME = getIntent().getStringExtra("NAME");
        if (NAME == null || NAME.isEmpty()) {
            Toast.makeText(this, "No hay resultados", Toast.LENGTH_SHORT).show();
            finish(); // Cierra esta activity y regresa a MainActivity
        }
    }
}
