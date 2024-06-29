package ar.edu.utn.frba.dds.services;


import ar.edu.utn.frba.dds.dtos.TecnicoInputDTO;
import ar.edu.utn.frba.dds.dtos.TecnicoOutputDTO;

public interface ITecnicoService {
    public TecnicoOutputDTO crear(TecnicoInputDTO dto);
    public TecnicoOutputDTO modificar(TecnicoInputDTO dto);
    public void eliminar(TecnicoInputDTO dto);
}
