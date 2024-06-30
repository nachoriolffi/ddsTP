package ar.edu.utn.frba.dds.dtos.outputs;

import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class HeladeraOutputDTO {
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
