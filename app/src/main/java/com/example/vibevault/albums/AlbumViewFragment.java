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
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.albumsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

        if(album_list.isEmpty()) {

            String clientId = "683fff68e09f4b97a5ded29474b883e2";
            String clientSecret = "a0ab2c15aa684a7287a993468c13ce17";

            String authHeader = "Basic " + Base64.encodeToString((clientId + ":" + clientSecret).getBytes(), Base64.NO_WRAP);
            String grantType = "client_credentials";

            Retrofit retrofitToken = new Retrofit.Builder()
                    .baseUrl("https://accounts.spotify.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Retrofit retrofitAPI = new Retrofit.Builder()
                    .baseUrl("https://api.spotify.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            SpotifyAPIToken spotifyAPIToken = retrofitToken.create(SpotifyAPIToken.class);

            SpotifyAPIService spotifyAPIServiceSongs = retrofitAPI.create(SpotifyAPIService.class);

            Call<ApiTokenResponse> call = spotifyAPIToken.getToken(grantType, authHeader);
            call.enqueue(new Callback<ApiTokenResponse>() {
                @Override
                public void onResponse(Call<ApiTokenResponse> call, retrofit2.Response<ApiTokenResponse> response) {
                    if (response.isSuccessful()) {
                        ApiTokenResponse tokenResponse = response.body();
                        DataHolder.getInstance().setAccess_token(tokenResponse.getAccess_token());
                        DataHolder.getInstance().setToken_type(tokenResponse.getToken_type());

                        // Realiza la llamada a la API de Spotify para obtener las canciones
                        String authToken = "Bearer " + DataHolder.getInstance().getAccess_token();
                        Call<ApiResponseGetSongs> callSongs = spotifyAPIServiceSongs.getAllGlobalSongs(authToken);
                        callSongs.enqueue(new Callback<ApiResponseGetSongs>() {
                            @Override
                            public void onResponse(Call<ApiResponseGetSongs> call, Response<ApiResponseGetSongs> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    ApiResponseGetSongs apiResponseGetSongs = response.body();

                                    // Create a Set to store unique albums based on their ids
                                    Set<String> uniqueAlbumIds = new HashSet<>();

                                    for (ApiResponseGetSongs.ItemsSong i : apiResponseGetSongs.getTracks()) {
                                        Album currentAlbum = i.track.getAlbum();

                                        // Check if the album's id is already in the Set
                                        if (!uniqueAlbumIds.contains(currentAlbum.getId())) {
                                            // If not, add the album to the list and the id to the Set
                                            album_list.add(currentAlbum);
                                            uniqueAlbumIds.add(currentAlbum.getId());
                                        }
                                    }
                                    DataHolder.getInstance().setTopAlbums(album_list);
                                    setUpAdapter(context);
                                }
                            }


                            @Override
                            public void onFailure(Call<ApiResponseGetSongs> call, Throwable throwable) {
                                // Manejar error
                            }
                        });
                    } else {
                        Log.e("API_ERROR", "Llamada para obtener token fallida (DataHolder/tokenCall/else)");
                    }
                }

                @Override
                public void onFailure(Call<ApiTokenResponse> call, Throwable t) {
                    Log.e("API_ERROR", "Llamada para obtener token fallida (DataHolder/onFailure) " + t);
                }
            });
        } else setUpAdapter(view.getContext());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        album_list = new ArrayList<>();
        results = new ArrayList<>();

        if(!DataHolder.getInstance().getTopAlbums().isEmpty()) album_list = DataHolder.getInstance().getTopAlbums();
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.albums_fragment_layout, container, false);
    }

    @Override
    public void OnItemClicked(Context context, String id) {
        DataHolder.getInstance().setSavedFragment(0);
        Intent intent = new Intent(context, AlbumViewSolo.class);
        intent.putExtra("ID", id);
        context.startActivity(intent);
    }

    private void setUpAdapter (Context context){
        AlbumViewAdapter albumViewAdapter = new AlbumViewAdapter(context, album_list, AlbumViewFragment.this);
        recyclerView.setAdapter(albumViewAdapter);
    }

}
