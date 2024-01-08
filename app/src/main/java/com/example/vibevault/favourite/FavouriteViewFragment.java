package com.example.vibevault.favourite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.vibevault.R;

public class FavouriteViewFragment extends Fragment {
    private ImageButton songs, artists, albums;

    public FavouriteViewFragment(){}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        Context context = view.getContext();
        songs = view.findViewById(R.id.favSongs_btn);
        artists = view.findViewById(R.id.favArtists_btn);
        albums = view.findViewById(R.id.favAlbums_btn);

        songs.setOnClickListener(buttonAction);
        artists.setOnClickListener(buttonAction);
        albums.setOnClickListener(buttonAction);
    }

    View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            Intent intent;
            if(id == songs.getId()) { // Songs
                intent = new Intent(v.getContext(), FavoriteActivityView.class);
                intent.putExtra("ITEM", 0);
            }else if (id == artists.getId()){ // Artists
                intent = new Intent(v.getContext(), FavoriteActivityView.class);
                intent.putExtra("ITEM", 1);
            }
            else { // Albums
                intent = new Intent(v.getContext(), FavoriteActivityView.class);
                intent.putExtra("ITEM", 2);
            }
            startActivity(intent);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.favourite_global_fragment_layout, container, false);
    }
}
