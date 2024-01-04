package com.example.vibevault.artists;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vibevault.R;

public class ArtistViewHolder  extends RecyclerView.ViewHolder {

    ImageView artistImg;
    TextView artistName, artistFollowers;
    ImageButton artistLike;
    CardView artistCardview;
    public ArtistViewHolder(@NonNull View itemView) {
        super(itemView);
        this.artistImg = itemView.findViewById(R.id.artist_img);
        this.artistName = itemView.findViewById(R.id.artist_name);
        this.artistFollowers = itemView.findViewById(R.id.artist_followers);
        this.artistLike = itemView.findViewById(R.id.artist_like_btn);
        this.artistCardview = itemView.findViewById(R.id.artist_main_container);
    }
}
