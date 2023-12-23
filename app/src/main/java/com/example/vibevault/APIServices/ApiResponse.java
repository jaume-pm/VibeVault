package com.example.vibevault.APIServices;

import com.example.vibevault.songs.Song;

import java.util.List;

public class ApiResponse {
    private int count;
    private String next;
    private String previous;
    private List<Song> tracks; // Asegúrate de que este nombre coincida con la clave en el JSON.

    // getters y setters...

    public List<Song> getAllSongsResult() {
        return tracks;
    }

    // ...otros getters y setters si son necesarios
}