package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.IncidenteInputDTO;
import ar.edu.utn.frba.dds.dtos.IncidenteOutputDTO;


public interface IIncidenteService {
    public IncidenteOutputDTO crear(IncidenteInputDTO dto);
    public IncidenteOutputDTO modificar(IncidenteInputDTO dto);
    public void eliminar(IncidenteInputDTO dto);

}
