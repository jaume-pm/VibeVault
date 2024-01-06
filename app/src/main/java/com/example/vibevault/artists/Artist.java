package com.example.vibevault.artists;

import androidx.annotation.Nullable;

import com.example.vibevault.utilities.Image;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private String id;
    private String name;
    private int popularity;
    private ArrayList<String> genres; // ?
    private List<Image> images = new ArrayList<>();
    private Followers followers = new Followers();

    private boolean isFavourite = false;

    public Artist(){}

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

    public List<Image> getImage() {
        return images;
    }

    public String getArtistProfilePic(int opt) {
        if (images != null && opt >= 0 && opt < images.size()) {
            return images.get(opt).url;
        } else {
            // Devolver una URL por defecto o null si el índice está fuera de los límites
            // Por ejemplo, devuelve null o una URL de una imagen por defecto
            return null; // o "URL_de_imagen_por_defecto"
        }
    }

    public void setImage(List<Image> images) {
        this.images = images;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}