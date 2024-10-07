package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import ar.edu.utn.frba.dds.models.entities.contacto.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class RegistroJuridicoController extends BaseController implements ICrudViewsHandler {
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

        String razonSocial = context.formParam("tipo-razon-social").toUpperCase();
        colaboradorJuridico.setTipoJuridiccion(TipoJuridiccion.valueOf(razonSocial));

        Direccion direccion = new Direccion();

        String altura = context.formParam("altura");
        String piso = context.formParam("piso");
        String calle = context.formParam("calle");

        if(piso != null && !piso.isEmpty() || altura != null && !altura.isEmpty() || calle != null && !calle.isEmpty()){
            if(piso != null && !piso.isEmpty()){
                direccion.setPiso(Integer.valueOf(context.formParam("piso")));
            }
            if(altura != null && !altura.isEmpty()){
                direccion.setAltura(Integer.valueOf(context.formParam("altura")));
            }
            if(calle != null && !calle.isEmpty()){
                direccion.setCalle(new Calle(calle));
            }
            colaboradorJuridico.setDireccion(direccion);
        }

        String telefono = context.formParam("telefono");
        String correo = context.formParam("correo");
        List<Contacto> contacto = new ArrayList<>();

        if (telefono != null && !telefono.isEmpty()) {
            Contacto contactoTelefono = new Contacto(TipoContacto.TELEGRAM, telefono);
            contacto.add(contactoTelefono);
        }
        if (correo != null && !correo.isEmpty()) {
            Contacto contactoCorreo = new Contacto(TipoContacto.MAIL, correo);
            contacto.add(contactoCorreo);
        }
        colaboradorJuridico.setContacto(contacto);


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
