package ar.edu.utn.frba.dds.models.entities.usuario;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "rol")
@Entity
public class Rol {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre",columnDefinition = "VARCHAR(255)")
    private String nombre;
    @Transient
    private List<Permiso> permisos;

    public Rol() {
        this.permisos = new ArrayList<Permiso>();
    }

    public Boolean tenesPermiso(Permiso permiso) {
        return this.permisos.contains(permiso);
    }

}
