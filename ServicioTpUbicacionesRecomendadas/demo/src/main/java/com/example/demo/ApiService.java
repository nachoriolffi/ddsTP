package com.example.demo;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public interface ApiService {
    @GET("nearby")
    Call<List<Coordenada>> getNearbyLocations(
            @Query("lat") double lat,
            @Query("lon") double lon,
            @Query("radius") int radius
    );
}

