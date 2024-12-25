package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.TipoPregunta;
import ar.edu.utn.frba.dds.models.entities.generadorCodigo.GeneradorDeCodigo;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class Init implements WithSimplePersistenceUnit {

    public static void iniciar() {

        /*--------------MODELOS--------------*/

        RepoModelo repoModelo = new RepoModelo();

        ModeloHeladera modeloHeladera1 = new ModeloHeladera();
        modeloHeladera1.setNombreModelo("Heladera Industrial X500");
        modeloHeladera1.setPeso(90D);
        modeloHeladera1.setCantidadMaximaDeViandas(100);
        modeloHeladera1.setTemperaturaMaxima(10D);
        modeloHeladera1.setTemperaturaMinima(-18D);
        repoModelo.agregar(modeloHeladera1);

        ModeloHeladera modeloHeladera2 = new ModeloHeladera();
        modeloHeladera2.setNombreModelo("Heladera Comercial C300");
        modeloHeladera2.setPeso(80D);
        modeloHeladera2.setCantidadMaximaDeViandas(50);
        modeloHeladera2.setTemperaturaMaxima(7D);
        modeloHeladera2.setTemperaturaMinima(-12D);
        repoModelo.agregar(modeloHeladera2);

        ModeloHeladera modeloHeladera3 = new ModeloHeladera();
        modeloHeladera3.setNombreModelo("Heladera de Oficina MiniCool");
        modeloHeladera3.setPeso(40D);
        modeloHeladera3.setCantidadMaximaDeViandas(25);
        modeloHeladera3.setTemperaturaMaxima(8D);
        modeloHeladera3.setTemperaturaMinima(-5D);
        repoModelo.agregar(modeloHeladera3);

        /*--------------USUARIOS--------------*/

//        RepoUsuario repoUsuario = RepoUsuario.INSTANCE;
//
//        Usuario usuarioAdmin = new Usuario();
//        usuarioAdmin.setNombre("Ignacio Joaquin");
//        usuarioAdmin.setApellido("Riolffi");
//        usuarioAdmin.setContrasenia("DDS25_2024");
//        usuarioAdmin.setCuentaEliminada(false);
//        usuarioAdmin.setCorreoElectronico("iriolffi@gmail.com");
//        usuarioAdmin.setRol(TipoRol.ADMIN);
//        repoUsuario.agregar(usuarioAdmin);

        /*--------------TARJETAS--------------*/

        RepoTarjeta repoTarjeta = new RepoTarjeta();
        for (int i = 0; i < 50; i++) {
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setCodigo(GeneradorDeCodigo.getInstance().generarCodigoUnico());
            repoTarjeta.agregar(tarjeta);
        }

        /*--------------CUESTIONARIOS--------------*/

        RepoPregunta repoPregunta = RepoPregunta.INSTANCE;
        RepoCuestionario repoCuestionario = RepoCuestionario.INSTANCE;
        RepoOpcion repoOpcion = RepoOpcion.INSTANCE;

        Cuestionario cuestionario1 = new Cuestionario();
        Pregunta nombrePregunta = new Pregunta();
        nombrePregunta.setNombre("nombre");
        nombrePregunta.setDescripcionPregunta("Ingrese su nombre");
        nombrePregunta.setTipoPregunta(TipoPregunta.STRING);
        Pregunta apellidoPregunta = new Pregunta();
        apellidoPregunta.setNombre("apellido");
        apellidoPregunta.setDescripcionPregunta("Ingrese su apellido");
        apellidoPregunta.setTipoPregunta(TipoPregunta.STRING);
        RepoPregunta.INSTANCE.agregar(nombrePregunta);
        RepoPregunta.INSTANCE.agregar(apellidoPregunta);
        cuestionario1.agregarPregunta(nombrePregunta);
        cuestionario1.agregarPregunta(apellidoPregunta);
        Pregunta fechaPregunta = new Pregunta();
        fechaPregunta.setNombre("fechaDeNacimiento");
        fechaPregunta.setDescripcionPregunta("Ingrese su fecha de nacimiento");
        fechaPregunta.setTipoPregunta(TipoPregunta.FECHA);
        RepoPregunta.INSTANCE.agregar(fechaPregunta);
        cuestionario1.agregarPregunta(fechaPregunta);
        RepoCuestionario.INSTANCE.agregar(cuestionario1);

        RubroColaborador rubroGastronomia = new RubroColaborador();
        rubroGastronomia.setNombre("GASTRONOMIA");

        RubroColaborador rubroEducacion = new RubroColaborador();
        rubroEducacion.setNombre("EDUCACION");

        RubroColaborador rubroSalud = new RubroColaborador();
        rubroSalud.setNombre("SALUD");

        RepoRubroColaborador.INSTANCE.agregar(rubroGastronomia);
        RepoRubroColaborador.INSTANCE.agregar(rubroEducacion);
        RepoRubroColaborador.INSTANCE.agregar(rubroSalud);

        //JURIDICO
        Cuestionario cuestionarioJuridico2 = new Cuestionario();
        cuestionarioJuridico2.setNombreCuestionario("Cuestionario Juridico");
        cuestionarioJuridico2.setDescripcion("Este es el cuestionario para registrar colaboradores juridicos");

        Pregunta preguntarRazonSocial = new Pregunta();
        preguntarRazonSocial.setNombre("razonSocial");
        preguntarRazonSocial.setDescripcionPregunta("Ingrese su Razon Social");
        preguntarRazonSocial.setEsObligatoria(true);
        preguntarRazonSocial.setTipoPregunta(TipoPregunta.STRING);
        RepoPregunta.INSTANCE.agregar(preguntarRazonSocial);

        Pregunta preguntaTipoRazonSocial = new Pregunta();
        preguntaTipoRazonSocial.setTipoPregunta(TipoPregunta.MULTIPLECHOICE);
        preguntaTipoRazonSocial.setNombre("tipoJuridiccion");
        preguntaTipoRazonSocial.setDescripcionPregunta("Ingrese su razon social");
        preguntaTipoRazonSocial.setEsObligatoria(true);
        RepoPregunta.INSTANCE.agregar(preguntaTipoRazonSocial);

        Opcion opcionGubernamental2 = new Opcion("GUBERNAMENTAL");
        Opcion opcionONG2 = new Opcion("ONG");
        Opcion opcionEmpresa2 = new Opcion("EMPRESA");
        Opcion opcionInstitucion2 = new Opcion("INSTITUCION");

        opcionGubernamental2.setPregunta(preguntaTipoRazonSocial);
        opcionONG2.setPregunta(preguntaTipoRazonSocial);
        opcionEmpresa2.setPregunta(preguntaTipoRazonSocial);
        opcionInstitucion2.setPregunta(preguntaTipoRazonSocial);

        preguntaTipoRazonSocial.agregarOpcion(opcionGubernamental2);
        preguntaTipoRazonSocial.agregarOpcion(opcionONG2);
        preguntaTipoRazonSocial.agregarOpcion(opcionEmpresa2);
        preguntaTipoRazonSocial.agregarOpcion(opcionInstitucion2);

        RepoOpcion.INSTANCE.agregar(opcionGubernamental2);
        RepoOpcion.INSTANCE.agregar(opcionONG2);
        RepoOpcion.INSTANCE.agregar(opcionEmpresa2);
        RepoOpcion.INSTANCE.agregar(opcionInstitucion2);

        Pregunta preguntaRubro = new Pregunta();
        preguntaRubro.setNombre("rubroColaborador");
        preguntaRubro.setDescripcionPregunta("Rubro de Persona Juridica");
        preguntaRubro.setTipoPregunta(TipoPregunta.MULTIPLECHOICE);
        preguntaRubro.setEsObligatoria(true);
        RepoPregunta.INSTANCE.agregar(preguntaRubro);

        List<RubroColaborador> rubrosParaOpcion = RepoRubroColaborador.INSTANCE.buscarTodos();

        agregarRubrosAPreguntaComoOpciones(preguntaRubro, rubrosParaOpcion);
        cuestionarioJuridico2.agregarPregunta(preguntarRazonSocial);
        cuestionarioJuridico2.agregarPregunta(preguntaTipoRazonSocial);
        cuestionarioJuridico2.agregarPregunta(preguntaRubro);

        RepoCuestionario.INSTANCE.agregar(cuestionarioJuridico2);
    }

    public static void agregarRubrosAPreguntaComoOpciones(Pregunta pregunta, List<RubroColaborador> rubros) {
        for (RubroColaborador rubro : rubros) {
            Opcion opcion = new Opcion(rubro.getNombre());
            opcion.setPregunta(pregunta);
            pregunta.agregarOpcion(opcion);
            RepoOpcion.INSTANCE.agregar(opcion);
        }
    }
}