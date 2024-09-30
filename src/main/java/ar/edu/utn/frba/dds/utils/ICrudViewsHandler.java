package ar.edu.utn.frba.dds.utils;

import io.javalin.http.Context;

import java.text.ParseException;

public interface ICrudViewsHandler {
    void index(Context context);
    void show(Context context);
    void create(Context context);
    void save(Context context) throws ParseException;
    void edit(Context context);
    void update(Context context);
    void delete(Context context);


}
