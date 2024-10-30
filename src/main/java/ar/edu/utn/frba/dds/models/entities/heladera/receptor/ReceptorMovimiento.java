package ar.edu.utn.frba.dds.models.entities.heladera.receptor;

import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoIncidente;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroMovimiento;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "receptor_movimiento")
public class ReceptorMovimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(mappedBy = "receptorMovimiento", cascade = CascadeType.ALL)
    private Heladera heladera;
    @Transient
    private Broker broker;

    public ReceptorMovimiento(Heladera heladera) {
        this.heladera = heladera;
    }

    public ReceptorMovimiento() {
    }

   public void evaluarDatosSensor(String dato,Heladera heladera){

        registrarAlerta(heladera, TipoAlerta.ROBO);
       //reportarAlerta(heladera, TipoAlerta.ROBO);

        //throw new RuntimeException("Se detecto movimiento en la heladera");
        // tengo mis dudas de que esto se maneja en otr p[arte y noa aca en una clase de exptions


   }

    public void registrarAlerta(Heladera heladera, TipoAlerta tipoAlerta) {
        Incidente registro = new Incidente(tipoAlerta);
        registro.setHeladera(heladera);
        registro.setFecha(new Date());
        registro.setDescripcion("Se detecto movimiento en la heladera");
        RepoIncidente.INSTANCE.agregar(registro);
        heladera.agregarRegistroDeAlerta(registro);
        heladera.setEstaActiva(false);
    }



}
