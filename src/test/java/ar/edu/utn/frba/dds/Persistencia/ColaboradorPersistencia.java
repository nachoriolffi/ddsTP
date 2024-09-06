package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridisccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import org.junit.jupiter.api.Test;

public class ColaboradorPersistencia {

    @Test
    public void persistirColaborador(){
        RepoColaborador repoColaborador = new RepoColaborador();
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNombre("Pepito");
        colaborador1.setApellido("Perez");
        colaborador1.setTipoPersona(TipoPersona.HUMANA);

        repoColaborador.agregar(colaborador1);
        Colaborador colaborador2 =  new Colaborador();
        colaborador2.setRazonSocial("Accenture");
        colaborador2.setTipoPersona(TipoPersona.JURIDICA);
        colaborador2.setTipoJuridisccion(TipoJuridisccion.EMPRESA);
        //colaborador2.setRubro(Rubro.TECNOLOGIA);
        repoColaborador.agregar(colaborador2);
    }
}
