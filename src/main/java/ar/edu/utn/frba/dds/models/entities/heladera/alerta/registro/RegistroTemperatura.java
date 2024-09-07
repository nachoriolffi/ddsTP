package ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter
@Entity
@Table(name = "registrosTemperatira")
public class RegistroTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_RegistroTemperatura;
    @Column(name = "lectura")
    Float lectura;
    @Column(name = "fechaHora",columnDefinition = "Date")
    Date fechaHora;

    public RegistroTemperatura(Float lectura, Date fechaHora) {
        this.lectura = lectura;
        this.fechaHora = fechaHora;
    }

    public RegistroTemperatura() {

    }
}
