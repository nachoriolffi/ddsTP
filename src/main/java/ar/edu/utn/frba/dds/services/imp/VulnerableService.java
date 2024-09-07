package ar.edu.utn.frba.dds.services.imp;
import ar.edu.utn.frba.dds.dtos.VulnerableDTO;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroMenorACargo;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoVulnerable;
import ar.edu.utn.frba.dds.services.interfaces.IVulnerableService;
import ar.edu.utn.frba.dds.utils.TipoDocumento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VulnerableService implements IVulnerableService {

    private final RepoVulnerable vulnerableRepository;

    public VulnerableService(RepoVulnerable vulnerableRepository) {
        this.vulnerableRepository = vulnerableRepository;
    }

    @Override
    public Vulnerable crear(VulnerableDTO vulnerableDTO) throws ParseException {
        Vulnerable vulnerable = new Vulnerable();
        vulnerable.setNombre(vulnerableDTO.getNombre());
        vulnerable.setApellido(vulnerableDTO.getApellido());
        SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(vulnerableDTO.getFechaDeNacimiento());
        vulnerable.setFechaDeNacimiento(date);
        vulnerable.setFechaDeRegistro(new Date());
        vulnerable.setSituacionDeCalle(Boolean.parseBoolean(vulnerableDTO.getSituacionDeCalle()));
        vulnerable.setDireccion(new Direccion(
                vulnerableDTO.getCalle(),
                vulnerableDTO.getAltura(),
                vulnerableDTO.getPiso()));
        vulnerable.setTipoDocumento(TipoDocumento.valueOf(vulnerableDTO.getTipoDocumento()));
        vulnerable.setNumeroDocumento(vulnerableDTO.getNumeroDocumento());
        List<RegistroMenorACargo> registroMenorACargos = new ArrayList<>();
        registroMenorACargos.add(new RegistroMenorACargo(
                vulnerableDTO.getTienePersonasACargo(),
                new Date(),
                vulnerableDTO.getPersonasACargo()));
        vulnerable.setMenoresACargo(registroMenorACargos);
        return vulnerable;
    }

    @Override
    public Vulnerable modificar(Integer id, VulnerableDTO dto) {
        Vulnerable vulnerableAModificar = this.vulnerableRepository.buscar(Long.valueOf(id));
        vulnerableAModificar.setNombre(dto.getNombre());
        return vulnerableAModificar;
    }

    @Override
    public void eliminar(Integer id) {
        Vulnerable vulnerableAEliminar = this.vulnerableRepository.buscar(Long.valueOf(id));
        this.vulnerableRepository.eliminar(vulnerableAEliminar);
    }

}
