package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DonacionVianda;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDonacionVianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import ar.edu.utn.frba.dds.utils.TipoDocumento;
import io.javalin.http.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.TipoColaboracion.DONACION_VIANDAS;

public class DonacionViandaController  extends BaseController implements ICrudViewsHandler {


    RepoDonacionVianda repositorioDonacionVianda = RepoDonacionVianda.INSTANCE;

//    private RepoDonacionVianda repositorioDonacionVianda;
//
//    public DonacionViandaController(RepoDonacionVianda repositorioDonacionVianda) {
//        this.repositorioDonacionVianda = repositorioDonacionVianda;
//    }

    @Override
    public void index(Context context) {

        Map<String, Object> model = new HashMap<>();

        try{
            verificarHumano(context, model);
        }
        catch (Exception e){
            context.redirect("/iniciarSesion");
        }




//        RepoViandas repositorioViandas = RepoViandas.INSTANCE;
        List<DonacionVianda> donacionesViandas = repositorioDonacionVianda.buscarTodos();



        List<Vianda> viandas = donacionesViandas.stream()
                .map(DonacionVianda::getVianda)
                .filter(Objects::nonNull)
                .toList();

        model.put("title", "Donaciones de Viandas");
        model.put("viandas", viandas);

        context.render("donaciones/donacionViandas.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws ParseException {
        DonacionVianda donacionVianda = new DonacionVianda();
        Vianda nuevaVianda = new Vianda();
        RepoViandas repoViandas = new RepoViandas();


//        nuevaVianda.setId(1L);
        nuevaVianda.setComida(context.formParam("comida"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        nuevaVianda.setFechaCaducidad(formatter.parse(context.formParam("fechaCaducidad")));
        nuevaVianda.setCalorias(Double.valueOf(Objects.requireNonNull(context.formParam("calorias"))));
        nuevaVianda.setPeso(Double.valueOf(context.formParam("peso")));
        nuevaVianda.setFechaDonacion(new Date());

        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNombre("Juan");
        colaborador1.setApellido("Alcachofa");
        colaborador1.setTipoDocumento(TipoDocumento.DNI);
        colaborador1.setNumeroDocumento(32458652);
        colaborador1.setTipoPersona(TipoPersona.HUMANA);

        RepoColaborador.INSTANCE.agregar(colaborador1);
        nuevaVianda.setColaborador(colaborador1);
        nuevaVianda.setFueEntregada(false);
        nuevaVianda.setHeladera(null);
        repoViandas.agregar(nuevaVianda);






        donacionVianda.setVianda(nuevaVianda);
        donacionVianda.setFechaColaboracion(new Date());
        donacionVianda.setTipoColaboracion(DONACION_VIANDAS);



        System.out.println("DonacionViandaController.save123");

        RepoDonacionVianda.INSTANCE.agregar(donacionVianda);

        System.out.println("DonacionViandaController.funciono");

        //this.repositorioViandas.guardar(nuevaVianda);
        //O BIEN LANZO UNA PANTALLA DE EXITO
        //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
        context.redirect("/donarViandas");

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
