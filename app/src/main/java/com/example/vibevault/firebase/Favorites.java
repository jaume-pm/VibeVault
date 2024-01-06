package com.example.vibevault.firebase;

import com.example.vibevault.albums.Album;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.songs.Song;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Favorites {

    //For this class to be work correctly the methods getFavoriteX must be called just once.
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static List<Artist> favoriteArtists = new ArrayList<>();
    private static List<Song> favoriteSongs = new ArrayList<>();
    private static List<Album> favoriteAlbums = new ArrayList<>();

    private static boolean is_favoriteArtistsComplete = true;
    private static boolean is_favoriteAlbumsComplete = false;
    private static boolean is_favoriteSongsComplete = true;

    
    public static boolean isInFavoritesAlbums(String id){
        return favoriteAlbums.stream()
                .anyMatch(album -> album.getId().equals(id));
    }

    public static boolean isInFavoritesArtist(String id){
        return favoriteArtists.stream()
                .anyMatch(artist -> artist.getId().equals(id));
    }
    public static boolean isInFavoritesSongs(String id){
        return favoriteSongs.stream()
                .anyMatch(song -> song.getId().equals(id));
    }

    public static void saveFavoriteArtist(Artist artist) {
        favoriteArtists.add(artist);
        db.collection("artists")
                .add(artist)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Handle success
                    }
                });
    }

    public static void saveFavoriteAlbum(Album album) {
        favoriteAlbums.add(album);
        db.collection("albums")
                .add(album)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Handle success
                    }
                });
    }

    public static void saveFavoriteSong(Song song) {
        favoriteSongs.add(song);
        db.collection("songs")
                .add(song)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Handle success
                    }
                });
    }

    public static void deleteFavoriteArtist(Artist artist) {
        favoriteArtists.remove(artist);
        db.collection("artists")
                .whereEqualTo("id", artist.getId()) // Assuming "id" is the field storing the artist name
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                        db.collection("artists")
                                .document(documentSnapshot.getId()) // Get the document ID
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Handle success
                                    }
                                });
                    }
                });
    }

    public static void deleteFavoriteAlbum(Album album) {
        favoriteAlbums.remove(album);
        db.collection("albums")
                .whereEqualTo("id", album.getId()) // Assuming "id" is the field storing the album name
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                        db.collection("albums")
                                .document(documentSnapshot.getId()) // Get the document ID
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Handle success
                                    }
                                });
                    }
                });
    }

    public static void deleteFavoriteSong(Song song) {
        favoriteSongs.remove(song);
        db.collection("songs")
                .whereEqualTo("id", song.getId()) // Assuming "id" is the field storing the song name
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (!queryDocumentSnapshots.isEmpty()) {
                        DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                        db.collection("songs")
                                .document(documentSnapshot.getId()) // Get the document ID
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // Handle success
                                    }
                                });
                    }
                });
    }

    public static void downloadFavoritesAlbums() {
        db.collection("albums")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    favoriteAlbums = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Album album = document.toObject(Album.class);
                        favoriteAlbums.add(album);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
        is_favoriteAlbumsComplete = true;
    }

    public static void downloadFavoritesSongs() {
        db.collection("songs")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    favoriteSongs = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Song song = document.toObject(Song.class);
                        favoriteSongs.add(song);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
        is_favoriteSongsComplete = true;
    }

    public static void downloadFavoritesArtists() {
        db.collection("artists")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    favoriteArtists = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Artist artist = document.toObject(Artist.class);
                        favoriteArtists.add(artist);
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
        is_favoriteArtistsComplete = true;
    }

    public static List<Album> getFavoriteAlbums() {
        return favoriteAlbums;
    }

    public static List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public static List<Artist> getFavoriteArtists() {
        return favoriteArtists;
    }


    public static boolean isFavoriteArtistsComplete() {
        return is_favoriteArtistsComplete;
    }

    public static boolean isFavoriteAlbumsComplete() {
        return is_favoriteAlbumsComplete;
    }

    public static boolean isFavoriteSongsComplete() {
        return is_favoriteSongsComplete;
    }
}
