package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Table(name = "rubro_colaborador")
public class RubroColaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "nombreRubro", columnDefinition = "VARCHAR(255)")
    String nombre;

    public RubroColaborador() {
    }

    public RubroColaborador(String nombre) {
        this.nombre = nombre;
    }
}
