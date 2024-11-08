package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.OfertaDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import ar.edu.utn.frba.dds.services.OfertaService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OfertaController extends BaseController implements ICrudViewsHandler {

    RepoOferta repositorioOferta = RepoOferta.INSTANCE;
    OfertaService ofertaService = new OfertaService();

    @Override
    public void index(Context ctx) {

        Map<String, Object> model = new HashMap<>();
        try {
            Usuario usuario = verificarSesion(ctx, model);
            Colaborador colaborador;
            List<Oferta> ofertas;
            switch (usuario.getRol()) {
                case ADMIN:
                    verificarAdmin(ctx, model);
                    model.put("darDeBajaOferta",true);
                    break;
                case COLABORADOR_HUMANO:
                    verificarHumano(ctx, model);
                    colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
                    Double puntos = colaborador.puntosActualesDisponibles();
                    model.put("PuntosTotales", puntos);
                    break;
                case COLABORADOR_JURIDICO:
                    verificarJuridico(ctx, model);
                    RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
                    model.put("rubros", Rubro.values());
                    break;
            }
            ofertas = repositorioOferta.buscarTodos();
            model.put("ofertas", ofertas);
            model.put("title", "Tienda Productos/Servicios");
            ctx.render("ofertas/ofertas.hbs", model);
        } catch (Exception e) {
            ctx.redirect("/iniciarSesion");
        }
    }

    public void canjear(Context context) {

        Long idUsuario = context.sessionAttribute("usuario_id");
        OfertaDTO ofertaDTO = new OfertaDTO();
        ofertaDTO.setId(Long.valueOf(context.formParam("idProducto")));
        ofertaDTO.setPuntos(Long.valueOf(context.formParam("puntosNecesarios")));
        ofertaService.canjearOferta(idUsuario,ofertaDTO);
        context.redirect("/canjeProductos");
    }

    @Override
    public void save(Context context) {
        Oferta oferta = new Oferta();
        //Usuario usuario = verificarSesion(ctx, model);
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
        //Colaborador colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
        //colaborador.agregarOferta(oferta);
        context.redirect("/canjeProductos");
    }


    public void verMisOfertas(Context context) {
    }

    @Override
    public void delete(Context context) {

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

}
