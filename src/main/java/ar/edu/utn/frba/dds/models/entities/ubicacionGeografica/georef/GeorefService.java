package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefService {
    @GET("provincias")
    Call<ListadoProvincias> provincias();
    @GET("provincias")
    Call<ListadoProvincias> provincias(@Query("campos") String campos);
    @GET("provincias")
    Call<ListadoProvincias> provincias(@Query("id") int id, @Query("campos") String campos);

    @GET("municipios")
    Call<ListadoMunicipios> municipios(@Query("id") int id, @Query("campos") String campos);

    @GET("municipios")
    Call<ListadoMunicipios> municipios(@Query("provincia") String idProvincia, @Query("max") int max);

    @GET("localidades")
    Call<ListadoLocalidades> localidades(@Query("id") long id, @Query("campos") String campos);

    @GET("localidades")
    Call<ListadoLocalidades> localidades(@Query("provincia") String idProvincia, @Query("municipio") String idMunicipio);

    @GET("direcciones")
    Call<ListadoCoordenadas> direcciones(@Query("direccion") String direccion, @Query("campos") String campos);

    @GET("calles")
    Call<ListadoCalles> obtenerCallesPorCoordenadas(
            @Query("lat") double latitud,
            @Query("lon") double longitud,
            @Query("campos") String campos
    );
    @GET("geocodificar")
    Call<ListadoCoordenadas> coordenadas(@Query("lat") double latitud, @Query("lon") double longitud, @Query("campos") String campos);






}
