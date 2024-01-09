package com.example.vibevault.songs;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vibevault.R;

public class SongViewHolder extends RecyclerView.ViewHolder{

    ImageView songImg;

    TextView name, artists;

    ImageButton like;

    CardView cardView;

    public SongViewHolder(@NonNull View itemView) {
        super(itemView);
        this.songImg = itemView.findViewById(R.id.song_img);
        this.name = itemView.findViewById(R.id.song_name);
        this.artists = itemView.findViewById(R.id.song_singers);
        this.like = itemView.findViewById(R.id.song_like_btn);
        this.cardView = itemView.findViewById(R.id.song_main_container);
    }
}
