package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.TipoPregunta;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;
import java.util.Date;

public class Init implements WithSimplePersistenceUnit {

    public static void iniciar() {

        RepoCoordenada repoCoordenada = new RepoCoordenada();
        Coordenada coordenada = new Coordenada(-34.598630, -58.419962);
        Direccion direccion = new Direccion();
        direccion.setAltura(951);
        //repoCoordenada.agregar(coordenada);

        RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
        Heladera heladera1 = new Heladera();
        heladera1.setNombre("UTN MEDRANO");
        heladera1.setCoordenada(coordenada);
        //heladera1.setDireccion(direccion);
        heladera1.setFechaPuestaFunc(new Date());
        heladera1.setEstaActiva(Boolean.TRUE);

        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNombre("Pepito");
        colaborador1.setApellido("Perez");
        colaborador1.setTipoDocumento(TipoDocumento.DNI);
        colaborador1.setNumeroDocumento(32458652);
        colaborador1.setTipoPersona(TipoPersona.HUMANA);

        //RepoColaborador.INSTANCE.agregar(colaborador1);
        Vianda vianda = new Vianda();
        vianda.setComida("qweqwe");
        vianda.setFechaCaducidad(new Date(2021, 10, 24));
        vianda.setFechaDonacion(new Date());

       // RepoColaborador.INSTANCE.agregar(colaborador1);
        vianda.setColaborador(colaborador1);
        vianda.setCalorias(500.0);
        vianda.setPeso(200.0);
        vianda.setFueEntregada(false);
        vianda.setHeladera(heladera1);

        //RepoViandas.INSTANCE.agregar(vianda);

        //repoOferta.agregar(oferta);

        Heladera heladera2 = new Heladera();
        heladera2.setNombre("UTN CAMPUS");
        heladera2.setCoordenada(coordenada);
        heladera2.setFechaPuestaFunc(new Date());
        heladera2.setEstaActiva(Boolean.TRUE);
        //heladera2.setModelo(modelo1);

        //repoHeladeras.agregar(heladera2);

        //Usuario usuario = new Usuario();
        //usuario.setNombre("Ignacio Riolffi");
        //usuario.setRol(TipoRol.ADMIN);
        //usuario.setContrasenia("1234");
        //usuario.setCorreoElectronico("nacho@gmail.com");
        RepoUsuario repoUsuario = RepoUsuario.INSTANCE;
        //repoUsuario.agregar(usuario);

        Cuestionario cuestionario = new Cuestionario();

// Crear preguntas de tipo STRING
        Pregunta nombrePregunta = new Pregunta();
        nombrePregunta.setNombre("nombre");
        nombrePregunta.setDescripcionPregunta("Ingrese su nombre");
        nombrePregunta.setTipoPregunta(TipoPregunta.STRING);

        Pregunta apellidoPregunta = new Pregunta();
        apellidoPregunta.setNombre("apellido");
        apellidoPregunta.setDescripcionPregunta("Ingrese su apellido");
        apellidoPregunta.setTipoPregunta(TipoPregunta.STRING);

// Persistir preguntas de tipo STRING
        //RepoPregunta.INSTANCE.agregar(nombrePregunta);
        //RepoPregunta.INSTANCE.agregar(apellidoPregunta);

// Agregar preguntas de tipo STRING al cuestionario
        //cuestionario.agregarPregunta(nombrePregunta);
        //cuestionario.agregarPregunta(apellidoPregunta);

// Crear pregunta de opción múltiple
        Pregunta multipleChoicePregunta = new Pregunta();
        multipleChoicePregunta.setNombre("Pregunta de opción múltiple");
        multipleChoicePregunta.setTipoPregunta(TipoPregunta.MULTIPLECHOICE);

// Persistir pregunta de opción múltiple antes de agregar opciones
       // RepoPregunta.INSTANCE.agregar(multipleChoicePregunta);

// Crear opciones para la pregunta de opción múltiple
        Opcion opcion1 = new Opcion("Opción 1");
        Opcion opcion2 = new Opcion("Opción 2");
        Opcion opcion3 = new Opcion("Opción 3");

// Asignar la pregunta a cada opción
        opcion1.setPregunta(multipleChoicePregunta);
        opcion2.setPregunta(multipleChoicePregunta);
        opcion3.setPregunta(multipleChoicePregunta);

// Agregar opciones a la pregunta de opción múltiple
        multipleChoicePregunta.getOpciones().add(opcion1);
        multipleChoicePregunta.getOpciones().add(opcion2);
        multipleChoicePregunta.getOpciones().add(opcion3);

// Persistir opciones
        //RepoOpcion.INSTANCE.agregar(opcion1);
        //RepoOpcion.INSTANCE.agregar(opcion2);
        //RepoOpcion.INSTANCE.agregar(opcion3);

// Agregar pregunta de opción múltiple al cuestionario
        //cuestionario.agregarPregunta(multipleChoicePregunta);
// Crear pregunta de tipo fecha
        Pregunta fechaPregunta = new Pregunta();
        fechaPregunta.setNombre("fechaDeNacimiento");
        fechaPregunta.setDescripcionPregunta("Ingrese su fecha de nacimiento");
        fechaPregunta.setTipoPregunta(TipoPregunta.FECHA);

// Persistir pregunta de tipo fecha
        //RepoPregunta.INSTANCE.agregar(fechaPregunta);

// Agregar pregunta de tipo fecha al cuestionario
        //cuestionario.agregarPregunta(fechaPregunta);
// Persistir el cuestionario
        //RepoCuestionario.INSTANCE.agregar(cuestionario);

        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico("iriolffi@gmail.com");
        usuario.setRol(TipoRol.ADMIN);
        usuario.setContrasenia("1234");
        usuario.setCuentaEliminada(false);
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre("Ignacio Joaquin");
        colaborador.setApellido("Riolffi");
        usuario.setNombre(colaborador.getNombre() + " " + colaborador.getApellido());
        //repoUsuario = RepoUsuario.INSTANCE;
        //repoUsuario.agregar(usuario);
        colaborador.setTipoPersona(TipoPersona.HUMANA);
        colaborador.setUsuario(usuario);
        RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
        //repoColaborador.agregar(colaborador);
    }


}
