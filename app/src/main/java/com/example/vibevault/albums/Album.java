package com.example.vibevault.albums;

import com.example.vibevault.songs.Song;

import java.util.List;

public class Album {

    private String id;
    private String name;
    private int total_tracks;
    private List<Artist> artists;

    private List<Song> songs;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotal_tracks() {
        return total_tracks;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotal_tracks(int total_tracks) {
        this.total_tracks = total_tracks;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    private class Images {
        public String url;

        public int height;

        public int width;
    }

    public class Artist { // Cuando se defina artists, hay que cambiar esto.
        public String name;
    }
}
