package ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI;

import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.entities.PuntoRecomendado;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

// uso del patron singleton
public class ServicioRecomendacion {

    private static  ServicioRecomendacion instancia = null;
    private static final String APIUrl = "https://71271dbf-24f0-4063-9962-775312a601c7.mock.pstmn.io/api/"; // se debe poder sacar de un archivo de configuracion
    private Retrofit retrofit;

    private ServicioRecomendacion(){
        // uso del patron builder
        this.retrofit = new Retrofit.Builder().
                baseUrl(APIUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public static ServicioRecomendacion getInstancia(){
        if (instancia == null){
            instancia = new ServicioRecomendacion();
        }
        return instancia;
    }
   // Esto ya se hace en el adapter de recomendacion
    public List<PuntoRecomendado> puntosRecomendados(Double longitud,Double latitud, Integer radio) throws IOException {
        ExternalRecomendacionHeladeras externalRecomendacionHeladeras = this.retrofit.create(ExternalRecomendacionHeladeras.class);
        Call<List<PuntoRecomendado>> requestPuntosRecomendados = externalRecomendacionHeladeras.puntosRecomendados(longitud,latitud,radio);
        Response<List<PuntoRecomendado>> responseListaPuntos =requestPuntosRecomendados.execute(); // aca ya se ejecuta la request
        return responseListaPuntos.body();
    }
}
