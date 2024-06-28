package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoVulnerable;
import lombok.Getter;

import java.util.List;

public class RepoVulnerable implements IRepoVulnerable {

    @Getter
    private List<Vulnerable> vulnerables;

    public void agregarVulnerable(Vulnerable vulnerable) {
        this.vulnerables.add(vulnerable);
    }

    public void eliminarVulnerable(Vulnerable vulnerable) {
        this.vulnerables.remove(vulnerable);
    }

    public void borrarVulnerables() {
        this.vulnerables.clear();
    }

}
