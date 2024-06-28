package ar.edu.utn.frba.dds.models.factories;

import ar.edu.utn.frba.dds.dtos.ColaboradorDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridisccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.Rubro;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.utils.TipoDocumento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FactoryColaborador {
    public static Colaborador crearColaborador(ColaboradorDTO colaboradorDTO) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre(colaboradorDTO.getNombre());
        colaborador.setApellido(colaboradorDTO.getApellido());
        colaborador.setFechaDeNacimiento(LocalDate.parse(colaboradorDTO.getFechaDeNacimiento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        Direccion direccion = new Direccion(colaboradorDTO.getCalle(), colaboradorDTO.getAltura(), colaboradorDTO.getPiso());
        colaborador.setDireccion(direccion);
        colaborador.setTipoDocumento(TipoDocumento.valueOf(colaboradorDTO.getTipoDocumento()));
        colaborador.setNumeroDocumento(colaboradorDTO.getNumeroDocumento());
        colaborador.setRazonSocial(colaboradorDTO.getRazonSocial());
        colaborador.setRubro(new Rubro(colaboradorDTO.getRubro()));
        colaborador.setTipoPersona(TipoPersona.valueOf(colaboradorDTO.getTipoPersona()));
        colaborador.setTipoJuridisccion(TipoJuridisccion.valueOf(colaboradorDTO.getTipoJuridisccion()));
        /*for (int i = 0; i < colaboradorDTO.getFormasDeColaboracion().size(); i++) {

        }
        colaborador.setFormasDeColaboracion();*/
        return colaborador;
    }
}
