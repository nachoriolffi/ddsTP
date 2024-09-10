package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "usoTarjeta")
public class UsoTarjeta {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id_UsoTarjeta;
    @Column(name = "fechaUso",columnDefinition = "Date",nullable = false)
    private Date fechaUso;
    @OneToOne
    @JoinColumn(name = "id_Heladera",nullable = false)
    private Heladera heladera;

    public UsoTarjeta(Date fechaUso, Heladera heladera) {
        this.fechaUso = fechaUso;
        this.heladera = heladera;
    }

    public UsoTarjeta() {

    }
}
