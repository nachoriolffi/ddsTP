package ar.edu.utn.frba.dds.models.entities.usuario;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Table(name = "usuario")
@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "clave")
    private String contrasenia;

    @Column(name = "correoElectronico", unique = true)
    private String correoElectronico;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoRol")
    private TipoRol rol;

    @Column(name = "cuentaEliminada")
    private Boolean cuentaEliminada;

    public Usuario() {
    }

    public Boolean tieneRol(TipoRol rol) {
        return this.getRol().equals(rol);
    }

}
