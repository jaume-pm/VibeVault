package com.example.vibevault.artists;

import static com.example.vibevault.firebase.Favorites.deleteFavoriteArtist;
import static com.example.vibevault.firebase.Favorites.isInFavoritesArtist;
import static com.example.vibevault.firebase.Favorites.saveFavoriteArtist;

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

public class ArtistViewAdapter extends RecyclerView.Adapter<ArtistViewHolder> {
    private final Context context;

    private final List<Artist> artistList;

    private final SelectListener listener;

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
        final String id = artist.getId();

        artist.setFavourite(isInFavoritesArtist(artist.getId()));

        holder.artistName.setText(artist.getName());
        String profilePicUrl = artist.getArtistProfilePic(1);
        if (profilePicUrl != null) {
            Glide.with(context).load(profilePicUrl).into(holder.artistImg);
        }

        holder.artistFollowers.setText("Seguidores: " + artist.getFollowersCount());

        if (artist.isFavourite()) holder.artistLike.setImageResource(R.drawable.filledheart_icon);
        else holder.artistLike.setImageResource(R.drawable.favorite_icon);

        holder.artistCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnItemClicked(v.getContext(), id, artist.isFavourite());
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
