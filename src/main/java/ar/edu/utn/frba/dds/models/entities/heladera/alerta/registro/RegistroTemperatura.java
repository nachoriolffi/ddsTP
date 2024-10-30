package ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@Entity
@Table(name = "registro_temperatura")
public class RegistroTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lectura")
    private Float lectura;

    @Column(name = "fechaHora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    public RegistroTemperatura(Float lectura, Date fechaHora) {
        this.lectura = lectura;
        this.fechaHora = fechaHora;
    }

    public RegistroTemperatura() {

    }
}
