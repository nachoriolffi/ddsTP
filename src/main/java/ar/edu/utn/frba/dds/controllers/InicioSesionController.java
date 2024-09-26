package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.utils.ICrudViewsHandler;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.Map;

public class InicioSesionController implements ICrudViewsHandler {
    @Override
    public void index(Context ctx) {

        Map<String, Object> model = new HashMap<>();
        model.put("title", "inicioSesion");
        ctx.render("logs/inicioSesion.hbs", model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

//        String correoElectronico = context.formParam("correoElectronico");
//        String clave = context.formParam(("clave"));
//
////        ctx ->{
////            String correoElectronico = ctx.formParam("correoElectronico");
////            String clave = ctx.formParam(("clave"));
////        Producto nuevoProducto = new Producto();
////
////        nuevoProducto.setNombre(context.formParam("nombre"));
////        nuevoProducto.setPrecio(Float.valueOf(context.formParam("precio")));
////
////        this.repositorioDeProductos.guardar(nuevoProducto);
//        //O BIEN LANZO UNA PANTALLA DE EXITO
//        //O BIEN REDIRECCIONO AL USER A LA PANTALLA DE LISTADO DE PRODUCTOS
//        context.redirect("/productos");

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