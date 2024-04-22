package ar.edu.utn.frba.dds.contacto;

import lombok.Getter;
import lombok.Setter;

public class Contacto {
    @Setter
    private String nombre;
    @Setter
    @Getter
    private String descripcion;

    public Contacto() {
    }
}
