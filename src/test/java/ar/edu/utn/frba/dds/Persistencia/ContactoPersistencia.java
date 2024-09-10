package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.cuestionario.CuestionarioRespondido;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Respuesta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.TipoPregunta;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoContacto;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoCoordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoTecnico;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

import static ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto.*;

public class ContactoPersistencia {
    @Test
    public void persistirContacto(){
        Contacto contacto1 = new Contacto();
        Contacto contacto2 = new Contacto();
        Contacto contacto3 = new Contacto();


        contacto1.setTipoContacto(MAIL);
        contacto1.setDescripcion("ejemplo1");
        contacto2.setTipoContacto(WPP);
        contacto2.setDescripcion("ejemplo2");
        contacto3.setTipoContacto(TELEGRAM);
        contacto3.setDescripcion("ejemplo3");

        RepoContacto repoContacto = new RepoContacto();
        repoContacto.agregar(contacto1);
        repoContacto.agregar(contacto2);
        repoContacto.agregar(contacto3);
    }

    @Test
    public void borrarContacto() {
        RepoContacto repoContacto = new RepoContacto();
        Contacto contacto = repoContacto.buscar(1L);
        repoContacto.eliminar(contacto);
    }

    @Test
    public void modificarTecnico() {
        RepoContacto repoContacto = new RepoContacto();
        Contacto contacto = repoContacto.buscar(1L);
        contacto.setDescripcion("No molestar"); // cambiar esto por un string luego
        repoContacto.modificar(contacto);
    }

}
