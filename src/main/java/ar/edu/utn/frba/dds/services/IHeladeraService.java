package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.HeladeraInputDTO;
import ar.edu.utn.frba.dds.dtos.HeladeraOutputDTO;

public interface IHeladeraService {
    public HeladeraOutputDTO crear(HeladeraInputDTO dto);
    public HeladeraOutputDTO modificar(HeladeraInputDTO dto);
    public void eliminar(HeladeraInputDTO dto); // public void eliminar(long idHeladera);
}
