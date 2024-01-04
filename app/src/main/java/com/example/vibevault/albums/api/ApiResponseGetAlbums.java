package com.example.vibevault.albums.api;

import com.example.vibevault.albums.Album;
import com.example.vibevault.songs.Song;
import com.example.vibevault.songs.api.ApiResponseGetSongs;

import java.util.List;

public class ApiResponseGetAlbums {
    private List<ItemsAlbum> items;

    // Constructor, getters y setters
    public ApiResponseGetAlbums() {
    }


    public class ItemsAlbum {
        public Album album;
    }

    public List<ItemsAlbum> getAlbums() {
        return items;
    }

}