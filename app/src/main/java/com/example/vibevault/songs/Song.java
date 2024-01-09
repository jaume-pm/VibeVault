package com.example.vibevault.songs;

import com.example.vibevault.albums.Album;
import com.example.vibevault.artists.Artist;

import java.util.List;

public class Song {

    private String id;
    private String name;
    private int duration_ms;
    private Album album;
    private List<Artist> artists;
    private int popularity;
    private boolean isFavourite = false;

    private String preview_url;

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

    public String getAlbumCover(int opt) {
        return album.getImage(opt);
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artist) {
        this.artists = artist;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public String getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(String preview_url) {
        this.preview_url = preview_url;
    }
}
