package ar.edu.utn.frba.dds.models.entities.contacto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoContacto",nullable = false)
    private TipoContacto tipoContacto;
    @Setter
    @Getter
    @Column(name = "nombreContacto",columnDefinition = "VARCHAR (255)")
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
