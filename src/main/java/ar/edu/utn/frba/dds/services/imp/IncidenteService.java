package ar.edu.utn.frba.dds.services.imp;

import ar.edu.utn.frba.dds.dtos.inputs.IncidenteInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.IncidenteOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoIncidente;

import ar.edu.utn.frba.dds.services.interfaces.IIncidenteService;

import static ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente.*;


public class IncidenteService implements IIncidenteService {
    private IRepoIncidente repoIncidente;

    @Override
    public IncidenteOutputDTO crear(IncidenteInputDTO dto) {
        Incidente incidente = new Incidente(
                dto.getId(),
                dto.getDescripcion(),
                dto.getPathFoto(),
                obtenerTipoIncidente(dto.getTipoIncidente()),
                null,
                null
        );

        repoIncidente.agregarIncidente(incidente);



        return null;
    }


    private TipoIncidente obtenerTipoIncidente (Integer valor){
        if(valor.equals(0)){
            return FALLA;
        }
        if(valor.equals(1)){
            return ALERTA;
        }
        return null;
    }

    @Override
    public IncidenteOutputDTO modificar(IncidenteInputDTO dto) {
        return null;
    }

    @Override
    public void eliminar(IncidenteInputDTO dto) {
        Incidente incidente = new Incidente(
                dto.getId(),
                dto.getDescripcion(),
                dto.getPathFoto(),
                obtenerTipoIncidente(dto.getTipoIncidente()),
                null,
                null
        );

        repoIncidente.eliminarIncidente(incidente);
    }
}
