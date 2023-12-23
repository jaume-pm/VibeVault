package com.example.vibevault.APIServices;

import com.example.vibevault.songs.Song;

import java.util.List;

public class ApiResponse {
    private List<ItemsSong> items;

    // Constructor, getters y setters
    public ApiResponse() {
    }

    public class ItemsSong {
        public boolean is_local;
        public Song track;
    }

    public List<ItemsSong> getTracks() {
        return items;
    }
}
