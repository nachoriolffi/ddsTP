package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "tarjeta")
@Entity
public class Tarjeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "usoTarjeta_id")
    private List<UsoTarjeta> registroUsos;

    @OneToOne
    @JoinColumn(name = "vulnerable_id")
    private Vulnerable personaAsociada;

    @ManyToOne
    @JoinColumn(name = "colaborador_id", nullable = false)
    private Colaborador colaboradorAsociado;

    @Column(name = "fechaRegistro", nullable = false)
    private Date fechaRegistro;

    public void sacarVianda(Heladera heladera) throws IOException {
        if (!alcanzaUsosLimite()) {
            heladera.quitarVianda();
            registroUsos.add(new UsoTarjeta(new Date(), heladera));
        }
        throw new IOException("No se puede sacar mas viandas alcanza su limite permitido");
    }

    public Tarjeta() {
    }

    public Tarjeta(Vulnerable personaAsociada, Colaborador colaboradorAsociado) {
        this.personaAsociada = personaAsociada;
        this.colaboradorAsociado = colaboradorAsociado;
        this.fechaRegistro = new Date();
        this.registroUsos = new ArrayList<UsoTarjeta>();
    }

    public Boolean alcanzaUsosLimite() {
        return registroUsos.size() == this.cantidadMaximaUsos();
    }

    public Integer cantidadMaximaUsos() {
        return 4 + this.personaAsociada.getMenoresACargo().size() * 2;
    }

}
