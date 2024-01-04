package com.example.vibevault.songs.api;

import com.example.vibevault.albums.Album;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.songs.Song;

import java.util.List;

public class ApiResponseGetSongs {
    private List<ItemsSong> items;

    private ItemsAlbum album;

    private ItemsArtist artist;

    // Constructor, getters y setters
    public ApiResponseGetSongs() {
    }

    public class ItemsSong {
        public boolean is_local;
        public Song track;
    }

    public class ItemsAlbum {
        public Album album;
    }

    public class ItemsArtist {
        public Artist artist;
    }


    public List<ItemsSong> getTracks() {
        return items;
    }

    public ItemsAlbum getAlbum(){
        return album;
    }

    public ItemsArtist getArtist() { return artist; }
}