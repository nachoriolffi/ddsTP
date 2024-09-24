package ar.edu.utn.frba.dds.controllers;

import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import io.javalin.http.Context;

public class ColaboradorController extends BaseController{

    private RepoColaborador repositorioColaborador = RepoColaborador.INSTANCE;

    public ColaboradorController(RepoColaborador repositorioColaborador){
        this.repositorioColaborador = repositorioColaborador;
    }

    public void index(Context context){




        /*List<Colaborador> colaboradores = this.repositorioColaborador.buscarTodos();

        Map<String, Object> model = new HashMap<>()*/
    }







}
