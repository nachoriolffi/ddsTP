package ar.edu.utn.frba.dds.models.entities.usuario;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Data

@Table(name = "usuario")
@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombreUsuario",columnDefinition = "VARCHAR(255)")
    private String nombre;
    @Column(name = "clave",columnDefinition = "VARCHAR(255)")
    private String contrasenia;
    @Column(name = "correoElectronico", columnDefinition = "VARCHAR(255)", unique = true)
    private String correoElectronico;
    @OneToOne
    private Rol rol;

    public Usuario() {


    }


}
