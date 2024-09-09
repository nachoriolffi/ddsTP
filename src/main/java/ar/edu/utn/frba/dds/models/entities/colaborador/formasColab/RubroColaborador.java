package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import lombok.Setter;

import javax.persistence.*;


@Setter
@Entity
@Table(name = "rubroColaborador")
public class RubroColaborador extends FormaDeColaboracion {

    @Column(name = "nombre", columnDefinition = "VARCHAR(25)")
    String nombre;

    public RubroColaborador() {
    }

    public RubroColaborador(String nombre) {
        this.nombre = nombre;
    }
}
