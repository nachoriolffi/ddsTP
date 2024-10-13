package ar.edu.utn.frba.dds.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class RespuestaDTO {
    private Map<Long, String> respuestasAbiertas;
    private Map<Long, Long> respuestasUnicas;
    private Map<Long, List<Long>> respuestasMultiples;
    private Map<Long, String> respuestasFechas;
}
