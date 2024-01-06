package com.example.vibevault.albums;

import com.example.vibevault.artists.Artist;
import com.example.vibevault.songs.Song;
import com.example.vibevault.utilities.Image;

import java.util.List;

public class Album {

    private String id;
    private String name;
    private int total_tracks;
    private List<Artist> artists;

   // private List<Song> songs;

    private List<Image> images;

    private String release_date;

    private String album_type;

    private String popularity;

    private boolean isFavourite;

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


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getImage(int i) {
        return images.get(i).url;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getAlbum_type() {
        return album_type;
    }

    public void setAlbum_type(String album_type) {
        this.album_type = album_type;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }


    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
