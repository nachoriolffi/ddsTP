package ar.edu.utn.frba.dds.models.entities.colaborador.formasColab;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.multiplicador.config.ConfiguracionMultiplicador;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="registro_vulnerable")
public class RegistroVulnerable extends FormaDeColaboracion {

    @OneToMany
    @JoinColumn(name = "id")
    private List<Tarjeta> tarjetasDonadas;

    @Column(name="cantidadTarjetas", columnDefinition = "INT")
    private Integer cantidadTarjetas;

    @Column(name="fechaColaboracion", columnDefinition = "DATE",nullable = false)
    private Date fechaColaboracion;

    @Enumerated(EnumType.STRING)
    private TipoColaboracion tipoColaboracion = TipoColaboracion.ENTREGA_TARJETAS;

    public RegistroVulnerable(){
        tarjetasDonadas = new ArrayList<>();
    }

    public RegistroVulnerable(List<Tarjeta> tarjetasDonadas) {
        this.tarjetasDonadas = tarjetasDonadas;
        this.fechaColaboracion = new Date();
        this.cantidadTarjetas = tarjetasDonadas.size();
    }

    public RegistroVulnerable(Integer cantidad, Date fechaColaboracion) {
        this.fechaColaboracion = fechaColaboracion;
        this.cantidadTarjetas = cantidad;
    }

    @Override
    public double sumarPuntosA(Colaborador colaborador) {
        return cantidadTarjetas * ConfiguracionMultiplicador.getInstance().getMultiplicadorRegistroVulnerables();
    }
}
