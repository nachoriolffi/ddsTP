package ar.edu.utn.frba.dds.models.entities.usuario;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;


@Getter
@Table(name = "rol")
@Entity
public class Rol {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    // @Column(name = "nombre",columnDefinition = "VARCHAR(255)")
    //private String nombre;
    /*@ManyToMany
    @JoinTable(
            name = "rol_permiso",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name="permiso_id")
    )*/
    //private List<Permiso> permisos; // esto lo dejamos por ahora

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    @Setter
    private TipoRol tipoRol;

    public Rol() {
        //this.permisos = new ArrayList<Permiso>();
    }

    /*public Boolean tenesPermiso(Permiso permiso) {
        //return this.permisos.contains(permiso);
    }*/

    public void agregarPermiso(Permiso permiso) {
        //is.permisos.add(permiso);
    }

    public void quitarPermiso(Permiso permiso) {
        //this.permisos.remove(permiso);
    }

}
