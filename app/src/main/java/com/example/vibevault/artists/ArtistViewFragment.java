package com.example.vibevault.artists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.artists.api.ApiResponseGetArtists;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.songs.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistViewFragment extends Fragment implements SelectListener {
    private RecyclerView recyclerView;
    private List<Artist> artist_list;
    private List<Artist> results;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        recyclerView = view.findViewById(R.id.artistsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        setUpAdapter(view.getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        results = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.artists_fragment_layout, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpAdapter(getContext());
    }

    @Override
    public void OnItemClicked(Context context, String name, boolean isFavorite) {
        DataHolder.getInstance().setSavedFragment((1));
        Intent intent = new Intent(context, ArtistViewSolo.class);
        intent.putExtra("ID", name);
        intent.putExtra("isFavorite", isFavorite);
        intent.putExtra("searchingById", true);
        context.startActivity(intent);
    }
    private void setUpAdapter (Context context){
        artist_list = DataHolder.getInstance().getTopArtists();
        ArtistViewAdapter artistViewAdapter = new ArtistViewAdapter(context, artist_list, ArtistViewFragment.this);
        recyclerView.setAdapter(artistViewAdapter);
    }
}