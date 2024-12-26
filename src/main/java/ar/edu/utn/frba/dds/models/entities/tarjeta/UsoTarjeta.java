package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.converters.LocalDateConverter;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@Table(name = "usoTarjeta")
public class UsoTarjeta {

    @Id
    @GeneratedValue
    private Long id;

    @Convert(converter = LocalDateConverter.class)
    @Column(name = "fechaUso")
    private LocalDate  fechaUso;

    @OneToOne
    @JoinColumn(name = "heladera_id")
    private Heladera heladera;

    public UsoTarjeta() {

    }
}
