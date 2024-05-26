package ar.edu.utn.frba.dds.recomendacionPuntos.adapter;

import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.ExternalRecomendacionHeladeras;
import ar.edu.utn.frba.dds.recomendacionPuntos.servicioAPI.entities.PuntoRecomendado;
import ar.edu.utn.frba.dds.utils.Coordenada;
import retrofit2.Call;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class AdapRecomendacionPuntos implements APIRecomendacionHeladeras {

    private final ExternalRecomendacionHeladeras externalRecomendacionHeladeras;

    public AdapRecomendacionPuntos(ExternalRecomendacionHeladeras externalRecomendacionHeladeras) {
        this.externalRecomendacionHeladeras = externalRecomendacionHeladeras;
    }

    @Override
    public List<Coordenada> recomendarPuntos(Double longitud, Double latitud, Integer radio) {

        try {
            Call<List<PuntoRecomendado>> call = externalRecomendacionHeladeras.puntosRecomendados(latitud,longitud,radio);
            Response<List<PuntoRecomendado>> response = call.execute();
            if (response.isSuccessful()) {
                List<PuntoRecomendado> ubicacionesRecomendadas = response.body();
                List<Coordenada> coordenadas = new ArrayList<>();
                assert ubicacionesRecomendadas != null;
                for (PuntoRecomendado puntoRecomendado : ubicacionesRecomendadas) {
                    Coordenada coordenada = new Coordenada(puntoRecomendado.getLatitud(),puntoRecomendado.getLongitud());
                    coordenadas.add(coordenada);
                }
                return coordenadas;
            } else {
                throw new Exception("Error al obtener recomendaciones: " + response.errorBody());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener recomendaciones");
        }
    }
}
