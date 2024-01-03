package com.example.vibevault.songs.api;

import com.example.vibevault.songs.Song;

import java.util.List;

public class ApiResponseGetSongs {
    private List<ItemsSong> items;

    // Constructor, getters y setters
    public ApiResponseGetSongs() {
    }

    public class ItemsSong {
        public boolean is_local;
        public Song track;
    }

    public List<ItemsSong> getTracks() {
        return items;
    }
}