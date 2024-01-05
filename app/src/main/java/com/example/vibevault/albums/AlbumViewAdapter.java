package com.example.vibevault.albums;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.albums.Album;
import com.example.vibevault.albums.AlbumViewHolder;
import com.example.vibevault.songs.SongViewHolder;

import java.util.List;

public class AlbumViewAdapter extends RecyclerView.Adapter<AlbumViewHolder>{
    private Context context;

    private List<Album> albumList;

    private SelectListener listener;

    public AlbumViewAdapter(Context context, List<Album> albumList, SelectListener listener) {
        this.context = context;
        this.albumList = albumList;
        this.listener = listener;
    }

    @NonNull
    public AlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(LayoutInflater.from(context).inflate(R.layout.album_rv, parent, false));
    }


    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        final String name = albumList.get(position).getName();

        holder.name.setText(albumList.get(position).getName().substring(0, 1).toUpperCase() + albumList.get(position).getName().substring(1));
        String aux = "";
        for(Album.Artist a : albumList.get(position).getArtists()) {
            aux = aux + a.name + ", ";
        }
        holder.artists.setText(aux.substring(0, aux.length() - 2));
        Glide.with(context).load(albumList.get(position).getImage(2)).into(holder.albumImg);
        //holder.like.setBackground();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClicked(v.getContext(), name);
            }
        });
    }


    public int getItemCount() {
        return albumList.size();
    }
}


