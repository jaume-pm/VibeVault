package com.example.vibevault;

import com.example.vibevault.songs.Song;
import com.example.vibevault.albums.Album;

import java.util.ArrayList;
import java.util.List;


public class DataHolder {
    private static final DataHolder INSTANCE = new DataHolder();
    private int savedFragment;
    private Song holdedSong;

    private List<Song> topSongs;

    private List<Album> topAlbums;

    private List<Song> favSongs;

    private String access_token;

    private String token_type;

    // Se podria usar para comprobar si el token ha caducado, si es asi hacer un refresh
    // private int expires_in; (de momento no se usa)

    private DataHolder() {
        savedFragment = 0;
        topSongs = new ArrayList<>();
        favSongs= new ArrayList<>();
        topAlbums = new ArrayList<>();
    }

    public static DataHolder getInstance() {
        return INSTANCE;
    }

    public int getSavedFragment() {
        return savedFragment;
    }

    public void setSavedFragment(int savedFragment) {
        this.savedFragment = savedFragment;
    }

    public Song getHoldedSong() {
        return holdedSong;
    }

    public void setHoldedSong(Song holdedSong) {
        this.holdedSong = holdedSong;
    }

    public List<Song> getTopSongs() {
        return topSongs;
    }

    public void setTopSongs(List<Song> topSongsLoaded) {
        this.topSongs = topSongsLoaded;
    }

    public List<Song> getFavSongs() {
        return favSongs;
    }

    public void setFavSongs(List<Song> favSongsLoaded) {
        this.favSongs = favSongsLoaded;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public List<Album> getTopAlbums() {
        return topAlbums;
    }

    public void setTopAlbums(List<Album> topAlbums) {
        this.topAlbums = topAlbums;
    }
}
