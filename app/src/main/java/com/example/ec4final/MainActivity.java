package com.example.ec4final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;


import com.example.ec4final.api.ApiService;
import com.example.ec4final.models.Item;
import com.example.ec4final.models.ItemRespuesta;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "WIKIZELDA";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaItemAdapter listaItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Añadiendo al recicler view
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewItems);
        listaItemAdapter = new ListaItemAdapter(this);

        recyclerView.setAdapter(listaItemAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        //api
        retrofit = new Retrofit.Builder()
                .baseUrl("https://botw-compendium.herokuapp.com/api/v3/compendium/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();
    }
    private void moveToDescription(Item item){
        Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Item", item);
        startActivity(intent);
    }

    private void obtenerDatos(){
        ApiService service = retrofit.create(ApiService.class);
        Call<ItemRespuesta> itemRespuestaCall = service.obtenerListaItem();

        itemRespuestaCall.enqueue(new Callback<ItemRespuesta>() {
            @Override
            public void onResponse(Call<ItemRespuesta> call, Response<ItemRespuesta> response) {
                if(response.isSuccessful()){
                    ItemRespuesta itemRespuesta = response.body();
                    ArrayList<Item> listaItem = itemRespuesta.getData();
                    listaItemAdapter.adicionarListaItem(listaItem);
                    /*for (int i = 0; i < listaItem.size(); i++) {
                        Item p = listaItem.get(i);
                        Log.i(TAG," Item: " + p.getName());
                    }*/
                }else{
                    Log.e(TAG," onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ItemRespuesta> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.log_out){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("¿Desea cerrar sesión?").setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Nos vemos!!!",Toast.LENGTH_SHORT).show();
                    //CIERRA EL MAIN_ACTIVITY Y MUESTRA EL LOGIN_ACTIVITY
                    finish();
                    //CIERRA LA APLICACION
                    /*Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);*/
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }else{
            if(item.getItemId() == R.id.favorites){
                Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                startActivity(intent);
            }
        }
        return true;
    }
}