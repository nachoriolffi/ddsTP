package ar.edu.utn.frba.dds.models.entities.contacto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Table;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity
@Table(appliesTo = "Contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id_Contacto;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoContacto",nullable = false)
    private TipoContacto tipoContacto;
    @Setter
    @Getter
    @Column(name = "nombreContacto",columnDefinition = "VARCHAR (250)")
    private String descripcion;

    public Contacto(TipoContacto nombre, String descripcion) {
        this.tipoContacto = nombre;
        this.descripcion = descripcion;
    }
    public Contacto() {
    }

  public String getNombre() {
    return descripcion;
  }
}
