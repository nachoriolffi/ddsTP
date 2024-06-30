package ar.edu.utn.frba.dds.services.interfaces;

import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

public interface IHeladeraService {
    Heladera crear(HeladeraInputDTO dto);
    Heladera modificar(Integer id, HeladeraInputDTO dto);
    void eliminar(Integer id);
}
