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

import com.example.vibevault.R;
import com.example.vibevault.interfaces.SelectListener;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SongViewFragment extends Fragment implements SelectListener {
    private RecyclerView recyclerView;

    private List<Pokemon> poke_list;

    private List<Results> results;

    public SongViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        Context context = view.getContext();
        recyclerView = view.findViewById(R.id.songsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poke_list = new ArrayList<>();
        results = new ArrayList<>();

        if(!DataHolder.getInstance().getPokemonList().isEmpty()) poke_list = DataHolder.getInstance().getPokemonList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_view, container, false);
    }

    @Override
    public void OnItemClicked(Context context, String name) {
        DataHolder.getInstance().setSavedFragment(0);
        Intent intent = new Intent(context, ViewPokemon.class);
        intent.putExtra("NAME", name);
        context.startActivity(intent);
    }

    private void setUpAdapter (Context context){
        PokemonViewAdapter pokemonViewAdapter = new PokemonViewAdapter(context, poke_list, PokemonViewFragment.this);
        recyclerView.setAdapter(pokemonViewAdapter);
    }
}
