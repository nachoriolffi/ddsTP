package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.vulnerable.RegistroMenorACargo;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroPersonaACargo;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoVulnerable;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class VulnerablePersistencia {

    @Test
    public void persistirPersonaVulnerable(){

        RepoRegistroPersonaACargo repoRegistroPersonaACargo = new RepoRegistroPersonaACargo();

        Vulnerable vulnerable = new Vulnerable();
        vulnerable.setNombre("Mateo");
        vulnerable.setApellido("Perez");
        vulnerable.setFechaDeNacimiento(new Date(2000,4,12));
        vulnerable.setFechaDeRegistro((new Date()));
        vulnerable.setSituacionDeCalle(Boolean.TRUE);
        vulnerable.setTipoDocumento(TipoDocumento.DNI);
        vulnerable.setNumeroDocumento(40845654);

        RegistroMenorACargo registro = new RegistroMenorACargo();
        registro.setFechaRegistro(new Date());
        registro.setTienePersonaACargo(Boolean.TRUE);
        registro.setCantidadDePersonasACargo(3);
        registro.setVulnerable(vulnerable);
        repoRegistroPersonaACargo.agregar(registro);
        vulnerable.getMenoresACargo().add(registro);

        RepoVulnerable repoVulnerable = new RepoVulnerable();
        repoVulnerable.agregar(vulnerable);
    }
    @Test
    public void modificarVulnerable(){

        RepoRegistroPersonaACargo repoRegistroPersonaACargo = new RepoRegistroPersonaACargo();

        RepoVulnerable repoVulnerable = new RepoVulnerable();
        Vulnerable vulnerable = repoVulnerable.buscar(1L);
        vulnerable.setSituacionDeCalle(Boolean.FALSE);

        RegistroMenorACargo registro2 = new RegistroMenorACargo();
        registro2.setFechaRegistro(new Date(2025,4,14));
        registro2.setTienePersonaACargo(Boolean.FALSE);
        registro2.setCantidadDePersonasACargo(0);
        registro2.setVulnerable(vulnerable);

        repoRegistroPersonaACargo.agregar(registro2);
        vulnerable.getMenoresACargo().add(registro2);

        repoVulnerable.modificar(vulnerable);

    }
}
