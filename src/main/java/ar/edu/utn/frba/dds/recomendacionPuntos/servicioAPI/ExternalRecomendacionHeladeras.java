package ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI;

import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.entities.PuntoRecomendado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
// Interfaz para API REST
public interface ExternalRecomendacionHeladeras {
    @GET("puntosRecomendados")
    Call<List<PuntoRecomendado>> puntosRecomendados(@Query("latitud") double latitud,
                                                    @Query("longitud") double longitud,
                                                    @Query("radio")double radio);

}
