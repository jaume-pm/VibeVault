package com.example.vibevault;

import com.example.vibevault.songs.Song;

import java.util.List;

public class DataHolder {
    private static final DataHolder INSTANCE = new DataHolder();
    private int savedFragment;
    private Song holdedSong;

    private List<Song> topSongsLoaded;

    private List<Song> favSongsLoaded;

    private DataHolder() {
        savedFragment = 0;
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

    public List<Song> getTopSongsLoaded() {
        return topSongsLoaded;
    }

    public void setTopSongsLoaded(List<Song> topSongsLoaded) {
        this.topSongsLoaded = topSongsLoaded;
    }

    public List<Song> getFavSongsLoaded() {
        return favSongsLoaded;
    }

    public void setFavSongsLoaded(List<Song> favSongsLoaded) {
        this.favSongsLoaded = favSongsLoaded;
    }
}
