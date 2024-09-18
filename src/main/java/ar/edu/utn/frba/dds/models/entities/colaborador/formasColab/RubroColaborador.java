package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import lombok.Setter;

import javax.persistence.*;


@Setter
@Entity
@Table(name = "rubro_colaborador")
public class RubroColaborador extends FormaDeColaboracion {

    @Column(name = "nombreRubro", columnDefinition = "VARCHAR(255)")
    String nombre;

    public RubroColaborador() {
    }

    public RubroColaborador(String nombre) {
        this.nombre = nombre;
    }
}
