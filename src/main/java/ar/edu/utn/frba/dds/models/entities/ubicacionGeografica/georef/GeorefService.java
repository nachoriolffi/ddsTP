package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.ListadoLocalidades;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.ListadoMunicipios;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.ListadoProvincias;
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
}
