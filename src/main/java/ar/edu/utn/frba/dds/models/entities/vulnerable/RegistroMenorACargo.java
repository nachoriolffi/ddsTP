package ar.edu.utn.frba.dds.models.entities.vulnerable;

import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "registroPersonaACargo")
@Setter
public class RegistroMenorACargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_RegistroPersonaACargo;
    @Column(name = "tienePersonaACargo")
    private Boolean tienePersonaACargo;
    @Column(name = "fechaRegistro")
    private Date fechaRegistro;
    @Column(name = "personasACargo")
    private Integer cantidadDePersonasACargo;

    @ManyToOne
    @JoinColumn(name = "vulnerable_id", nullable = false)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Vulnerable vulnerable;

    public RegistroMenorACargo(){

    }

    public RegistroMenorACargo(Boolean tienePersonaACargo, Date fecaRegistro, Integer cantidadDePersonasACargo) {
        this.tienePersonaACargo = tienePersonaACargo;
        this.fechaRegistro = fecaRegistro;
        this.cantidadDePersonasACargo = cantidadDePersonasACargo;
    }
}
