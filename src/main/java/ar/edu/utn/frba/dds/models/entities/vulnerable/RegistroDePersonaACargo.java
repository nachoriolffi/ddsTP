package ar.edu.utn.frba.dds.models.entities.vulnerable;

import java.util.Date;

public class RegistroDePersonaACargo {
    private Boolean tienePersonaACargo;
    private Date fechaRegistro;
    private Integer cantidadDePersonasACargo;

    public RegistroDePersonaACargo(Boolean tienePersonaACargo, Date fecaRegistro, Integer cantidadDePersonasACargo) {
        this.tienePersonaACargo = tienePersonaACargo;
        this.fechaRegistro = fecaRegistro;
        this.cantidadDePersonasACargo = cantidadDePersonasACargo;
    }
}
