package com.example.vibevault.favourite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.vibevault.APIServicesToken.ApiTokenResponse;
import com.example.vibevault.DataHolder;
import com.example.vibevault.MainActivity;
import com.example.vibevault.R;
import com.example.vibevault.albums.AlbumViewSolo;
import com.example.vibevault.artists.ArtistViewSolo;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.interfaces.SpotifyAPIToken;
import com.example.vibevault.songs.SongViewSolo;
import com.example.vibevault.songs.api.ApiResponseGetSongs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                intent = new Intent(v.getContext(), SongViewSolo.class);
            }else if (id == artists.getId()){ // Artists
                intent = new Intent(v.getContext(), ArtistViewSolo.class);
            }
            else { // Albums
                intent = new Intent(v.getContext(), AlbumViewSolo.class);
            }
            intent.putExtra("NAME", "vfgagvagvagaqsgfwsfw");
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
