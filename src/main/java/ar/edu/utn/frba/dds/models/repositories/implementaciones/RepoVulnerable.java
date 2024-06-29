package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoVulnerable;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class RepoVulnerable implements IRepoVulnerable {
    private static RepoVulnerable instancia = null;

    @Getter
    private List<Vulnerable> vulnerables;

    public static RepoVulnerable getInstancia() {
        if (instancia == null) {
            instancia = new RepoVulnerable();
        }
        return instancia;
    }


    @Override
    public void agregarVulnerable(Vulnerable vulnerable) {
        this.vulnerables.add(vulnerable);
    }

    @Override
    public void eliminarVulnerable(Vulnerable vulnerable) {
        this.vulnerables.remove(vulnerable);
    }

    @Override
    public Optional<Vulnerable> buscarVulnerable(Long id) {
        return vulnerables
                .stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

}
