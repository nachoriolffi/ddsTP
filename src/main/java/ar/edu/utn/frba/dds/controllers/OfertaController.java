package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.dtos.OfertaDTO;
import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Oferta;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.OfertaCanje;
import ar.edu.utn.frba.dds.models.entities.intercambioPuntos.Rubro;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoOferta;
import ar.edu.utn.frba.dds.services.OfertaService;
import ar.edu.utn.frba.dds.services.UserService;
import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import io.javalin.util.FileUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class OfertaController extends BaseController implements ICrudViewsHandler {

    RepoOferta repositorioOferta = RepoOferta.INSTANCE;
    OfertaService ofertaService = new OfertaService();
    UserService userService = new UserService();

    @Override
    public void index(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        try {
            // Verificar la sesión y obtener el usuario
            System.out.println("Verificando sesión...");
            Usuario usuario = verificarSesion(ctx, model);
            System.out.println("Usuario obtenido de la sesión: " + usuario);

            // Declaración de variables
            Colaborador colaborador;
            List<Oferta> ofertas;

            // Comprobando el rol del usuario
            switch (usuario.getRol()) {
                case ADMIN:
                    System.out.println("Rol de usuario: ADMIN");
                    usuario = verificarAdmin(ctx, model);
                    model.put("darDeBajaOferta", true);
                    break;
                case COLABORADOR_HUMANO:
                    System.out.println("Rol de usuario: COLABORADOR_HUMANO");
                    usuario = verificarHumano(ctx, model);
                    colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
                    Double puntos = colaborador.puntosActualesDisponibles();
                    System.out.println("Puntos actuales disponibles para el colaborador: " + puntos);
                    model.put("PuntosTotales", puntos);
                    break;
                case COLABORADOR_JURIDICO:
                    System.out.println("Rol de usuario: COLABORADOR_JURIDICO");
                    usuario = verificarJuridico(ctx, model);
                    RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
                    model.put("rubros", Rubro.values());
                    break;
                default:
                    System.out.println("Rol desconocido: " + usuario.getRol());
                    break;
            }

            // Convertir el usuario a un DTO y agregar al modelo
            UsuarioDTO usuarioDTO = userService.obtenerUsuarioDTO(usuario);
            model.put("usuario", usuarioDTO);
            System.out.println("UsuarioDTO agregado al modelo: " + usuarioDTO);

            // Obtener las ofertas
            ofertas = repositorioOferta.buscarTodos();
            System.out.println("Cantidad de ofertas obtenidas: " + ofertas.size());

            // Filtrar ofertas con stock
            List<Oferta> ofertasConStock = new ArrayList<>();
            for (Oferta oferta : ofertas) {
                if (oferta.getStockInicial() != 0) {
                    ofertasConStock.add(oferta);
                }
            }
            System.out.println("Cantidad de ofertas con stock: " + ofertasConStock.size());

            // Agregar las ofertas al modelo
            model.put("ofertas", ofertasConStock);
            model.put("title", "Tienda Productos/Servicios");

            // Renderizar la vista
            System.out.println("Renderizando la vista de ofertas...");
            ctx.render("ofertas/ofertas.hbs", model);
        } catch (Exception e) {
            // En caso de error, redirigir a la página de inicio de sesión
            System.out.println("Error al procesar la solicitud: " + e.getMessage());
            e.printStackTrace();
            ctx.redirect("/iniciarSesion");
        }
    }


    public void canjear(Context context) {

        Long idUsuario = context.sessionAttribute("usuario_id");
        OfertaDTO ofertaDTO = new OfertaDTO();
        ofertaDTO.setId(Long.valueOf(Objects.requireNonNull(context.formParam("idProducto"))));
        ofertaDTO.setCantidadPuntos(Long.valueOf(Objects.requireNonNull(context.formParam("puntosNecesarios"))));
        ofertaService.canjearOferta(idUsuario, ofertaDTO);
        context.redirect("/canjeProductos");
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
        assert stockInicialStr != null;
        int stockInicial = Integer.parseInt(stockInicialStr);
        oferta.setStockInicial(stockInicial);
        oferta.setStockUsado(0);
        System.out.println(context.formParam("tipoProducto"));
        Rubro rubro = Rubro.fromDescripcion(context.formParam("tipoProducto"));
        oferta.setRubro(rubro);
        repositorioOferta.agregar(oferta);
        context.redirect("/canjeProductos");
    }


    public void verMisOfertas(Context ctx) {
        Map<String, Object> model = new HashMap<>();
        List<OfertaCanje> ofertasCanjeadas;
        Colaborador colaborador;
        try {
            Usuario usuario = verificarHumano(ctx, model);
            if (Objects.requireNonNull(usuario.getRol()) == TipoRol.COLABORADOR_HUMANO) {
                colaborador = RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
                Double puntos = colaborador.puntosActualesDisponibles();
                model.put("PuntosTotales", puntos);

                ofertasCanjeadas = colaborador.getOfertasCanjeadasORegistradas();
                List<OfertaDTO> misCanjes = new ArrayList<>();
                for (OfertaCanje ofertaCanje : ofertasCanjeadas) {
                    OfertaDTO ofertaDTO = new OfertaDTO();
                    ofertaDTO.setOferta(ofertaCanje.getOferta().getNombre());
                    ofertaDTO.setRubro(ofertaCanje.getOferta().getRubro().toString());
                    ofertaDTO.setCantidadPuntos(ofertaCanje.getOferta().getPuntosNecesarios().longValue());

                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    ofertaDTO.setFecha(formatter.format(ofertaCanje.getFechaCanje()));

                    misCanjes.add(ofertaDTO);
                }
                model.put("ofertasCanjeadas", misCanjes);
                model.put("title", "Ofertas Canjeadas");
                ctx.render("ofertas/ofertasCanjeadasColaborador.hbs", model);

            }

        } catch (Exception e) {
            ctx.redirect("/iniciarSesion");
        }
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
