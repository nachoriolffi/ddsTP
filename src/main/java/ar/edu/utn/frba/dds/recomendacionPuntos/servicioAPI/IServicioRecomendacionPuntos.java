package ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI;

import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;
// Interfaz para API REST
public interface IServicioRecomendacionPuntos {
    @GET("puntosRecomendados")
    Call<List<Coordenada>> puntosRecomendados(@Query("latitud") double latitud,
                                              @Query("longitud") double longitud,
                                              @Query("radio")double radio);
}
