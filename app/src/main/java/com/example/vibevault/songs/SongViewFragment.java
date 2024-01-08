package com.example.vibevault.songs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vibevault.albums.Album;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.firebase.Favorites;
import com.example.vibevault.songs.api.ApiResponseGetSongs;
import com.example.vibevault.APIServicesToken.ApiTokenResponse;
import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.interfaces.SpotifyAPIToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongViewFragment extends Fragment implements SelectListener {
    private RecyclerView recyclerView;

    private List<Song> song_list;

    private List<Song> results;

    public SongViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        recyclerView = view.findViewById(R.id.songsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        setUpAdapter(view.getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        song_list = new ArrayList<>();
        results = new ArrayList<>();
        if(!DataHolder.getInstance().getTopSongs().isEmpty()) song_list = DataHolder.getInstance().getTopSongs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.songs_fragment_layout, container, false);
    }

    @Override
    public void OnItemClicked(Context context, String id, boolean isFavorite) {
        DataHolder.getInstance().setSavedFragment(0);
        Intent intent = new Intent(context, SongViewSolo.class);
        intent.putExtra("ID", id);
        intent.putExtra("isFavorite", isFavorite);
        intent.putExtra("searchingById", true);
        context.startActivity(intent);
    }

    private void setUpAdapter (Context context){
        SongViewAdapter songViewAdapter = new SongViewAdapter(context, song_list, SongViewFragment.this);
        recyclerView.setAdapter(songViewAdapter);
    }
}
