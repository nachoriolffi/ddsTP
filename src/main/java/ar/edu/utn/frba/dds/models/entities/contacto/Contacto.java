package ar.edu.utn.frba.dds.models.entities.contacto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(11)")
    private int id_Contacto;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoContacto",nullable = false)
    private TipoContacto tipoContacto;
    @Setter
    @Getter
    @Column(name = "descripcion", nullable = false)
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
