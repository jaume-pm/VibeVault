package com.example.vibevault.songs;

public class Song {

    private String spotySongID, name, albumName, artistsName;

    private int songLenght;

    private Album album;

    private Artists artists;

    private String songCover;

    private String artistProfilePic;

    private class Album {
        public Images images;

        public String name;
        public String id;
    }

    private class Images {
        public String url;

        public int height;

        public int width;
    }
    private class Artists {
        public String name;
        public String url;
    }

    public String getSpotySongID() {
        return spotySongID;
    }

    public void setSpotySongID(String spotySongID) {
        this.spotySongID = spotySongID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistsName() {
        return artistsName;
    }

    public void setArtistsName(String artistsName) {
        this.artistsName = artistsName;
    }

    public int getSongLenght() {
        return songLenght;
    }

    public void setSongLenght(int songLenght) {
        this.songLenght = songLenght;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    public String getSongCover() {
        return songCover;
    }

    public void setSongCover(String songCover) {
        this.songCover = songCover;
    }

    public String getArtistProfilePic() {
        return artistProfilePic;
    }

    public void setArtistProfilePic(String artistProfilePic) {
        this.artistProfilePic = artistProfilePic;
    }
}
