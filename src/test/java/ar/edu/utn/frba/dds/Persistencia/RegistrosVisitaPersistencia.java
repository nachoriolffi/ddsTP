package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.entities.tecnico.RegistroVisita;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCoordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class RegistrosVisitaPersistencia {

    @Test
    public void persistirRegistroVisita(){

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Juancito");
        tecnico.setApellido("Perez");
        tecnico.setTipoDocumento(TipoDocumento.DNI);
        tecnico.setDNI(35142987);
        tecnico.setDisponible(Boolean.TRUE);
        Coordenada coordenada = new Coordenada(14.45,45.12);
        RepoCoordenada repoCoordenada = new RepoCoordenada();
        repoCoordenada.agregar(coordenada);
        tecnico.setCoordenada(coordenada);

        RepoTecnico repoTecnico = new RepoTecnico();
        repoTecnico.agregar(tecnico);

        RepoRegistrosVisita repoRegistrosVisita = new RepoRegistrosVisita();
        RegistroVisita registroVisita = new RegistroVisita();
        registroVisita.setFechaVisita(new Date());
        registroVisita.setTecnico(tecnico);
        registroVisita.setDescripcion("Fue a ver la heladera y tenia un hacha en la puerta, no se puede arreglar");

        Incidente incidente = new Incidente();
        incidente.setTipoIncidente(TipoIncidente.FALLA);
        incidente.setFecha(new Date());
        incidente.setDescripcion("Me encontre la heladera con un hacha, pero me olvide de sacarle foto :D");

        Colaborador colaborador = new Colaborador();


        incidente.setColaborador(colaborador);

        registroVisita.setIncidenteASolucionar(incidente);

        repoRegistrosVisita.agregar(registroVisita);
    }

}
