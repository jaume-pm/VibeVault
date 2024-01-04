package com.example.vibevault.artists;

import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.api.ApiResponseGetSongs;

import java.util.List;

public class ApiResponseGetArtists {

    private List<Artist> artists;

    // Constructor, getters y setters
    public ApiResponseGetArtists() {
    }

    public List<Artist> getArtists() {
        return artists;
    }
}
