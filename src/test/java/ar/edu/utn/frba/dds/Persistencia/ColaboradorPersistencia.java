package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridisccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRubroColaborador;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

public class ColaboradorPersistencia {

    @Test
    public void persistirColaborador(){
        RepoColaborador repoColaborador = new RepoColaborador();
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNombre("Pepito");
        colaborador1.setApellido("Perez");
        colaborador1.setTipoDocumento(TipoDocumento.DNI);
        colaborador1.setNumeroDocumento(32458652);
        colaborador1.setTipoPersona(TipoPersona.HUMANA);

        repoColaborador.agregar(colaborador1);
        Colaborador colaborador2 =  new Colaborador();
        colaborador2.setRazonSocial("Accenture");
        colaborador2.setTipoPersona(TipoPersona.JURIDICA);
        colaborador2.setTipoJuridisccion(TipoJuridisccion.EMPRESA);

        RepoRubroColaborador repoRubro = new RepoRubroColaborador();
        RubroColaborador rubro = new RubroColaborador();
        rubro.setNombre("Consultora");
        repoRubro.agregar(rubro);
        colaborador2.setRubroColaborador(rubro);
        DonacionDinero donacionDinero = new DonacionDinero(1000, null);
        RepoDonacionDinero.INSTANCE.agregar(donacionDinero);
        colaborador2.agregarFormaDeColaboracion(donacionDinero);
        repoColaborador.agregar(colaborador2);
    }

    @Test
    public void persistirDonacionDineroParaColaborador() {
        RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
        RepoDonacionDinero repoDonacionDinero = RepoDonacionDinero.INSTANCE;

        Colaborador colaborador = new Colaborador();
        colaborador.setNombre("Juan");
        colaborador.setApellido("Gomez");
        colaborador.setTipoDocumento(TipoDocumento.DNI);
        colaborador.setNumeroDocumento(12345678);
        colaborador.setTipoPersona(TipoPersona.HUMANA);

        DonacionDinero donacionDinero = new DonacionDinero(500, null);

        // Agregar la colaboración y asegurar la relación bidireccional
        colaborador.agregarColaboracionRealizada(donacionDinero);

        // Persistir colaborador y donación
        repoColaborador.agregar(colaborador);
    }
}
