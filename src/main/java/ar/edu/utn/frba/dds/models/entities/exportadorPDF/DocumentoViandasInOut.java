package ar.edu.utn.frba.dds.models.entities.exportadorPDF;

import ar.edu.utn.frba.dds.models.entities.exportadorPDF.Exportable;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroApertura;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentoViandasInOut implements Exportable {


    private  Map<String, List<String>>  datos;

    private RepoHeladeras repoHeladeras;

    public DocumentoViandasInOut() {
        this.datos = new HashMap<String, List<String>>();
        this.repoHeladeras = RepoHeladeras.INSTANCE;
    }

    @Override
    public Map<String, List<String>> datos() {
        return this.datos;
    }

    public void generarDocumento() {

        List<String> heladerasNombre = new ArrayList<String>();
        List<String> ingresos = new ArrayList<String>();
        List<String> retiros = new ArrayList<String>();

        List<Heladera> heladeras = repoHeladeras.INSTANCE.buscarTodos();
        // ya tengo las heladeras necesito los registros de aperturas
        for(Heladera heladera : heladeras){
            if(!heladera.getAperturas().isEmpty()) {
                heladerasNombre.add(heladera.getNombre());
                ingresos.add(contarIngresos(heladera));
                 retiros.add(contadorRetiros(heladera));
            }
        }

        datos.put("Heladera", heladerasNombre);
        datos.put("Cantidad Viandas Ingresadas", ingresos);
        datos.put("Cantidad Viandas Retiradas", retiros);
    }
    public String contarIngresos (Heladera heladera){
        int contador_Ingresos = 0;
        for(RegistroApertura registro : heladera.getAperturas()){
            if(registro.getSolicitud() == TipoSolicitud.DONACION_VIANDA ||
                    (registro.getSolicitud() ==TipoSolicitud.REDISTRIBUCION_VIANDAS && (registro.getRetiroVianda()== Boolean.FALSE))){
                contador_Ingresos+=registro.getViandas().size();
            }
        }
        return String.valueOf(contador_Ingresos);
    }
    public String contadorRetiros(Heladera heladera){
        int contadorRetiros =0;
        for (int i =0; i < heladera.getAperturas().size();i++){
            RegistroApertura apertura = heladera.getAperturas().get(i);
            if (apertura.getSolicitud() == TipoSolicitud.REDISTRIBUCION_VIANDAS && apertura.getRetiroVianda() ){
                contadorRetiros += apertura.getViandas().size();
            }
        }
        return String.valueOf(contadorRetiros);
    }
}
