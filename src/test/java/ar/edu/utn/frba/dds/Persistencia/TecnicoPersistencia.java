package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCoordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoGenerico;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistrosVisita;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

public class TecnicoPersistencia {

    @Test
    public void persistirTecnico(){
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

    }

    @Test
    public void borrarTecnico() {
        RepoTecnico repoTecnico = new RepoTecnico();
        Tecnico tecnico = repoTecnico.buscar(1L);
        repoTecnico.eliminar(tecnico);
    }
    @Test
    public void modificarTecnico() {
        RepoTecnico repoTecnico = new RepoTecnico();
        Tecnico tecnico = repoTecnico.buscar(1L);
        tecnico.setCUIL(22 - 35142987 - 5); // cambiar esto por un string luego
        tecnico.setNombre("Juancito II");
        repoTecnico.modificar(tecnico);
    }
}
