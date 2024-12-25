package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;

import java.util.*;

public class DocumentoViandasDonadas implements Exportable {
    
    private Map<String, List<String>>  datos;
    private final RepoColaborador repoColaborador;

    public DocumentoViandasDonadas() {
        this.datos = new HashMap<String, List<String>>();
        this.repoColaborador = RepoColaborador.INSTANCE;
        generarDocumento();
    }

    @Override
    public Map<String, List<String>> datos() {
        return this.datos;
    }

    @Override
    public void generarDocumento(){

        List<String> colaboradoresNombre = new ArrayList<String>();
        List<String> cantidades = new ArrayList<String>();

        for(Colaborador colaborador : RepoColaborador.INSTANCE.buscarTodos()){
            if(!colaborador.getColaboracionesRealizadas().isEmpty()) {
                List<FormaDeColaboracion> colaboraciones = colaborador.getColaboracionesRealizadas();
                colaboradoresNombre.add(colaborador.getNombre());
                Integer cantidad = 0;
                for(FormaDeColaboracion colaboracion : colaboraciones){
                    if(Objects.equals(colaboracion.getTipoColaboracion(), TipoColaboracion.DONACION_VIANDAS)){
                        cantidad++; // solamente se puede donar de una vianda a la vez
                    }
                }
              cantidades.add(String.valueOf(cantidad));
            }
        }
        datos.put("Colaborador", colaboradoresNombre);
        datos.put("Cantidad Viandas Donadas", cantidades);
    }


}
