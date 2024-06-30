package ar.edu.utn.frba.dds.services.interfaces;

import ar.edu.utn.frba.dds.dtos.inputs.IncidenteInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.IncidenteOutputDTO;


public interface IIncidenteService {
    public IncidenteOutputDTO crear(IncidenteInputDTO dto);
    public IncidenteOutputDTO modificar(IncidenteInputDTO dto);
    public void eliminar(IncidenteInputDTO dto);

}
