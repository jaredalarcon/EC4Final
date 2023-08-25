package com.example.ec4final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class FavoritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu_favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.back_to_main){
            /*Intent intent = new Intent(FavoritesActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(FavoritesActivity.this, "Nos vemos!!!",Toast.LENGTH_SHORT).show();
            //CIERRA EL MAIN_ACTIVITY Y MUESTRA EL LOGIN_ACTIVITY*/
            finish();
        }
        return true;
    }
}