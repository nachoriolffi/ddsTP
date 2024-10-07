package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.Georef;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.GeorefService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeorefInitializer {
    public static Georef initializeGeoref() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.datos.gob.ar/georef/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GeorefService georefService = retrofit.create(GeorefService.class);
        return new Georef(georefService);
    }
}
