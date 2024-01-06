package com.example.vibevault.interfaces;

import android.content.Context;

public interface SelectListener {

    // Alomejor en lugar del nombre hay que poner el id que proporciona spotify, o la url.

    void OnItemClicked(Context context, String nombre, boolean isFavorite);

    //void OnRVItemClicked (Context context, String name);
}
