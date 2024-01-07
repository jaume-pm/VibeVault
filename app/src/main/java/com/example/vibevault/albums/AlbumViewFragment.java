package com.example.vibevault.albums;
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

import com.example.vibevault.APIServicesToken.ApiTokenResponse;
import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.albums.api.ApiResponseGetAlbums;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.interfaces.SpotifyAPIToken;
import com.example.vibevault.songs.api.ApiResponseGetSongs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumViewFragment extends Fragment implements SelectListener {

    private RecyclerView recyclerView;

    private List<Album> album_list;

    private List<Album> results;

    public AlbumViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        recyclerView = view.findViewById(R.id.albumsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        album_list = DataHolder.getInstance().getTopAlbums();
        setUpAdapter(view.getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        album_list = new ArrayList<>();
        results = new ArrayList<>();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.albums_fragment_layout, container, false);
    }

    @Override
    public void OnItemClicked(Context context, String id, boolean isFavorite) {
        DataHolder.getInstance().setSavedFragment(1);
        Intent intent = new Intent(context, AlbumViewSolo.class);
        intent.putExtra("ID", id);
        intent.putExtra("isFavorite", isFavorite);
        intent.putExtra("searchingById", true);
        context.startActivity(intent);
    }

    private void setUpAdapter (Context context){
        AlbumViewAdapter albumViewAdapter = new AlbumViewAdapter(context, album_list, AlbumViewFragment.this);
        recyclerView.setAdapter(albumViewAdapter);
    }

}
