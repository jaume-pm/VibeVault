package com.example.vibevault.songs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SelectListener;

import java.util.List;

public class SongViewAdapter extends RecyclerView.Adapter<SongViewHolder>{

    private Context context;

    private List<Song> songList;

    private SelectListener listener;

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
        final String name = songList.get(position).getName();

        holder.name.setText(songList.get(position).getName().substring(0, 1).toUpperCase() + songList.get(position).getName().substring(1));
        String aux = "";
        for(Song.Artists a : songList.get(position).getArtists()) {
            aux = aux + a.name + ", ";
        }
        holder.artists.setText(aux.substring(0, aux.length() - 2));
        Glide.with(context).load(songList.get(position).getAlbumCover(2)).into(holder.songImg);
        //holder.like.setBackground();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClicked(v.getContext(), name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songList.size();
    }
}
