package com.example.vibevault.songs;

import java.util.List;

public class Song {

    private String id;
    private String name;
    private int duration_ms;
    private Album album;
    private List<Artists> artists;
    private class Album {
        public String name;
        public List<Images> images;
    }

    private class Images {
        public String url;

        public int height;

        public int width;
    }
    public class Artists { // Cuando se defina artists, hay que cambiar esto.
        public String name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Artists> getArtists() {
        return artists;
    }

    public void setArtists(List<Artists> artists) {
        this.artists = artists;
    }

    public String getAlbumCover(int opt) {
        return album.images.get(opt).url;
    }

}
