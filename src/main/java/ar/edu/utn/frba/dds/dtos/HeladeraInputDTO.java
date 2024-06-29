package ar.edu.utn.frba.dds.dtos;


import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;


import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HeladeraInputDTO {
    private Direccion direccion;
    private Coordenada coordenada;
    private String nombre;
    private Integer capacidad;
    private Date fechaPuestaFunc;
    private Boolean estaActiva;
    private Double tempActual;

    private List<Long> idViandas;
    private Long idReceptorMovimiento;
    private Long idReceptorTemperatura;
    private List<Long> idIncidentes;
    private List<Long> idSolicitudesApertura;
    private List<Long> idAperturas;
    private long idModelo;
}
