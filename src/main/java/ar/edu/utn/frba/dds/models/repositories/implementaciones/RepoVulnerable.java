package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoVulnerable;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepoVulnerable extends RepoGenerico<Vulnerable> implements IRepoVulnerable {
    private static RepoVulnerable instancia = null;

    @Getter
    private List<Vulnerable> vulnerables;

    public static RepoVulnerable getInstancia() {
        if (instancia == null) {
            instancia = new RepoVulnerable();
        }
        return instancia;
    }

    public RepoVulnerable() {
        super(Vulnerable.class);
        this.vulnerables = new ArrayList<Vulnerable>();
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
    public Vulnerable buscarVulnerable(Integer id) {
        return this.getVulnerables().get(id);
    }

}
