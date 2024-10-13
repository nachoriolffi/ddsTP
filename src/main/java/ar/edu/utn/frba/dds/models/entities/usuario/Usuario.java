package ar.edu.utn.frba.dds.models.entities.usuario;

import io.javalin.http.Context;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

@Data

@Table(name = "usuario")
@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombreUsuario", columnDefinition = "VARCHAR(255)", nullable = false)
    private String nombre;
    @Column(name = "clave", columnDefinition = "VARCHAR(255)", nullable = false)
    private String contrasenia;
    @Column(name = "correoElectronico", columnDefinition = "VARCHAR(255)", unique = true)
    private String correoElectronico;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoRol", nullable = false)
    private TipoRol rol;

    public Usuario() {


    }

    public Boolean tieneRol(TipoRol rol) {
        return this.getRol().equals(rol);
    }

}
