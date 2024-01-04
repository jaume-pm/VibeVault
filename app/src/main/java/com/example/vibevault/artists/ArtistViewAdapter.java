package com.example.vibevault.artists;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        final String artistName = artistList.get(position).getName();

        holder.artistName.setText(artistList.get(position).getName().substring(0, 1).toUpperCase() + artistList.get(position).getName().substring(1));
        holder.artistFollowers.setText(String.format(Locale.getDefault(), "%,d Followers", Artist.getFollowers().getTotal()));

        holder.artistCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClicked(v.getContext(), artistName.replace("", "+"));
            }
        });

    }

    @Override
    public int getItemCount() {
        return artistList.size();
    }
}
