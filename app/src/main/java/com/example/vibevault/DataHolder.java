package com.example.vibevault;

import com.example.vibevault.albums.Album;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.songs.Song;

import java.util.ArrayList;
import java.util.List;


public class DataHolder {
    private static final DataHolder INSTANCE = new DataHolder();
    private int savedFragment;
    private Song holdedSong;

    private List<Song> topSongs, tempSongs;

    private List<Artist> topArtists;

    private List<Album> topAlbums;


    private boolean weAreInFav;

    // Se podria usar para comprobar si el token ha caducado, si es asi hacer un refresh
    // private int expires_in; (de momento no se usa)

    private DataHolder() {
        savedFragment = 0;
        topSongs = new ArrayList<>();
        topArtists = new ArrayList<>();
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


    public List<Album> getTopAlbums() {
        return topAlbums;
    }

    public void setTopAlbums(List<Album> topAlbums) {
        this.topAlbums = topAlbums;
    }

    public List<Artist> getTopArtists() {
        return topArtists;
    }

    public void setTopArtists(List<Artist> topArtists) {
        this.topArtists = topArtists;
    }
}
