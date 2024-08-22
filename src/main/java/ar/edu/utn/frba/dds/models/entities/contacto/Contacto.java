package ar.edu.utn.frba.dds.models.entities.contacto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import static javax.persistence.GenerationType.*;

@Entity
@Table(appliesTo = "Contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
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
