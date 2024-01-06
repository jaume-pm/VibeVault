package com.example.vibevault.artists;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String id;
    private String name;
    private int popularity;
    private ArrayList<String> genres; // ?
    private List<Images> images;
    private Followers followers = new Followers();

    private boolean isFavourite = false;

    public class Followers {
        private String href;
        public int total; // Make total public

        public int getTotal() {
            return total; // Add a public getter method
        }
    }

    private class Images {
        private String url;

        private int height;

        private int width;
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

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public List<Images> getImages() {
        return images;
    }

    public void setImages(List<Images> images) {
        this.images = images;
    }
}