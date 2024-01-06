package com.example.vibevault.artists;

import static com.example.vibevault.firebase.Favorites.deleteFavoriteAlbum;
import static com.example.vibevault.firebase.Favorites.deleteFavoriteArtist;
import static com.example.vibevault.firebase.Favorites.isInFavoritesAlbums;
import static com.example.vibevault.firebase.Favorites.isInFavoritesArtist;
import static com.example.vibevault.firebase.Favorites.saveFavoriteAlbum;
import static com.example.vibevault.firebase.Favorites.saveFavoriteArtist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.songs.Song;

import java.util.List;
import java.util.Locale;

public class ArtistViewAdapter extends RecyclerView.Adapter<ArtistViewHolder> {
    private Context context;

    private List<Artist> artistList;

    private SelectListener listener;

    public ArtistViewAdapter(Context context, List<Artist> artistList, SelectListener listener) {
        this.context = context;
        this.artistList = artistList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistViewHolder(LayoutInflater.from(context).inflate(R.layout.artist_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist artist = artistList.get(position);
        final String artistName = artistList.get(position).getName();

        if(isInFavoritesArtist(artist.getId())) artist.setFavourite(true);

        holder.artistName.setText(artistName);
        Glide.with(context).load(artistList.get(position).getArtistProfilePic(1)).into(holder.artistImg);
        holder.artistFollowers.setText("Seguidores: " + String.valueOf(artistList.get(position).getFollowersCount()));

        if (artist.isFavourite()) holder.artistLike.setImageResource(R.drawable.filledheart_icon);
        else holder.artistLike.setImageResource(R.drawable.favorite_icon);

        holder.artistCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClicked(v.getContext(), artistName);
            }
        });

        holder.artistLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (artist.isFavourite()) {
                    deleteFavoriteArtist(artist);
                    artist.setFavourite(false);
                    holder.artistLike.setImageResource(R.drawable.favorite_icon);
                } else {
                    saveFavoriteArtist(artist);
                    artist.setFavourite(true);
                    holder.artistLike.setImageResource(R.drawable.filledheart_icon);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }
}
