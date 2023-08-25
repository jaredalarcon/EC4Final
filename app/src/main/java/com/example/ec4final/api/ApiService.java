package com.example.ec4final.api;

import retrofit2.Call;
import retrofit2.http.GET;

import com.example.ec4final.models.ItemRespuesta;

public interface ApiService {
    @GET("category/treasure")
    Call<ItemRespuesta> obtenerListaItem();
}
