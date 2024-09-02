package ar.edu.utn.frba.dds.models.entities.usuario;

import lombok.Data;

import javax.persistence.*;

@Data

@Table(name = "usuario")
@Entity
public class Usuario {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombreUsuario",columnDefinition = "TEXT")
    private String nombre;
    @Column(name = "contrasenia",columnDefinition = "TEXT")
    private String contrasenia;
    @OneToOne
    private Rol rol;

    public Usuario() {

    }
}
