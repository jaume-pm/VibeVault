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
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.artistsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

        if(artist_list.isEmpty()){
            try {
                List<Song> s = DataHolder.getInstance().getTopSongs();
                Set<String> notSame = new HashSet<>();
                String artistIds = "";
                for(int i = 0; i < 25; i++){
                    for(Artist a : s.get(i).getArtists()){
                        if (!notSame.contains(a.getId())) {
                            artistIds = artistIds + "," + a.getId();
                            notSame.add(a.getId());
                        }
                    }
                }
                artistIds = artistIds.substring(1);

                Retrofit retrofitAPI = new Retrofit.Builder()
                        .baseUrl("https://api.spotify.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                SpotifyAPIService spotifyAPIServiceArtists = retrofitAPI.create(SpotifyAPIService.class);
                // Realiza la llamada a la API de Spotify para obtener las canciones
                String authToken = "Bearer " + DataHolder.getInstance().getAccess_token();
                Call<ApiResponseGetArtists> callArtists = spotifyAPIServiceArtists.getArtists(artistIds, authToken);
                callArtists.enqueue(new Callback<ApiResponseGetArtists>() {
                    @Override
                    public void onResponse(Call<ApiResponseGetArtists> call, Response<ApiResponseGetArtists> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            ApiResponseGetArtists apiResponseGetArtists = response.body();

                            artist_list = apiResponseGetArtists.getArtists();

                            artist_list.sort(Comparator.comparingInt(Artist::getFollowersCount).reversed());

                            DataHolder.getInstance().setTopArtists(artist_list);
                            setUpAdapter(context);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponseGetArtists> call, Throwable throwable) {
                        Toast.makeText(context, "No va", Toast.LENGTH_SHORT).show();
                        Log.e("error", throwable.toString());
                        // Manejar error
                    }
                });
            } catch (Exception e){
                Toast.makeText(context, "No se ha podido obtener artistas de la API", Toast.LENGTH_SHORT).show();
            }
        }
        else setUpAdapter(view.getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artist_list = new ArrayList<>();
        results = new ArrayList<>();

        if(!DataHolder.getInstance().getTopArtists().isEmpty()) artist_list = DataHolder.getInstance().getTopArtists();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.artists_fragment_layout, container, false);
    }



    @Override
    public void OnItemClicked(Context context, String name, boolean isFavorite) {
        DataHolder.getInstance().setSavedFragment((1));
        Intent intent = new Intent(context, ArtistViewSolo.class);
        intent.putExtra("NAME", name);
        intent.putExtra("isFavorite", isFavorite);
        context.startActivity(intent);
    }
    private void setUpAdapter (Context context){
        ArtistViewAdapter artistViewAdapter = new ArtistViewAdapter(context, artist_list, ArtistViewFragment.this);
        recyclerView.setAdapter(artistViewAdapter);
    }
}