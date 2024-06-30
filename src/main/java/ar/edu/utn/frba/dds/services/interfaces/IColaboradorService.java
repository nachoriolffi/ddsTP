package ar.edu.utn.frba.dds.services.interfaces;


import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

public interface IColaboradorService {

    public Colaborador crear(ColaboradorDTO dto);
    public Colaborador modificar(Integer id,ColaboradorDTO dto);
    public void eliminar(Integer id);
}
