package com.example.vibevault.artists;

import java.util.ArrayList;

public class Artist {
    private String id;
    private String name;
    private int popularity;
    private ArrayList<String> genres;
    private boolean isFavourite = false;
    private Followers followers = new Followers();

    private class Followers {
        public String href;
        public int total;
    }
    private Images images;
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

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}