package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCaller {
    protected final Retrofit retrofit;

    public ApiCaller(String api) {
        retrofit = new Retrofit.Builder()
                .baseUrl(api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
