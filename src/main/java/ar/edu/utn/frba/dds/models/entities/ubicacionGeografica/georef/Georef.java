package ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Provincia;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.ListadoLocalidades;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.ListadoMunicipios;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.ListadoProvincias;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoProvincia;
import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Response;
import java.io.IOException;


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
}