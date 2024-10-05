package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionDinero;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionDinero;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRubroColaborador;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.text.ParseException;
import java.util.List;

public class RegistroJuridicoController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {
        context.render("logs/registroJuridico.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws ParseException {
        // Logging form parameters
        System.out.println("razon-social: " + context.formParam("razon-social"));
        System.out.println("tipo-razon-social: " + context.formParam("tipo-razon-social"));
        System.out.println("calle: " + context.formParam("calle"));
        System.out.println("altura: " + context.formParam("altura"));
        System.out.println("piso: " + context.formParam("piso"));
        System.out.println("telefono: " + context.formParam("telefono"));
        System.out.println("correo: " + context.formParam("correo"));

        RepoColaborador repoColaborador = new RepoColaborador();


        Colaborador colaboradorJuridico = new Colaborador();
        colaboradorJuridico.setRazonSocial(context.formParam("razon-social"));
        colaboradorJuridico.setTipoPersona(TipoPersona.JURIDICA);
        colaboradorJuridico.setTipoJuridiccion(TipoJuridiccion.EMPRESA);

        Direccion direccion = new Direccion();
        Calle calle = new Calle();
        direccion.setAltura(Integer.valueOf(context.formParam("altura")));
        direccion.setPiso(Integer.valueOf(context.formParam("piso")));
        calle.setCalle(context.formParam("calle"));
        direccion.setCalle(calle);
        //colaboradorJuridico.setDireccion(direccion);
at
        repoColaborador.agregar(colaboradorJuridico);

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
