package ar.edu.utn.frba.dds.models.entities.colaborador.observer;

import javax.persistence.*;

@Entity
@Table(name = "observer")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class IObserverColaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public abstract void recibirNotificacion(String mensaje);
}
