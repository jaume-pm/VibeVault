package com.example.vibevault.songs.api;

import com.example.vibevault.songs.Song;

import java.util.List;

public class ApiResponseSearchSong {

    private Tracks tracks;

    // Constructor, getters y setters
    public ApiResponseSearchSong() {
    }

    private class Tracks {
        public List<Song> items;

    }

    public Song getTrack() { return tracks.items.get(0);}
}
