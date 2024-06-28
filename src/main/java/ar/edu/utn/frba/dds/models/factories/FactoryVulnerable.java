package ar.edu.utn.frba.dds.models.factories;

import ar.edu.utn.frba.dds.dtos.VulnerableDTO;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroDePersonaACargo;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.utils.TipoDocumento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FactoryVulnerable {

    public static Vulnerable crearVulnerable(VulnerableDTO vulnerableDTO) {

        Vulnerable vulnerable = new Vulnerable();
        vulnerable.setNombre(vulnerableDTO.getNombre());
        vulnerable.setApellido(vulnerableDTO.getApellido());
        vulnerable.setFechaDeNacimiento(
                LocalDate.parse(vulnerableDTO.getFechaDeNacimiento(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        vulnerable.setFechaDeRegistro(LocalDate.now());
        vulnerable.setSituacionDeCalle(Boolean.parseBoolean(vulnerableDTO.getSituacionDeCalle()));
        vulnerable.setDireccion(new Direccion(
                vulnerableDTO.getCalle(),
                vulnerableDTO.getAltura(),
                vulnerableDTO.getPiso()));
        vulnerable.setTipoDocumento(TipoDocumento.valueOf(vulnerableDTO.getTipoDocumento()));
        vulnerable.setNumeroDocumento(vulnerableDTO.getNumeroDocumento());
        List<RegistroDePersonaACargo> registroDePersonaACargo = new ArrayList<>();
        registroDePersonaACargo.add(new RegistroDePersonaACargo(
                vulnerableDTO.getTienePersonasACargo(),
                LocalDate.now(),
                vulnerableDTO.getPersonasACargo()));
        vulnerable.setRegistroDePersonasACargo(registroDePersonaACargo);
        return vulnerable;
    }
}
