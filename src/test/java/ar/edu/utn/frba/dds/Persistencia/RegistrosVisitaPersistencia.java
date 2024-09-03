package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class RegistrosVisitaPersistencia {

    @Test
    public void persistirRegistroVisita() {

        RepoCoordenada repoCoordenada = new RepoCoordenada();
        RepoTecnico repoTecnico = new RepoTecnico();
        RepoRegistrosVisita repoRegistrosVisita = new RepoRegistrosVisita();
        RepoIncidente repoIncidente = new RepoIncidente();
        RepoColaborador repoColaborador = new RepoColaborador();

        Coordenada coordenada = new Coordenada(14.45, 45.12);
        repoCoordenada.agregar(coordenada);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Juancito");
        tecnico.setApellido("Perez");
        tecnico.setTipoDocumento(TipoDocumento.DNI);
        tecnico.setDNI(35142987);
        tecnico.setDisponible(Boolean.TRUE);
        tecnico.setCoordenada(coordenada);

        repoTecnico.agregar(tecnico);

        Colaborador colaborador = new Colaborador();
        repoColaborador.agregar(colaborador);

        Incidente incidente = new Incidente();
        incidente.setTipoIncidente(TipoIncidente.FALLA);
        incidente.setFecha(new Date());
        incidente.setDescripcion("Fue a dejar viandas a la heladera y tenia un hacha en la puerta, me asuste");
        incidente.setColaborador(colaborador);
        repoIncidente.agregar(incidente);

        RegistroVisita registroVisita = new RegistroVisita();
        registroVisita.setFechaVisita(new Date());
        registroVisita.setTecnico(tecnico);
        registroVisita.setDescripcion("Me encontre la heladera con un hacha y no se puede arreglar, pero me olvide de sacarle foto :D");
        registroVisita.setIncidenteASolucionar(incidente);
        registroVisita.setProblemaSolucionado(Boolean.FALSE);
        repoRegistrosVisita.agregar(registroVisita);
    }
    // Los registros de visita no se pueden modificar
    @Test
    public void eliminarRegistroVisita() {
        RepoRegistrosVisita repoRegistrosVisita = new RepoRegistrosVisita();
        RegistroVisita registroVisita= repoRegistrosVisita.buscar(1L);
        repoRegistrosVisita.eliminar(registroVisita);
    }

}
