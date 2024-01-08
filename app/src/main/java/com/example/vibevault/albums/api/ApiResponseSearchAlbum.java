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
        if (albums != null && albums.items != null && !albums.items.isEmpty()) {
            return true;
        }
        return false;
    }

    public static class Albums {
        public List<Album> items;
    }
}

