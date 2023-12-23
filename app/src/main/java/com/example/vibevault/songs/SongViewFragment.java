package com.example.vibevault.songs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vibevault.APIServices.ApiResponse;
import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.interfaces.SpotifyAPIService;

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
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.songsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        /*
        if(song_list.isEmpty()) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.spotify.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(DataHolder.getInstance().getOkHttpClient())
                    .build();

            SpotifyAPIService spotifyAPIService = retrofit.create(SpotifyAPIService.class);

            spotifyAPIService.getAllGlobalSongs().enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        results = response.body().getAllSongsResult();
                        for (Song s : results) {
                            if (response.isSuccessful() && response.body() != null) {
                                song_list.add(s);
                                if (song_list.size() == results.size()) { // Verifica si todos los detalles han sido agregados.
                                    // Actualiza el adaptador en el hilo de UI
                                    //poke_list.sort(Comparator.comparingInt(Pokemon::getID));
                                    DataHolder.getInstance().setTopSongsLoaded(song_list);
                                    setUpAdapter(context);
                                }
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                    Toast.makeText(context, "Error al obtener la lista de Pokémon", Toast.LENGTH_SHORT).show();
                    //Log.e("PokemonViewFragment", "Error al obtener la lista de Pokémon", throwable);
                }
            });
        } else setUpAdapter(view.getContext());
        */
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        song_list = new ArrayList<>();
        results = new ArrayList<>();

        if(!DataHolder.getInstance().getTopSongsLoaded().isEmpty()) song_list = DataHolder.getInstance().getTopSongsLoaded();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.songs_fragment_layout, container, false);
    }

    @Override
    public void OnItemClicked(Context context, String id) {
        DataHolder.getInstance().setSavedFragment(0);
        Intent intent = new Intent(context, ViewSoloSong.class);
        intent.putExtra("ID", id);
        context.startActivity(intent);
    }

    private void setUpAdapter (Context context){
        SongViewAdapter songViewAdapter = new SongViewAdapter(context, song_list, SongViewFragment.this);
        recyclerView.setAdapter(songViewAdapter);
    }
}
