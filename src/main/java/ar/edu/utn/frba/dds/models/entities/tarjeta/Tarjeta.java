package ar.edu.utn.frba.dds.models.entities.tarjeta;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.entities.vulnerable.Vulnerable;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class Tarjeta {
    
    private Integer idTarjeta;
    private List<UsoTarjeta> registroUsos;
    private Vulnerable personaAsociada;
    private Colaborador colaboradorAsociado;
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
        this.idTarjeta = idTarjeta;
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
