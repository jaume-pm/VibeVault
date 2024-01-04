package com.example.vibevault.albums.api;

import com.example.vibevault.albums.Album;
import com.example.vibevault.songs.Song;

import java.util.List;

public class ApiResponseSearchAlbum {

    private Tracks tracks;

    // Constructor, getters y setters
    public ApiResponseSearchAlbum() {
    }

    private class Tracks {
        public List<Album> items;

    }

    public Album getTrack() { return tracks.items.get(0);}
}
