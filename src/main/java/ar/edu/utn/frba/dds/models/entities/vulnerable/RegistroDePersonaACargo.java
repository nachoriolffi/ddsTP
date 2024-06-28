package ar.edu.utn.frba.dds.models.entities.vulnerable;

import java.time.LocalDate;

public class RegistroDePersonaACargo {
    private Boolean tienePersonaACargo;
    private LocalDate fechaRegistro;
    private Integer cantidadDePersonasACargo;

    public RegistroDePersonaACargo(Boolean tienePersonaACargo, LocalDate fecaRegistro, Integer cantidadDePersonasACargo) {
        this.tienePersonaACargo = tienePersonaACargo;
        this.fechaRegistro = fecaRegistro;
        this.cantidadDePersonasACargo = cantidadDePersonasACargo;
    }
}
