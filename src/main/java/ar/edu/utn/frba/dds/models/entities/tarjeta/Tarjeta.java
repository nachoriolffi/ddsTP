package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
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
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Integer id_Tarjeta;
    @OneToMany
    @JoinColumn(name = "id_UsoTarjeta")
    private List<UsoTarjeta> registroUsos;
    @OneToOne
    @JoinColumn(name = "id_Vulnerable")
    private Vulnerable personaAsociada;
    @ManyToOne
    @JoinColumn(name = "id_Colaborador",nullable = false)
    private Colaborador colaboradorAsociado;
    @Column(name="fechaRegistro",columnDefinition = "Date")
    private Date fechaRegistro;

    public Vianda sacarVianda(Vianda viandaQuitada, Heladera heladera)throws IOException {
        if(!alcanzaUsosLimite()){
        heladera.getViandas().remove(viandaQuitada);
        registroUsos.add(new UsoTarjeta(new Date(), heladera));
        return viandaQuitada;
        }
        throw new IOException("No se puede sacar mas viandas alcanza su limite permitido");

    }

    public Tarjeta(Integer idTarjeta, Vulnerable personaAsociada, Colaborador colaboradorAsociado) {
        this.id_Tarjeta = idTarjeta;
        this.personaAsociada = personaAsociada;
        this.colaboradorAsociado = colaboradorAsociado;
        this.fechaRegistro = new Date();
        this.registroUsos= new ArrayList<UsoTarjeta>();
    }

    public Boolean alcanzaUsosLimite(){
        return registroUsos.size()==this.cantidadMaximaUsos();
    }

    public Integer cantidadMaximaUsos(){
        return 4 + this.personaAsociada.getRegistroDePersonasACargo().size()*2;
    }

}
