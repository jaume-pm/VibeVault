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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Favorites {

    //For this class to be work correctly the methods getFavoriteX must be called just once.
    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static Map<String, Artist> favoriteArtists = new HashMap<>();
    private static Map<String, Song> favoriteSongs = new HashMap<>();
    private static Map<String, Album> favoriteAlbums = new HashMap<>();

    private static boolean is_favoriteArtistsComplete = false;
    private static boolean is_favoriteAlbumsComplete = false;
    private static boolean is_favoriteSongsComplete = false;

    
    public static boolean isInFavoritesAlbums(String id){
        return favoriteAlbums.containsKey(id);
    }

    public static boolean isInFavoritesArtist(String id){
        return favoriteArtists.containsKey(id);
    }
    public static boolean isInFavoritesSongs(String id){
        return favoriteSongs.containsKey(id);
    }

    public static void saveFavoriteArtist(Artist artist) {
        favoriteArtists.put(artist.getId(), artist);
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
        favoriteAlbums.put(album.getId(), album);
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
        favoriteSongs.put(song.getId(), song);
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
        String artistId = artist.getId(); // Get the ID from the provided artist
        if (favoriteArtists.containsKey(artistId)) {
            favoriteArtists.remove(artistId);
            // Now you can delete the artist from Firestore if needed
            db.collection("artists")
                    .whereEqualTo("id", artistId) // Assuming "id" is the field storing the artist ID
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
    }

    public static void deleteFavoriteAlbum(Album album) {
        String albumId = album.getId();

        if (favoriteAlbums.containsKey(albumId)) {
            favoriteAlbums.remove(albumId);
            // Now you can delete the album from Firestore if needed
            db.collection("albums")
                    .whereEqualTo("id", albumId) // Assuming "id" is the field storing the album ID
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
    }


    public static void deleteFavoriteSong(Song song) {
        String songId = song.getId();

        if (favoriteSongs.containsKey(songId)) {
            favoriteSongs.remove(songId);
            // Now you can delete the song from Firestore if needed
            db.collection("songs")
                    .whereEqualTo("id", songId) // Assuming "id" is the field storing the song ID
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
    }

    public static void downloadFavoritesAlbums() {
        db.collection("albums")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    favoriteAlbums = new HashMap<>(); // Change from ArrayList to HashMap
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Album album = document.toObject(Album.class);
                        favoriteAlbums.put(album.getId(), album); // Store the album in the HashMap with the ID as the key
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
                    favoriteSongs = new HashMap<>(); // Change from ArrayList to HashMap
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Song song = document.toObject(Song.class);
                        favoriteSongs.put(song.getId(), song); // Store the song in the HashMap with the ID as the key
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
                    favoriteArtists = new HashMap<>(); // Change from ArrayList to HashMap
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Artist artist = document.toObject(Artist.class);
                        favoriteArtists.put(artist.getId(), artist); // Store the artist in the HashMap with the ID as the key
                    }
                })
                .addOnFailureListener(e -> {
                    // Handle the error
                });
        is_favoriteArtistsComplete = true;
    }


    public static List<Album> getFavoriteAlbums() {
        return new ArrayList<>(favoriteAlbums.values());
    }

    public static List<Song> getFavoriteSongs() {
        return new ArrayList<>(favoriteSongs.values());
    }

    public static List<Artist> getFavoriteArtists() {
        return new ArrayList<>(favoriteArtists.values());
    }

    public static boolean isFavoritesDownloaded(){
        return is_favoriteAlbumsComplete && is_favoriteArtistsComplete && is_favoriteSongsComplete;
    }
}
