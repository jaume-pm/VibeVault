package com.example.vibevault.firebase;

import com.example.vibevault.albums.Album;
import com.example.vibevault.artists.Artist;
import com.example.vibevault.songs.Song;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Favorites {
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static void saveFavoriteArtist(Artist artist) {
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
}
