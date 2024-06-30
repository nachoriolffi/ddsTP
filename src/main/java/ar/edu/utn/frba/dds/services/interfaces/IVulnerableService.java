package ar.edu.utn.frba.dds.services.interfaces;
import ar.edu.utn.frba.dds.dtos.VulnerableDTO;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;

public interface IVulnerableService {
    public Vulnerable crear(VulnerableDTO dto);
    public Vulnerable modificar(Integer id,VulnerableDTO dto);
    public void eliminar(Integer id);
}
