package com.example.vibevault;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vibevault.albums.AlbumViewFragment;
import com.example.vibevault.albums.AlbumViewSolo;
import com.example.vibevault.artists.ArtistViewFragment;
import com.example.vibevault.artists.ArtistViewSolo;
import com.example.vibevault.favourite.FavouriteGlobalViewFragment;
import com.example.vibevault.songs.SongViewFragment;
import com.example.vibevault.songs.SongViewSolo;

public class MainActivity extends AppCompatActivity {

    private ImageButton searchBtn;

    private ImageButton[] bottomBarBtn;

    private TextView[] bottomBarTxt;

    private EditText searchInp;

    private Fragment[] fragments;

    private int selectedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments = new Fragment[4];
        fragments[0] = new SongViewFragment();
        fragments[1] = new ArtistViewFragment();
        fragments[2] = new AlbumViewFragment();
        fragments[3] = new FavouriteGlobalViewFragment();
        //fragments[1] = new ExempleViewFragment();
        setContentView(R.layout.activity_main);

        selectedFragment = DataHolder.getInstance().getSavedFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragments[selectedFragment]);
        fragmentTransaction.commit();

        bottomBarBtn = new ImageButton[4];
        bottomBarTxt = new TextView[4];

        searchBtn = (ImageButton) findViewById(R.id.main_search_btn);
        bottomBarBtn[0] = (ImageButton) findViewById(R.id.main_songs_btn);
        bottomBarBtn[1] = (ImageButton) findViewById(R.id.main_artists_btn);
        bottomBarBtn[2] = (ImageButton) findViewById(R.id.main_albums_btn);
        bottomBarBtn[3] = (ImageButton) findViewById(R.id.main_fav_btn);

        searchBtn.setOnClickListener(buttonAction);
        bottomBarBtn[0].setOnClickListener(buttonAction);
        bottomBarBtn[1].setOnClickListener(buttonAction);
        bottomBarBtn[2].setOnClickListener(buttonAction);
        bottomBarBtn[3].setOnClickListener(buttonAction);

        bottomBarTxt[0] = (TextView) findViewById(R.id.main_songs_txt);
        bottomBarTxt[1] = (TextView) findViewById(R.id.main_artists_txt);
        bottomBarTxt[2] = (TextView) findViewById(R.id.main_albums_txt);
        bottomBarTxt[3] = (TextView) findViewById(R.id.main_fav_txt);

        searchInp = (EditText) findViewById(R.id.main_search_inp);

        bottomBarBtn[selectedFragment].setSelected(true);
        bottomBarTxt[selectedFragment].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));

    }

    protected View.OnClickListener buttonAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == searchBtn.getId()){
                Intent intent;
                if(selectedFragment == 0) { // Songs
                    intent = new Intent(MainActivity.this, SongViewSolo.class);
                }else if (selectedFragment == 1){ // Artists
                    intent = new Intent(MainActivity.this, ArtistViewSolo.class);
                }
                else { // Albums
                    intent = new Intent(MainActivity.this, AlbumViewSolo.class);
                }
                intent.putExtra("NAME", searchInp.getText().toString());
                startActivity(intent);
                // Gestionar el click del boton buscar
                // Se puede usar el selectedFragment para saber en que fragmento estamos
            } else if(id == bottomBarBtn[0].getId()){
                renderNewFragment (0);
            } else if (id == bottomBarBtn[1].getId()) {
                renderNewFragment (1);
            } else if (id == bottomBarBtn[2].getId()) {
                renderNewFragment (2);
            } else { // Boton de favoritos
                renderNewFragment (3);
            }
        }
    };

    void renderNewFragment (int newSelection){
        // Utilitzarem això per guardar el fragrment en el que estem
        DataHolder.getInstance().setSavedFragment(newSelection);
        selectedFragment = newSelection;
        for(int i = 0; i<fragments.length; i++){
            if(i == newSelection){
                bottomBarBtn[i].setSelected(true);
                bottomBarTxt[i].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
            } else {
                bottomBarBtn[i].setSelected(false);
                bottomBarTxt[i].setTextColor(ContextCompat.getColor(MainActivity.this, R.color.vibeVault_light_grey));
            }
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragments[selectedFragment]);
        fragmentTransaction.commit();
    }
}