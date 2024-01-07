package com.example.vibevault.artists.api;

import com.example.vibevault.artists.Artist;

import java.util.List;

public class ApiResponseSearchArtist {
    private Artists artists;
    public ApiResponseSearchArtist() {

    }

    private class Artists {
        public List<Artist> items;
    }

    public Artist getArtist() { return artists.items.get(0);}

    public boolean resultOk () {
        if(artists.items.size() == 0) return false;
        return true;
    }
}
