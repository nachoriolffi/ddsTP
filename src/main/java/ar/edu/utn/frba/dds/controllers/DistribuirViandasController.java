package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.FormaDeColaboracion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.MotivoDistribucion;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoDistribucionVianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import java.util.stream.Collectors;

import java.sql.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistribuirViandasController extends BaseController implements ICrudViewsHandler {
    @Override
    public void index(Context context) {


        Map<String, Object> model = new HashMap<>();
        Usuario usuario = verificarSesion(context, model);
        verificarHumano(context, model);

        Colaborador colaboradorHumano = RepoColaborador.INSTANCE.buscar(usuario.getId());
        List<DistribucionVianda> distribuciones = colaboradorHumano.getColaboracionesRealizadas().stream()
                .filter(c -> c instanceof DistribucionVianda)
                .map(c -> (DistribucionVianda) c)
                .collect(Collectors.toList());


        //Estoy buscando todas las distribuciones de viandas para probar, luego voy a buscar solo
        //Las del usuario que inició la sesión

        model.put("distribuciones", distribuciones);
        model.put("title", "Distribuir Viandas");



        context.render("donaciones/distribuirViandas.hbs",model);

//        Usuario usuario = usuarioLogueado(context);
//        if (usuario == null) {
//            context.redirect("/inicioSesion");
//        } /*else if (!usuario.getRol().equals(TipoRol.COLABORADOR_HUMANO)){
//            context.redirect("/error403");
//        } */
//        else{
//            Colaborador colaboradorHumano = RepoColaborador.INSTANCE.buscar(usuario.getId());
//            List<DistribucionVianda> distribuciones = colaboradorHumano.getColaboracionesRealizadas().stream()
//                    .filter(c -> c instanceof DistribucionVianda)
//                    .map(c -> (DistribucionVianda) c)
//                    .collect(Collectors.toList());
//
//            Map<String, Object> model = new HashMap<>();
//            model.put("distribuciones", distribuciones);
//            model.put("title", "Distribuir Viandas");
//
//            context.render("donaciones/distribuirViandas.hbs",model);
//        }
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws ParseException {
        System.out.println("heladera-origen: " + context.formParam("heladera-origen"));
        System.out.println("heladera-destino: " + context.formParam("heladera-destino"));
        System.out.println("motivo: " + context.formParam("motivo"));
        System.out.println("cantidad-viandas: " + context.formParam("cantidad-viandas"));
        System.out.println("fecha-distribucion: " + context.formParam("fecha-distribucion"));

        //Esto es para la persona humana, así que hay que chequear que tenga ese rol
        //también se podría asumir que cada heladera tiene un nombre unico, entonces la recuperamos por el nombre
        //en este caso solo se registra la contribución, no se hace la disribución

        //fechaDistribucion -> es la fecha en la que se van a distribuir las viandas
        //fechaColaboracion -> es la fecha en la que colaborador completo el formulario

        Long heladeraOrigen_id = Long.valueOf(context.formParam("heladera-origen"));
        Long heladeraDestino_id = Long.valueOf(context.formParam("heladera-destino"));

        Heladera heladeraOrigen =  RepoHeladeras.INSTANCE.buscar(heladeraOrigen_id);
        Heladera heladeraDestino =  RepoHeladeras.INSTANCE.buscar(heladeraDestino_id);

        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setHeladeraOrigen(heladeraOrigen);
        distribucionVianda.setHeladeraDestino(heladeraDestino);

        MotivoDistribucion motivo = MotivoDistribucion.valueOf(context.formParam("motivo"));// revisar

        distribucionVianda.setMotivo(motivo);

        distribucionVianda.setCantidadViandas(Integer.valueOf(context.formParam("cantidad-viandas")));

        distribucionVianda.setFechaDistribucion(Date.valueOf(context.formParam("fecha-distribucion")));//probar
        distribucionVianda.setFechaColaboracion(new java.util.Date());

        RepoDistribucionVianda.INSTANCE.agregar(distribucionVianda);
        //hay que agregarle la contribución al usuario y luego hacer un update

        Usuario usuario = usuarioLogueado(context);
        usuario.getId();
        Colaborador colaboradorHumano = RepoColaborador.INSTANCE.buscar(usuario.getId());
        colaboradorHumano.agregarColaboracionRealizada(distribucionVianda);
        RepoColaborador.INSTANCE.modificar(colaboradorHumano);

        context.redirect("distribuirViandas");
        //en base al usuario obtener el id de colaborador

        //distribucionVianda.getHeladeraOrigen().getId();
        //distribucionVianda.getCantidadViandas()

        //Lo siguiente se haría cuando el colaborador asista personalmente a realizar la distribución:
        //Habría que quitar las viandas de la heladera A segun la cantidad y agregarlas a la heladera B
        //Despues hay que hacer un update o modificar
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
