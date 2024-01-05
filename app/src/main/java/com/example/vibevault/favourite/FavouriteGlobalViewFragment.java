package com.example.vibevault.favourite;

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
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.interfaces.SpotifyAPIToken;
import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.SongViewAdapter;
import com.example.vibevault.songs.SongViewFragment;
import com.example.vibevault.songs.SongViewSolo;
import com.example.vibevault.songs.api.ApiResponseGetSongs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavouriteGlobalViewFragment extends Fragment {
    private RecyclerView recyclerView;

    public FavouriteGlobalViewFragment() {
        // Required empty public constructor
    }

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
