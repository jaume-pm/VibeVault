package com.example.vibevault.loading;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.vibevault.MainActivity;
import com.example.vibevault.R;

public class LoadingScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);
        ImageView gif = findViewById(R.id.gifimg);
        Glide.with(this).load(R.drawable.loading_animation).into(gif);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(LoadingScreen.this, MainActivity.class));
                finish();
            }
        },4000);
    }
}
