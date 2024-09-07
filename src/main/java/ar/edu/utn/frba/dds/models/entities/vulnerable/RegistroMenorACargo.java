package ar.edu.utn.frba.dds.models.entities.vulnerable;

import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registroPersonaACargo")
@Setter
public class RegistroMenorACargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "tienePersonaACargo",nullable = false)
    private Boolean tienePersonaACargo;
    @Column(name = "fechaRegistro",columnDefinition = "Date")
    private Date fechaRegistro;
    @Column(name = "personasACargo",columnDefinition = "INT")
    private Integer cantidadDePersonasACargo;

    public RegistroMenorACargo(){

    }

    public RegistroMenorACargo(Boolean tienePersonaACargo, Date fecaRegistro, Integer cantidadDePersonasACargo) {
        this.tienePersonaACargo = tienePersonaACargo;
        this.fechaRegistro = fecaRegistro;
        this.cantidadDePersonasACargo = cantidadDePersonasACargo;
    }
}
