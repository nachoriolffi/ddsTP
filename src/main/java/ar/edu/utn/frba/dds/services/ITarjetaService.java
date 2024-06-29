package ar.edu.utn.frba.dds.services;


import ar.edu.utn.frba.dds.dtos.TarjetaInputDTO;
import ar.edu.utn.frba.dds.dtos.TarjetaOutputDTO;

public interface ITarjetaService {
    public TarjetaOutputDTO crear(TarjetaInputDTO dto);
    public TarjetaOutputDTO modificar(TarjetaInputDTO dto);
    public void eliminar(TarjetaInputDTO dto); // public void eliminar(long idTarjeta);
}
