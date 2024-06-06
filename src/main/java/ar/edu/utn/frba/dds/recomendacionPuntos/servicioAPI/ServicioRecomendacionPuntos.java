package ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI;

import ar.edu.utn.frba.dds.ubicacionGeografica.Coordenada;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

// uso del patron singleton
public class ServicioRecomendacionPuntos {

    private static ServicioRecomendacionPuntos instancia = null;
    private static final String APIUrl = "https://71271dbf-24f0-4063-9962-775312a601c7.mock.pstmn.io/api/"; // se debe poder sacar de un archivo de configuracion
    private final Retrofit retrofit;

    private ServicioRecomendacionPuntos(){
        // uso del patron builder
        this.retrofit = new Retrofit.Builder().
                baseUrl(APIUrl).
                addConverterFactory(GsonConverterFactory.create()).
                build();
    }

    public static ServicioRecomendacionPuntos getInstancia(){
        if (instancia == null){
            instancia = new ServicioRecomendacionPuntos();
        }
        return instancia;
    }

    public List<Coordenada> puntosRecomendados(Double longitud, Double latitud, Integer radio) throws IOException {
        IServicioRecomendacionPuntos IServicioRecomendacionPuntos = this.retrofit.create(IServicioRecomendacionPuntos.class);
        Call<List<Coordenada>> requestPuntosRecomendados = IServicioRecomendacionPuntos.puntosRecomendados(longitud,latitud,radio);
        Response<List<Coordenada>> responseListaPuntos =requestPuntosRecomendados.execute(); // aca ya se ejecuta la request
        return responseListaPuntos.body();
    }
}
