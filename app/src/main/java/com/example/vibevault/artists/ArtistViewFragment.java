package com.example.vibevault.artists;

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
import com.example.vibevault.albums.AlbumViewAdapter;
import com.example.vibevault.albums.AlbumViewFragment;
import com.example.vibevault.interfaces.SelectListener;
import com.example.vibevault.interfaces.SpotifyAPIService;
import com.example.vibevault.interfaces.SpotifyAPIToken;
import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.api.ApiResponseGetSongs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistViewFragment extends Fragment implements SelectListener {
    private RecyclerView recyclerView;
    private List<Artist> artist_list;
    private List<Artist> results;
    private List<Song> song_list;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.artistsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));

        if (artist_list.isEmpty()) {

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

                        // Realiza la llamada a la API de Spotify para obtener los artistas
                        String authToken = "Bearer " + DataHolder.getInstance().getAccess_token();
                        Call<ApiResponseGetSongs> callSongs = spotifyAPIServiceSongs.getAllGlobalSongs(authToken);
                        callSongs.enqueue(new Callback<ApiResponseGetSongs>() {
                            @Override
                            public void onResponse(Call<ApiResponseGetSongs> call, Response<ApiResponseGetSongs> response) {
                                if (response.isSuccessful() && response.body() != null) {
                                    ApiResponseGetSongs apiResponseGetSongs = response.body();

                                    for (ApiResponseGetSongs.ItemsSong i : apiResponseGetSongs.getTracks()) {
                                        //artist_list.add(i.track.getArtist());
                                    }

                                    DataHolder.getInstance().setTopArtists(artist_list);
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
           /* call.enqueue(new Callback<ApiTokenResponse>() {
                @Override
                public void onResponse(Call<ApiTokenResponse> call, Response<ApiTokenResponse> response) {
                    if (response.isSuccessful()) {
                        ApiTokenResponse tokenResponse = response.body();
                        DataHolder.getInstance().setAccess_token(tokenResponse.getAccess_token());
                        DataHolder.getInstance().setToken_type(tokenResponse.getToken_type());
                        song_list = DataHolder.getInstance().getTopSongs();
                        for (Song.artists artist_list : song_list.getArtists(){

                        }


                    }
                    }

                @Override
                public void onFailure(Call<ApiTokenResponse> call, Throwable t) {

                }
            });*/

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
    public void OnItemClicked(Context context, String name) {
        DataHolder.getInstance().setSavedFragment((0));
        Intent intent = new Intent(context, ArtistViewSolo.class);
        intent.putExtra("NAME", name);
        context.startActivity(intent);
    }
    private void setUpAdapter (Context context){
        ArtistViewAdapter artistViewAdapter = new ArtistViewAdapter(context, artist_list, ArtistViewFragment.this);
        recyclerView.setAdapter(artistViewAdapter);
    }
}