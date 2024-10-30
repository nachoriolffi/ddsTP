package ar.edu.utn.frba.dds;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.CronJobReporte;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.DocumentoFallasHeladera;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.adapterPDF.ExportarAPdf;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroApertura;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TestExportadorPdf {

    private DocumentoFallasHeladera exportable1;
    private DocumentoFallasHeladera exportable2;
    private DocumentoFallasHeladera exportable3;
    Map<String, List<String>> fallasHeladeras;
    Map<String, List<String>> viandasRetCol;
    Map<String, List<String>> viandasColaborador;

    @BeforeEach
     public void setUp() {

        RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;

        fallasHeladeras = Map.of(
                "Heladera", List.of("Heladera Medrano", "Heladera Campus","Heladera FMED"),
                "Cantidad Fallas", List.of("10", "2","15")
        );
        viandasRetCol = Map.of(
                "Heladera", List.of("Heladera Medrano"),
                "Viandas Colocadas", List.of("60"),
                "Viandas Retiradas", List.of("30")
        );
        viandasColaborador = Map.of(
                "Colaborador", List.of("Juancito Perez","Luciana Maria"),
                "Cantidad Viandas Donadas", List.of("100","50")
        );
        exportable1 = new DocumentoFallasHeladera(fallasHeladeras);
        exportable2= new DocumentoFallasHeladera(viandasRetCol);
        exportable3 = new DocumentoFallasHeladera(viandasColaborador);

        // necesitamos cargar los datos dentro de los repor para los documentos
        //ModeloHeladera modelo = new ModeloHeladera(18.0,5.0,70.0,200);
        // fallas de heladeras
        /*Heladera heladera1 = new Heladera();
        heladera1.setNombre("HELADERA 1");
        heladera1.setModelo(modelo);
        heladera1.setIncidentes(new ArrayList<>());
        Heladera heladera2 = new Heladera();
        heladera2.setNombre("HELADERA 2");
        heladera2.setModelo(modelo);
        heladera2.setIncidentes(new ArrayList<>());
        Incidente registroIncidente1 = new Incidente();
        registroIncidente1.setTipoIncidente(TipoIncidente.FALLA);
        Incidente registroIncidente2 = new Incidente();
        registroIncidente2.setTipoIncidente(TipoIncidente.ALERTA);
        Incidente registroIncidente3 = new Incidente();
        registroIncidente3.setTipoIncidente(TipoIncidente.FALLA);
        Incidente registroIncidente4 = new Incidente();
        registroIncidente4.setTipoIncidente(TipoIncidente.FALLA);

        // agrego a la heladera sus fallas
        heladera1.agregarRegistroDeAlerta(registroIncidente1);
        heladera1.agregarRegistroDeAlerta(registroIncidente2);
        heladera1.agregarRegistroDeAlerta(registroIncidente3);
        heladera1.agregarRegistroDeAlerta(registroIncidente4);

        heladera2.agregarRegistroDeAlerta(registroIncidente1);
        heladera2.agregarRegistroDeAlerta(registroIncidente3);
        heladera2.agregarRegistroDeAlerta(registroIncidente2);

        Vianda vianda = new Vianda("Sopa", new Colaborador(), true);
        Vianda vianda1 = new Vianda("Carne" , new Colaborador(), true);
        Vianda vianda2 = new Vianda("Papas" , new Colaborador(), false);
        Vianda vianda3 = new Vianda("Milanesa", new Colaborador(), true);
        Vianda vianda4 = new Vianda("Pizza" , new Colaborador(), false);
        Vianda vianda5 = new Vianda("Fideos", new Colaborador(), true);
        Vianda vianda6 = new Vianda("Ravioles", new Colaborador(), true);

        // Ahora a las heladeras le cargo la cantidad de viandas que tiene

        List<Vianda> viandas= List.of(vianda, vianda1, vianda2, vianda3, vianda4, vianda5, vianda6);
        List<Vianda> viandas2= List.of(vianda,vianda1,vianda2,vianda4);
        List<Vianda> donacion1 = new ArrayList<>();
        donacion1.add(vianda);
        donacion1.add(vianda1);
        donacion1.add(vianda2);
        heladera1.setViandas(viandas);
        heladera2.setViandas(viandas2);
        List<Vianda> donacion2 = new ArrayList<>();
        donacion1.add(vianda);
        donacion1.add(vianda2);
        heladera1.setViandas(viandas);
        heladera2.setViandas(viandas2);
        List<Vianda> donacion3 = new ArrayList<>();
        donacion1.add(vianda);
        heladera1.setViandas(viandas);
        heladera2.setViandas(viandas2);

        // luego le agrego a la heladera registro de apertura
        // para la heladera 1
        RegistroApertura apertura= new RegistroApertura();
        apertura.setSolicitud(TipoSolicitud.DONACION_VIANDA);
        apertura.setViandas(donacion1);
       apertura.setRetiroVianda(Boolean.FALSE);

        RegistroApertura apertura1 = new RegistroApertura();
        apertura1.setSolicitud(TipoSolicitud.DONACION_VIANDA);
        apertura1.setViandas(donacion2);
       apertura1.setRetiroVianda(Boolean.TRUE);

        RegistroApertura apertura2 = new RegistroApertura();
        apertura2.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        apertura2.setViandas(donacion3);
        apertura2.setRetiroVianda(Boolean.TRUE);

        RegistroApertura apertura3 = new RegistroApertura();
        apertura3.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        apertura3.setViandas(donacion1);
       apertura3.setRetiroVianda(Boolean.TRUE);

        RegistroApertura apertura4 = new RegistroApertura();
        apertura4.setSolicitud(TipoSolicitud.DONACION_VIANDA);
        apertura4.setViandas(donacion2);
       apertura4.setRetiroVianda(Boolean.FALSE);
        // para la heladera 2
        RegistroApertura apertura5 = new RegistroApertura();
        apertura5.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        apertura5.setViandas(donacion3);
       apertura5.setRetiroVianda(Boolean.TRUE);
        RegistroApertura apertura6 = new RegistroApertura();
        apertura6.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        apertura6.setViandas(donacion3);
       apertura6.setRetiroVianda(Boolean.TRUE);
        RegistroApertura apertura7 = new RegistroApertura();
        apertura7.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        apertura7.setViandas(donacion3);
       apertura7.setRetiroVianda(Boolean.TRUE);

        List<RegistroApertura> aperturas1 = List.of(apertura,apertura1,apertura2,apertura3,apertura4);
        List<RegistroApertura> aperturas2 = List.of(apertura5,apertura6,apertura7);

        heladera1.setAperturas(aperturas1);
        heladera2.setAperturas(aperturas2);
        // esto significa que un colaborador entro a la heladera y saco o agrego heladeras*/

    // COLABORADORES
     /*   Colaborador colaborador1 = new Colaborador();

        FormaDeColaboracion formaDeColaboracion1 = new DonacionVianda(vianda, new Date());

        colaborador1.agregarColaboracionRealizada(formaDeColaboracion1);
        RepoColaborador.INSTANCE.agregar(colaborador1);
        // ya tengo el colaborador cargado en el repo

        repoHeladeras.agregar(heladera1);
        repoHeladeras.agregar(heladera2);*/
    }

    @Test
    public void testExportarPdf() throws IOException {
        // Exportar a PDF
        ExportarAPdf exportarAPdf = new ExportarAPdf();
        exportarAPdf.exportar(exportable1, exportable2, exportable3);
    }

   /* @Test
    public void testDeCroneJob() throws IOException {
        /*ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "target/ejercicio-1.0-SNAPSHOT.jar");

        try {
            Process process = processBuilder.start();

            // Leer la salida estándar del proceso
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // Leer la salida de error del proceso
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.err.println(line);
                }
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }*/
        /*CronJobReporte cronJobReporte = new CronJobReporte();
        cronJobReporte.main(new String[0]);
    }*/
}