package ar.edu.utn.frba.dds.colaborador;

import java.util.Date;

public class RegistroDePersonaACargo {
    private Boolean tienePersonaACargo;
    private Date fecaRegistro;
    private Integer cantidadDePersonasACargo;

    public RegistroDePersonaACargo(Boolean tienePersonaACargo, Date fecaRegistro, Integer cantidadDePersonasACargo) {
        this.tienePersonaACargo = tienePersonaACargo;
        this.fecaRegistro = fecaRegistro;
        this.cantidadDePersonasACargo = cantidadDePersonasACargo;
    }
}
