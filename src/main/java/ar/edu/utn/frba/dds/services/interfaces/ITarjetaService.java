package ar.edu.utn.frba.dds.services.interfaces;


import ar.edu.utn.frba.dds.dtos.inputs.TarjetaInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.TarjetaOutputDTO;

public interface ITarjetaService {
    public TarjetaOutputDTO crear(TarjetaInputDTO dto);
    public TarjetaOutputDTO modificar(TarjetaInputDTO dto);
    public void eliminar(TarjetaInputDTO dto); // public void eliminar(long idTarjeta);
}
