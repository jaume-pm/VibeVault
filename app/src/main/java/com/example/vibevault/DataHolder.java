package com.example.vibevault;

public class DataHolder {
    private static final DataHolder INSTANCE = new DataHolder();
    private int savedFragment;

    private DataHolder() {
        savedFragment = 0;
    }
    public static DataHolder getInstance() {
        return INSTANCE;
    }

    public int getSavedFragment() {
        return savedFragment;
    }

    public void setSavedFragment(int savedFragment) {
        this.savedFragment = savedFragment;
    }
}
