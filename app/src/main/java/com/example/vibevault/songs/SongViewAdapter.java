package com.example.vibevault.songs;

import static com.example.vibevault.firebase.Favorites.deleteFavoriteAlbum;
import static com.example.vibevault.firebase.Favorites.deleteFavoriteSong;
import static com.example.vibevault.firebase.Favorites.isInFavoritesAlbums;
import static com.example.vibevault.firebase.Favorites.isInFavoritesSongs;
import static com.example.vibevault.firebase.Favorites.saveFavoriteAlbum;
import static com.example.vibevault.firebase.Favorites.saveFavoriteSong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vibevault.R;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.interfaces.SelectListener;

import java.util.List;

public class SongViewAdapter extends RecyclerView.Adapter<SongViewHolder>{

    private final Context context;

    private final List<Song> songList;

    private final SelectListener listener;

    public SongViewAdapter(Context context, List<Song> songList, SelectListener listener) {
        this.context = context;
        this.songList = songList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SongViewHolder(LayoutInflater.from(context).inflate(R.layout.song_rv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songList.get(position);
        final String id = song.getId();

        song.setFavourite(isInFavoritesSongs(song.getId()));

        holder.name.setText(songList.get(position).getName());
        String aux = "";
        for(Artist a : songList.get(position).getArtists()) {
            aux = aux + a.getName() + ", ";
        }
        holder.artists.setText(aux.substring(0, aux.length() - 2));
        Glide.with(context).load(songList.get(position).getAlbumCover(2)).into(holder.songImg);

        if (song.isFavourite()) holder.like.setImageResource(R.drawable.filledheart_icon);
        else holder.like.setImageResource(R.drawable.favorite_icon);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClicked(v.getContext(), id, song.isFavourite());
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song.isFavourite()) {
                    deleteFavoriteSong(song);
                    song.setFavourite(false);
                    holder.like.setImageResource(R.drawable.favorite_icon);
                } else {
                    saveFavoriteSong(song);
                    song.setFavourite(true);
                    holder.like.setImageResource(R.drawable.filledheart_icon);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}
