package ar.edu.utn.frba.dds.models.entities.contacto;

import lombok.Getter;
import lombok.Setter;

public class Contacto {
    @Getter
    @Setter
    private TipoContacto nombre;
    @Setter
    @Getter
    private String descripcion;

    public Contacto(TipoContacto nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Contacto() {
    }

}
