package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.Telegram;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRubroColaborador;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import org.junit.jupiter.api.Test;

public class ColaboradorPersistencia {

    @Test
    public void persistirColaborador() {
        // Crear repositorios
        RepoColaborador repoColaborador = new RepoColaborador();
        RepoRubroColaborador repoRubro = new RepoRubroColaborador();

        // Crear y persistir colaborador humano
        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNombre("Pepito");
        colaborador1.setApellido("Perez");
        colaborador1.setTipoDocumento(TipoDocumento.DNI);
        colaborador1.setNumeroDocumento(32458652);
        colaborador1.setTipoPersona(TipoPersona.HUMANA);

        repoColaborador.agregar(colaborador1);

        // Crear y persistir colaborador jurídico
        Colaborador colaborador2 = new Colaborador();
        colaborador2.setRazonSocial("Accenture");
        colaborador2.setTipoPersona(TipoPersona.JURIDICA);
        colaborador2.setTipoJuridiccion(TipoJuridiccion.EMPRESA);

        // Crear y persistir rubro
        RubroColaborador rubro = new RubroColaborador();
        rubro.setNombre("Consultora");
        repoRubro.agregar(rubro);
        colaborador2.setRubroColaborador(rubro);

        // Crear y persistir donación de dinero
        DonacionDinero donacionDinero = new DonacionDinero(1000, null); // puedes quitar el null aquí
        RepoDonacionDinero.INSTANCE.agregar(donacionDinero);

        // Establecer relación bidireccional
        colaborador2.agregarFormaDeColaboracion(donacionDinero);
        donacionDinero.setColaborador(colaborador2); // Asegurarse de que la relación sea bidireccional

        // Persistir colaborador2 con su donación y rubro
        repoColaborador.agregar(colaborador2);

        // Opcional: Agregar asserts para verificar que las entidades se hayan guardado correctamente
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

    @Test
    public void cargadaDeMedioDEcomuinicacion(){
        RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre("Juan");
        colaborador.setApellido("Gomez");
        colaborador.setTipoDocumento(TipoDocumento.DNI);
        colaborador.setNumeroDocumento(12345678);
        colaborador.setTipoPersona(TipoPersona.HUMANA);
        MedioDeComunicacion medioDeComunicacion = new Telegram();
        colaborador.agregarMedioDeComunicacion(medioDeComunicacion);

        repoColaborador.agregar(colaborador);
    }
}
