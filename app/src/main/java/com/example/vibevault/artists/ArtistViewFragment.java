package com.example.vibevault.artists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vibevault.DataHolder;
import com.example.vibevault.R;
import com.example.vibevault.interfaces.SelectListener;

import java.util.ArrayList;
import java.util.List;

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
        intent.putExtra("searchingById", true);
        context.startActivity(intent);
    }
    private void setUpAdapter (Context context){
        artist_list = DataHolder.getInstance().getTopArtists();
        ArtistViewAdapter artistViewAdapter = new ArtistViewAdapter(context, artist_list, ArtistViewFragment.this);
        recyclerView.setAdapter(artistViewAdapter);
    }
}