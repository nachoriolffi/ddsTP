package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef;

import ar.edu.utn.frba.dds.dtos.PuntoRecomendadoDTO;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Provincia;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.*;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoProvincia;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Georef extends ApiCaller {
    private final GeorefService georefService;

    public Georef(GeorefService georefService) {
        super("https://apis.datos.gob.ar/georef/api/");
        this.georefService = georefService;
    }

    public ListadoProvincias listadoProvincias() {
        String campos = "id,nombre";
        Call<ListadoProvincias> requestProvinciasArg = georefService.provincias(campos);
        try {
            Response<ListadoProvincias> responseProvinciasArg = requestProvinciasArg.execute();
            ListadoProvincias listadoProvincias = responseProvinciasArg.body();

            if (listadoProvincias != null) {
                listadoProvincias.getProvincias().forEach(provincia -> {
                    Provincia entity = new Provincia();
                    entity.setId(provincia.getId());
                    entity.setNombre(provincia.getNombre());
                    RepoProvincia.INSTANCE.agregar(entity);
                });
            }

            return listadoProvincias;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ListadoProvincias listadoProvincias(int idProvincia) throws IOException {
        String campos = "id,nombre";

        Call<ListadoProvincias> requestProvinciasArg = georefService.provincias(idProvincia, campos);
        Response<ListadoProvincias> responseProvinciasArg = requestProvinciasArg.execute();
        return responseProvinciasArg.body();
    }

    public ListadoMunicipios listadoMunicipios(String id) {
        try {
            Call<ListadoMunicipios> requestMunicipiosArg = georefService.municipios(id, 5000);
            Response<ListadoMunicipios> responseMunicipiosArg = requestMunicipiosArg.execute();
            return responseMunicipiosArg.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ListadoMunicipios listadoMunicipios(int idProvincia) {
        String campos = "id,nombre,provincia";
        Call<ListadoMunicipios> requestMunicipiosArg = georefService.municipios(idProvincia, campos);
        try {
            Response<ListadoMunicipios> responseMunicipiosArg = requestMunicipiosArg.execute();
            return responseMunicipiosArg.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ListadoLocalidades listadoLocalidades(String idProvincia, String idMunicipio) {
        Call<ListadoLocalidades> requestLocalidadesArg = georefService.localidades(idProvincia, idMunicipio);
        try {
            Response<ListadoLocalidades> responseLocalidadesArg = requestLocalidadesArg.execute();
            return responseLocalidadesArg.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ListadoLocalidades listadoLocalidades(long id) throws IOException {
        String campos = "id,nombre,municipio,provincia";
        Call<ListadoLocalidades> requestLocalidadesArg = georefService.localidades(id, campos);
        Response<ListadoLocalidades> responseLocalidadesArg = requestLocalidadesArg.execute();
        return responseLocalidadesArg.body();
    }

    public Coordenada obtenerCoordenadasPorDireccion(String direccion) {
        try {
            // Codifica la dirección para la solicitud HTTP
            String direccionCodificada = URLEncoder.encode(direccion, StandardCharsets.UTF_8.toString());
            String campos = "ubicacion.lat,ubicacion.lon";

            // Llama al servicio de geocodificación con la dirección codificada y los campos necesarios
            Call<ListadoCoordenadas> requestGeocodificacion = georefService.direcciones(direccionCodificada, campos);
            Response<ListadoCoordenadas> responseGeocodificacion = requestGeocodificacion.execute();

            // Verifica si la respuesta es exitosa
            if (!responseGeocodificacion.isSuccessful()) {
                System.out.println("Código de respuesta: " + responseGeocodificacion.code());
                System.out.println("Mensaje: " + responseGeocodificacion.message());
                throw new RuntimeException("Error en la solicitud: " + responseGeocodificacion.message());
            }

            // Obtiene el cuerpo de la respuesta
            ListadoCoordenadas listadoCoordenadas = responseGeocodificacion.body();
            System.out.println("Cuerpo de la respuesta: " + listadoCoordenadas); // Imprime el cuerpo

            // Verifica si hay coordenadas disponibles
            if (listadoCoordenadas != null && listadoCoordenadas.getDirecciones() != null && !listadoCoordenadas.getDirecciones().isEmpty()) {
                ListadoCoordenadas.Direccion direccionResultado = listadoCoordenadas.getDirecciones().get(0);
                if (direccionResultado.getUbicacion() != null) {
                    // Retorna la coordenada con latitud y longitud
                    return new Coordenada(direccionResultado.getUbicacion().getLat(), direccionResultado.getUbicacion().getLon());
                }
            } else {
                throw new RuntimeException("No se encontraron coordenadas para la dirección proporcionada.");
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error al codificar la dirección.", e);
        } catch (IOException e) {
            throw new RuntimeException("Error al intentar obtener coordenadas de la dirección.", e);
        }

        return null; // Retorna null si no se encuentran resultados
    }


    public List<PuntoRecomendadoDTO> obtenerCallesPorCoordenadas(List<Coordenada> coordenadas) {
        return null;
    }
}