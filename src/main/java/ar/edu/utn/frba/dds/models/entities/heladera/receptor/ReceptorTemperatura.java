package ar.edu.utn.frba.dds.models.entities.heladera.receptor;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "receptorTemperatura")
public class ReceptorTemperatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "receptorTemperatura", cascade = CascadeType.ALL)
    private Heladera heladera;

    @Setter
    @OneToMany
    @JoinColumn(name = "registroTemperatura_id")
    private List<RegistroTemperatura> temperaturasLeidas;

    public ReceptorTemperatura(List<RegistroTemperatura> temperaturasLeidas) {
        this.temperaturasLeidas = temperaturasLeidas;
    }

    public ReceptorTemperatura() {
        this.temperaturasLeidas= new ArrayList<>();
    }

    public void agregarTemperatura(float temperatura) {
        RegistroTemperatura registro = new RegistroTemperatura(temperatura, new Date());
        temperaturasLeidas.add(registro);
    }

    public void evaluarTemperatura(String dato, Heladera heladera) {
        if (evaluarLimitesTemperatura(Float.parseFloat(dato),heladera)){
            registrarIncidente(heladera, TipoAlerta.TEMPERATURA);
        }
        //hay que hacer un RegistroTemperatura y despues agregarlo
        RegistroTemperatura registro = new RegistroTemperatura(Float.parseFloat(dato), new Date());

        temperaturasLeidas.add(registro);
    }

    private Boolean evaluarLimitesTemperatura(Float temperatura, Heladera heladera) {
        return evaluarTemperaturaMaxima(temperatura,heladera) || evaluarTemperaturaMinima(temperatura,heladera);
    }

    private boolean evaluarTemperaturaMaxima(Float temperatura, Heladera heladera) {
        return temperatura > heladera.getModelo().getTemperaturaMaxima();
    }

    private boolean evaluarTemperaturaMinima(Float temperatura, Heladera heladera) {
        return temperatura < heladera.getModelo().getTemperaturaMinima();
    }
    public void registrarIncidente(Heladera heladera, TipoAlerta tipoAlerta) {
        Incidente registro = new Incidente(tipoAlerta);
       // registro.notificarTecnicoMasCercano(heladera); //DESCOMENTAR NO SEAMOS SALAMES QUE DESPUES NO ANDA LO DEL TECNICO
        heladera.agregarRegistroDeAlerta(registro);
        heladera.setEstaActiva(false);
    }

    public void agregarRegistro(RegistroTemperatura registro){
        temperaturasLeidas.add(registro);
    }
}
