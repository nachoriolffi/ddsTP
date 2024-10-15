package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;

import java.util.*;

public class OfertaController extends BaseController implements ICrudViewsHandler {

    RepoOferta repositorioOferta = RepoOferta.INSTANCE;

    @Override
    public void index(Context ctx) {

        Map<String, Object> model = new HashMap<>();


        Usuario usuario = verificarJuridicoOHumano(ctx, model);

        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        List<Oferta> ofertas = repositorioOferta.buscarTodos();
        colaborador.puntosActualesDisponibles();

        model.put("title", "Tienda Productos/Servicios");
        model.put("ofertas", ofertas);
        model.put("PuntosTotales", colaborador.puntosActualesDisponibles());
        model.put("rubros", Rubro.values());
        ctx.render("ofertas/ofertas.hbs", model);
    }

    public void canjear(Context context) {
        Long idUsuario = context.sessionAttribute("usuario_id");
        Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(Long.valueOf(idUsuario));
        Long idOferta = Long.valueOf(context.formParam("idProducto"));
        Long puntosProductos = Long.valueOf(context.formParam("puntosNecesarios"));
        Oferta oferta = repositorioOferta.buscar(Long.valueOf(idOferta));
        if (oferta.getStockInicial() > 0 && colaborador.puntosActualesDisponibles() >= Double.valueOf(puntosProductos)) {
            colaborador.agregarOfertasCanjeadas(oferta);
            repositorioOferta.modificar(oferta);
            RepoColaborador.INSTANCE.modificar(colaborador);
        }
    }

    @Override
    public void save(Context context) {
        Oferta oferta = new Oferta();

        UploadedFile file = context.uploadedFile("imagenProducto");
        if (file != null) {
            String contentType = file.contentType();
            if ("image/jpg".equals(contentType) || "image/jpeg".equals(contentType) || "image/png".equals(contentType)) {
                FileUtil.streamToFile(file.content(), "src/main/resources/public/imagenes/" + file.filename()); // ESTO NO SE TOCA
                oferta.setImagen("/imagenes/" + file.filename());
            } else {
                context.status(400).result("Invalid file type. Only JPG and PNG are allowed.");
                return;
            }
        } else {
            context.status(400).result("No file uploaded.");
            return;
        }
        oferta.setNombre(context.formParam("nombreProducto"));

        String puntosNecesariosStr = context.formParam("puntosNecesarios");
        if (puntosNecesariosStr != null && !puntosNecesariosStr.isEmpty()) {
            try {
                int puntosNecesarios = Integer.parseInt(puntosNecesariosStr);
                oferta.setPuntosNecesarios(puntosNecesarios);
            } catch (NumberFormatException e) {
                context.status(400).result("Invalid puntosNecesarios value. It must be an integer.");
                return;
            }
        } else {
            context.status(400).result("puntosNecesarios is required.");
            return;
        }
        oferta.setFechaInicio(new Date());
        oferta.setFechaFin(new Date());
        String stockInicialStr = context.formParam("stockInicial");
        int stockInicial = Integer.parseInt(stockInicialStr);
        oferta.setStockInicial(stockInicial);
        oferta.setStockUsado(0);
        System.out.println(context.formParam("tipoProducto"));
        Rubro rubro = Rubro.fromDescripcion(context.formParam("tipoProducto"));
        oferta.setRubro(rubro);
        repositorioOferta.agregar(oferta);

        context.redirect("/canjeProductos");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

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
