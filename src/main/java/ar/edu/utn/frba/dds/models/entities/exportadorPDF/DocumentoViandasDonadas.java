package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;

import java.util.*;
import java.util.stream.Collectors;

public class DocumentoViandasDonadas implements Exportable {
    
    private Map<String, List<String>>  datos;
    private RepoColaborador repoColaborador;

    public DocumentoViandasDonadas() {
        this.datos = new HashMap<String, List<String>>();
        this.repoColaborador = RepoColaborador.getInstancia();
    }

    @Override
    public Map<String, List<String>> datos() {
        return null;
    }

    @Override
    public void generarDocumento(){
        for(Colaborador colaborador : repoColaborador.buscarTodosColaboradors()){
            if(!colaborador.getColaboracionesRealizadas().isEmpty()) {
                List<FormaDeColaboracion> colaboraciones = colaborador.getColaboracionesRealizadas();
                for(FormaDeColaboracion colaboracion : colaboraciones){
                    if(Objects.equals(colaboracion.getTipoColaboracion(), "DONACION_VIANDAS")){
                        List<String> list = new ArrayList<>();
                        list.add(String.valueOf(colaboracion.getCantidadViandas()));
                        datos.put(colaborador.getNombre(), list);
                    }
                }
            }
        }

        Map<String, List<String>> donacionesViandas = new HashMap<>();
        List<String> colaboradores = new ArrayList<>(datos.keySet());
        List<String> cantidades = datos.values().stream()
                .map(list -> list.get(0)) // asumiendo que cada lista tiene al menos un elemento
                .collect(Collectors.toList());

        donacionesViandas.put("COLABORADOR", colaboradores);
        donacionesViandas.put("CANTIDAD", cantidades);
    }


}
