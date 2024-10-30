package ar.edu.utn.frba.dds.Persistencia;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.contacto.telegram.Telegram;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
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
        colaborador2.agregarColaboracionRealizada(donacionDinero);
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
        MedioDeComunicacion medioDeComunicacion = new CorreoElectronico();
        colaborador.agregarMedioDeComunicacion(medioDeComunicacion);



        repoColaborador.agregar(colaborador);

        Colaborador colaborador1 = RepoColaborador.INSTANCE.buscar(1L);
        colaborador1.getMediosDeComunicacion().forEach(medioDeComunicacion1 -> System.out.println(medioDeComunicacion1.getClass().getName()));
        //colaborador1.getMediosDeComunicacion().forEach(medioDeComunicacion1 -> medioDeComunicacion.comunicar(null));
        MedioDeComunicacion correo = new CorreoElectronico();

        Coordenada coordenadaTecnico = new Coordenada();
        coordenadaTecnico.setLatitud(-34.615803);
        coordenadaTecnico.setLongitud(-58.433298);

        RepoCoordenada.INSTANCE.agregar(coordenadaTecnico);

        Contacto contacto = new Contacto();
        contacto.setTipoContacto(TipoContacto.MAIL);
        contacto.setDescripcion("federperez@frba.utn.edu.ar");
        RepoContacto.INSTANCE.agregar(contacto);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Tecnico1");
        tecnico.setApellido("Apellido1");
        tecnico.setCoordenada(coordenadaTecnico);
        tecnico.setAreaCobertura((int) 10.0);
        tecnico.setDni(Integer.valueOf("12345678"));
        tecnico.setCuil(Integer.valueOf("12345678"));
        tecnico.setTipoDocumento(TipoDocumento.DNI);
        tecnico.setDisponible(true);
        tecnico.agregarContacto(contacto);
        tecnico.agregarMedioDeComunicacion(correo);
        RepoTecnico.INSTANCE.agregar(tecnico);

    }
}
