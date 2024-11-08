package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.generadorCodigo.GeneradorDeCodigo;
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
    @Column(name = "codigo", columnDefinition = "VARCHAR(11)")
    private String codigo; // codigo alfanumerico unico por tarjeta

    @OneToMany
    @JoinColumn(name = "usoTarjeta_id")
    private List<UsoTarjeta> registroUsos;

    @ManyToOne
    @JoinColumn(name = "colaboradorAsignador_id")
    private Colaborador colaboradorAsignador;

    @OneToOne
    @JoinColumn(name = "vulnerable_id")
    private Vulnerable personaAsociada;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaboradorAsociado;

    @Column(name = "fechaRegistro")
    private Date fechaRegistro;

    public void sacarVianda(Heladera heladera) throws IOException {
        if (!alcanzaUsosLimite()) {
            heladera.quitarVianda();
            registroUsos.add(new UsoTarjeta(new Date(), heladera));
        }
        throw new IOException("No se puede sacar mas viandas alcanza su limite permitido");
    }

    public Tarjeta() {
        //this.codigo = GeneradorDeCodigo.getInstance().generarCodigoUnico();
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
