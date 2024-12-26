package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Table(name = "tarjeta")
@Entity
public class Tarjeta {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "codigo", columnDefinition = "VARCHAR(11)")
    private String codigo;

    @OneToMany
    @JoinColumn(name = "usoTarjeta_id")
    private List<UsoTarjeta> registroUsos;

    @ManyToOne
    @JoinColumn(name = "colaboradorAsignador_id")
    private Colaborador colaboradorAsignador;

    @OneToOne
    @JoinColumn(name = "vulnerable_id")
    private Vulnerable personaAsociada;

    @OneToOne
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaboradorAsociado;

    @Column(name = "fechaRegistro")
    private LocalDate fechaRegistro;

    public Tarjeta() {
    }

    // TODO pasar al servicio este metodo
    public void sacarVianda(Heladera heladera, Vianda vianda) {
        if (!alcanzaUsosLimite()) {
            heladera.quitarVianda(vianda);
            UsoTarjeta usoTarjeta = UsoTarjeta.builder().fechaUso(LocalDate.now()).heladera(heladera).build();
            registroUsos.add(usoTarjeta);
        }
    }

    public Boolean alcanzaUsosLimite() {
        return this.registroUsos.size() == this.cantidadMaximaUsos();
    }

    public Integer cantidadMaximaUsos() {
        return 4 + this.personaAsociada.getMenoresACargo().size() * 2;
    }

}
