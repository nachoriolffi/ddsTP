package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.dtos.VulnerableDTO;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import ar.edu.utn.frba.dds.models.factories.FactoryVulnerable;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoVulnerable;

public class VulnerableController {

    private final IRepoVulnerable vulnerableRepository;

    public VulnerableController(IRepoVulnerable vulnerableRepository) {
        this.vulnerableRepository = vulnerableRepository;
    }

    public void crearVulnerable(VulnerableDTO vulnerableDTO) {
        Vulnerable vulnerable = FactoryVulnerable.crearVulnerable(vulnerableDTO);
        this.vulnerableRepository.agregarVulnerable(vulnerable);
    }
}
