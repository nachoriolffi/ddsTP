package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.MotivoDistribucion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.CronJobReporte;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroApertura;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Collections;
import java.util.Date;
import java.util.List;

class TestCronJobReporte {

    /*DocumentoFallasHeladera exportable1;
    DocumentoFallasHeladera exportable2;
    DocumentoFallasHeladera exportable3;
    Map<String, List<String>> fallasHeladeras;
    Map<String, List<String>> viandasRetCol;
    Map<String, List<String>> viandasColaborador;

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

    */
    @BeforeEach
    public void setUp() {
        // primero tengo que crear las heladeras

        ModeloHeladera modeloPDF = new ModeloHeladera();
        modeloPDF.setTemperaturaMinima(3.0);
        modeloPDF.setTemperaturaMaxima(15.0);
        modeloPDF.setPeso(200.0);
        modeloPDF.setCantidadMaximaDeViandas(500);
        modeloPDF.setNombreModelo("Modelo PDF");
        RepoModelo.INSTANCE.agregar(modeloPDF);

        Vianda vianda3 = new Vianda();

        vianda3.setComida("Comida 1");
        vianda3.setFechaDonacion(new Date());

        RegistroApertura registroAperturaSACAR = new RegistroApertura();
        registroAperturaSACAR.setFechaApertura(new Date());
        registroAperturaSACAR.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroAperturaSACAR.setRetiroVianda(Boolean.TRUE);
        registroAperturaSACAR.setViandas(Collections.singletonList(vianda3));
        RepoRegistroApertura.INSTANCE.agregar(registroAperturaSACAR);

        Heladera heladera3 = new Heladera();
        heladera3.setNombre("Heladera Medrano");
        heladera3.setEstaActiva(true);
        heladera3.agregarApertura(registroAperturaSACAR);
        heladera3.setModelo(modeloPDF);
        RepoHeladeras.INSTANCE.agregar(heladera3);

        vianda3.setHeladera(heladera3);
        RepoViandas.INSTANCE.agregar(vianda3);

        Vianda vianda4 = new Vianda();
        vianda4.setHeladera(heladera3);
        vianda4.setComida("Comida 4");
        vianda4.setFechaDonacion(new Date());
        RepoViandas.INSTANCE.agregar(vianda4);
        
        RegistroApertura registroAperturaPONER = new RegistroApertura();
        registroAperturaPONER.setFechaApertura(new Date());
        registroAperturaPONER.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroAperturaPONER.setRetiroVianda(Boolean.FALSE);
        registroAperturaPONER.setViandas(Collections.singletonList(vianda4));
        RepoRegistroApertura.INSTANCE.agregar(registroAperturaPONER);

        Incidente incidente3 = new Incidente();
        incidente3.setDescripcion("Incidente de prueba 1");
        incidente3.setPathFoto(null);
        incidente3.setTipoIncidente(TipoIncidente.FALLA);
        incidente3.setTipoAlerta(null);

        RepoHeladeras.INSTANCE.agregar(heladera3);
        incidente3.setHeladera(heladera3);
        RepoIncidente.INSTANCE.agregar(incidente3);
        heladera3.agregarRegistroDeAlerta(incidente3);
        RepoHeladeras.INSTANCE.modificar(heladera3);

        DonacionVianda donacionVianda3 = new DonacionVianda();
        donacionVianda3.setFechaColaboracion(new Date());
        donacionVianda3.setVianda(vianda3);

        DonacionVianda donacionVianda4 = new DonacionVianda();
        donacionVianda4.setFechaColaboracion(new Date());
        donacionVianda4.setVianda(vianda4);


        Colaborador colaborador3 = new Colaborador();
        colaborador3.setNombre("Juan");
        colaborador3.setApellido("Perez");
        colaborador3.setTipoPersona(TipoPersona.HUMANA);
        vianda3.setColaborador(colaborador3);
        colaborador3.agregarColaboracionRealizada(donacionVianda3);
        colaborador3.agregarColaboracionRealizada(donacionVianda4);
        RepoColaborador.INSTANCE.agregar(colaborador3);
        RepoDonacionVianda.INSTANCE.agregar(donacionVianda3);
        RepoDonacionVianda.INSTANCE.agregar(donacionVianda4);

        Heladera heladera4 = new Heladera();
        heladera4.setNombre("Heladera de Fede");
        heladera4.setEstaActiva(true);
        heladera4.setModelo(modeloPDF);
        heladera4.agregarApertura(registroAperturaPONER);
        RepoHeladeras.INSTANCE.agregar(heladera4);

        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setFechaColaboracion(new Date());
        distribucionVianda.setCantidadViandas(10);
        distribucionVianda.setMotivo(MotivoDistribucion.FALTA_VIANDAS_H_DESTINO);
        distribucionVianda.setHeladeraOrigen(heladera3);
        distribucionVianda.setHeladeraDestino(heladera4);
        distribucionVianda.setTipoColaboracion(TipoColaboracion.REDISTRIBUCION_VIANDAS);
        distribucionVianda.setColaborador(colaborador3);
        RepoDistribucionVianda.INSTANCE.agregar(distribucionVianda);
    }

    @Test
    void test(){
        CronJobReporte cronJobReporte = new CronJobReporte();
        cronJobReporte.ejecutarReporte();

    }
}
