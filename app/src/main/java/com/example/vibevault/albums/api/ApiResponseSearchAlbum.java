package com.example.vibevault.albums.api;

import com.example.vibevault.albums.Album;

import java.util.List;

public class ApiResponseSearchAlbum {

    private Albums albums;

    // Constructor, getters, and setters

    public ApiResponseSearchAlbum() {
    }

    public Albums getAlbums() {
        return albums;
    }

    public boolean resultOk() {
        return albums != null && albums.items != null && !albums.items.isEmpty();
    }

    public static class Albums {
        public List<Album> items;
    }
}

