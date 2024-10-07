package ar.edu.utn.frba.dds.models.entities.usuario;
import lombok.Getter;

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
    @Column(name = "nombre",columnDefinition = "VARCHAR(255)")
    private String nombre;
    @ManyToMany
    @JoinTable(
            name = "rol_permiso",
            joinColumns = @JoinColumn(name = "rol_id"),
            inverseJoinColumns = @JoinColumn(name="permiso_id")
    )
    private List<Permiso> permisos;

    @Enumerated(EnumType.STRING)
    private TipoRol tipoRol;

    public Rol() {
        this.permisos = new ArrayList<Permiso>();
    }

    public Boolean tenesPermiso(Permiso permiso) {
        return this.permisos.contains(permiso);
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

    public void quitarPermiso(Permiso permiso) {
        this.permisos.remove(permiso);
    }

}
