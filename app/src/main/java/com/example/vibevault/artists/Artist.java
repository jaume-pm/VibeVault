package com.example.vibevault.artists;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String id;
    private String name;
    private int popularity;
    private ArrayList<String> genres; // ?
    private List<Images> images;
    private Followers followers;

    private boolean isFavourite = false;

    private class Followers {
        public String href;
        public int total;
    }

    private class Images {
        public String url;

        public int height;

        public int width;
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

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public Followers getFollowers() {
        return followers;
    }

    public int getFollowersCount() {
        return followers.total;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public List<Images> getImage() {
        return images;
    }

    public String getArtistProfilePic(int opt) {
        return images.get(opt).url;
    }

    public void setImage(List<Images> images) {
        this.images = images;
    }
}