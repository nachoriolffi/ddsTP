package ar.edu.utn.frba.dds.services.imp;
import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridisccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.services.interfaces.IColaboradorService;
import ar.edu.utn.frba.dds.utils.TipoDocumento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ColaboradorService implements IColaboradorService {

    private RepoColaborador colaboradorRepository;

    public ColaboradorService(RepoColaborador colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    @Override
    public Colaborador crear(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre(colaboradorDTO.getNombre());
        colaborador.setApellido(colaboradorDTO.getApellido());
        colaborador.setFechaDeNacimiento(LocalDate.parse(colaboradorDTO.getFechaDeNacimiento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Direccion direccion = new Direccion(colaboradorDTO.getCalle(), colaboradorDTO.getAltura(), colaboradorDTO.getPiso());
        colaborador.setDireccion(direccion);
        colaborador.setTipoDocumento(TipoDocumento.valueOf(colaboradorDTO.getTipoDocumento()));
        colaborador.setNumeroDocumento(colaboradorDTO.getNumeroDocumento());
        colaborador.setRazonSocial(colaboradorDTO.getRazonSocial().toString());
        colaborador.setRubroColaborador(new RubroColaborador(colaboradorDTO.getRubro()));
        colaborador.setTipoPersona(TipoPersona.valueOf(colaboradorDTO.getTipoPersona()));
        colaborador.setTipoJuridisccion(TipoJuridisccion.valueOf(colaboradorDTO.getTipoJuridisccion()));
        // faltan mas atributos
        return colaborador;
    }

    @Override
    public Colaborador modificar(Integer id,ColaboradorDTO dto) {
        Colaborador colaboradorAModificar = this.colaboradorRepository.buscar(Long.valueOf(id));
        // seteamos todos los atributos del colaborador
        colaboradorAModificar.setNombre(dto.getNombre());
        return colaboradorAModificar;
    }

    @Override
    public void eliminar(Integer id) {
        Colaborador colaboradorAEliminar = this.colaboradorRepository.buscar(Long.valueOf(id));
        this.colaboradorRepository.eliminar(colaboradorAEliminar);
    }
}
