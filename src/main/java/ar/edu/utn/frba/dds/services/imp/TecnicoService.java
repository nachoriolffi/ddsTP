package ar.edu.utn.frba.dds.services.imp;

import ar.edu.utn.frba.dds.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.TecnicoOutputDTO;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.services.interfaces.ITecnicoService;

public class TecnicoService implements ITecnicoService {

    private RepoTecnico repoTecnico;
    @Override
    public TecnicoOutputDTO crear(TecnicoInputDTO dto) {
        Tecnico tecnico = new Tecnico(
                dto.getId(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getTipoDocumento(),
                dto.getDNI(),
                dto.getCUIL(),
                null,
                dto.getAreaCobertura()
        );
        repoTecnico.agregar(tecnico);

        TecnicoOutputDTO result = new TecnicoOutputDTO(
                dto.getId(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getTipoDocumento(),
                dto.getDNI(),
                dto.getCUIL(),
                dto.getAreaCobertura()
        );

        return result;
    }

    @Override
    public TecnicoOutputDTO modificar(TecnicoInputDTO dto) {
        return null;
    }

    @Override
    public void eliminar(TecnicoInputDTO dto) {
        Tecnico tecnico = new Tecnico(
                dto.getId(),
                dto.getNombre(),
                dto.getApellido(),
                dto.getTipoDocumento(),
                dto.getDNI(),
                dto.getCUIL(),
                null,
                dto.getAreaCobertura()
        );

        this.repoTecnico.eliminar(tecnico);
    }
}
