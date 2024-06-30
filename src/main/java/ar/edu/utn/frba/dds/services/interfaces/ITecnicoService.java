package ar.edu.utn.frba.dds.services.interfaces;


import ar.edu.utn.frba.dds.dtos.inputs.TecnicoInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.TecnicoOutputDTO;

public interface ITecnicoService {
    public TecnicoOutputDTO crear(TecnicoInputDTO dto);
    public TecnicoOutputDTO modificar(TecnicoInputDTO dto);
    public void eliminar(TecnicoInputDTO dto);
}
