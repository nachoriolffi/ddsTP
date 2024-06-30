package ar.edu.utn.frba.dds.services.imp;
import ar.edu.utn.frba.dds.dtos.VulnerableDTO;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroDePersonaACargo;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoVulnerable;
import ar.edu.utn.frba.dds.services.interfaces.IVulnerableService;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VulnerableService implements IVulnerableService {

    private final IRepoVulnerable vulnerableRepository;

    public VulnerableService(IRepoVulnerable vulnerableRepository) {
        this.vulnerableRepository = vulnerableRepository;
    }

    @Override
    public Vulnerable crear(VulnerableDTO vulnerableDTO) {
        Vulnerable vulnerable = new Vulnerable();
        vulnerable.setNombre(vulnerableDTO.getNombre());
        vulnerable.setApellido(vulnerableDTO.getApellido());
        vulnerable.setFechaDeNacimiento(
                LocalDate.parse(vulnerableDTO.getFechaDeNacimiento()));
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

    @Override
    public Vulnerable modificar(Integer id, VulnerableDTO dto) {
        Vulnerable vulnerableAModificar = this.vulnerableRepository.buscarVulnerable(id);
        vulnerableAModificar.setNombre(dto.getNombre());
        vulnerableAModificar.setApellido(dto.getApellido());
        vulnerableAModificar.setFechaDeNacimiento(LocalDate.parse(dto.getFechaDeNacimiento()));
        /*
        *  Poner los restantes del DTO.
        * */
        return vulnerableAModificar;
    }

    @Override
    public void eliminar(Integer id) {
        Vulnerable vulnerableAEliminar = this.vulnerableRepository.buscarVulnerable(id);
        this.vulnerableRepository.eliminarVulnerable(vulnerableAEliminar);
    }

}
