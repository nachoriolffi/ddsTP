package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.*;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.CorreoElectronico;
import ar.edu.utn.frba.dds.models.entities.contacto.correo.MedioDeComunicacion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.TipoPregunta;
import ar.edu.utn.frba.dds.models.entities.exportadorPDF.DocumentoFallasHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroApertura;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.Incidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoAlerta;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.TipoIncidente;
import ar.edu.utn.frba.dds.models.entities.heladera.alerta.registro.RegistroTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Init implements WithSimplePersistenceUnit {

    public static void iniciar() {

/*
        RepoCoordenada repoCoordenada = new RepoCoordenada();
        RepoModelo repoModelo = new RepoModelo();
        RepoHeladeras repoHeladera = new RepoHeladeras();
        RepoViandas repoViandas = new RepoViandas();
        RepoDonacionVianda repoDonacionVianda = new RepoDonacionVianda();
        Coordenada coordenada = new Coordenada(-34.598630, -58.419962);
        repoCoordenada.agregar(coordenada);

        ModeloHeladera modeloPDF = new ModeloHeladera();
        modeloPDF.setTemperaturaMinima(3.0);
        modeloPDF.setTemperaturaMaxima(15.0);
        modeloPDF.setPeso(200.0);
        modeloPDF.setCantidadMaximaDeViandas(500);
        modeloPDF.setNombreModelo("Modelo PDF");
        repoModelo.agregar(modeloPDF);

        Heladera heladeraPDF = new Heladera();
        heladeraPDF.setModelo(modeloPDF);
        heladeraPDF.setEstaActiva(true);
        heladeraPDF.setViandasDisponibles(0);
        heladeraPDF.setFechaPuestaFunc(new Date());
         heladeraPDF.setNombre("Heladera PDF");
        heladeraPDF.setCoordenada(coordenada);
        repoHeladera.agregar(heladeraPDF);

        Vianda viandaPDF = new Vianda();
        viandaPDF.setFueEntregada(true);
        viandaPDF.setCalorias(500.0);
        viandaPDF.setPeso(500.0);
        viandaPDF.setFechaDonacion(new Date());
        viandaPDF.setFechaCaducidad(new Date());
        viandaPDF.setHeladera(heladeraPDF);
        viandaPDF.setComida("Comida");


        DonacionVianda donacionVianda = new DonacionVianda();
        donacionVianda.setVianda(viandaPDF);
        donacionVianda.setFechaColaboracion(new Date());
        donacionVianda.setTipoColaboracion(TipoColaboracion.DONACION_VIANDAS);


        Colaborador colaboradorPDF=new Colaborador();
        viandaPDF.setColaborador(colaboradorPDF);
        repoViandas.agregar(viandaPDF);
        donacionVianda.setColaborador(colaboradorPDF);
        repoDonacionVianda.agregar(donacionVianda);

        colaboradorPDF.setNombre("Ignacio Joaquin");
        colaboradorPDF.setApellido("Riolffi");
        colaboradorPDF.setTipoPersona(TipoPersona.HUMANA);
        colaboradorPDF.agregarColaboracionRealizada(donacionVianda);

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

        RepoColaborador.INSTANCE.agregar(colaborador1);

        Incidente incidente1 = new Incidente();
        incidente1.setDescripcion("Incidente de prueba 1");
        incidente1.setPathFoto(null);
        incidente1.setTipoIncidente(TipoIncidente.FALLA);
        incidente1.setTipoAlerta(null);
        incidente1.setColaborador(colaborador1);
        incidente1.setHeladera(heladera1);
        RepoIncidente.INSTANCE.agregar(incidente1);

        repoHeladeras.agregar(heladera1);



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
        Usuario usuario2 = new Usuario();
        usuario2.setCorreoElectronico("nacho2@gmail.com");
        usuario2.setRol(TipoRol.COLABORADOR_HUMANO);
        usuario2.setContrasenia("1234");

        Colaborador colaborador = new Colaborador();
        colaborador.setNombre("Ignacio Joaquin");
        colaborador.setApellido("Riolffi");
        usuario.setNombre(colaborador.getNombre() + " " + colaborador.getApellido());
        //repoUsuario = RepoUsuario.INSTANCE;
        //repoUsuario.agregar(usuario);
        colaborador.setNombre("Nacho");
        colaborador.setApellido("Nachito");
        usuario2.setNombre(colaborador.getNombre() + " " + colaborador.getApellido());
        repoUsuario = RepoUsuario.INSTANCE;
        repoUsuario.agregar(usuario2);
        colaborador.setTipoPersona(TipoPersona.HUMANA);
        colaborador.setUsuario(usuario);
        RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
        repoColaborador.agregar(colaborador);

        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setColaboradorAsignador(colaborador);
        Tarjeta tarjeta2 = new Tarjeta();
        tarjeta2.setColaboradorAsignador(colaborador);

        RepoTarjeta.INSTANCE.agregar(tarjeta);
        RepoTarjeta.INSTANCE.agregar(tarjeta2);
*/

        //----------------------------------------------

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

        /*--------------HELADERAS--------------*/

        RepoHeladeras repoHeladera = RepoHeladeras.INSTANCE;


        /*--------------USUARIOS--------------*/

        //repoHeladeras.agregar(heladera2);
/*
        Usuario usuario = new Usuario();
        usuario.setNombre("Ignacio Riolffi");
        usuario.setRol(TipoRol.ADMIN);
        usuario.setContrasenia("1234");
        usuario.setCorreoElectronico("nacho@gmail.com");
*/
        RepoUsuario repoUsuario = RepoUsuario.INSTANCE;

        // ADMIN
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setNombre("Ignacio Joaquin Riolffi");
        usuarioAdmin.setContrasenia("1234");
        usuarioAdmin.setCuentaEliminada(false);
        usuarioAdmin.setCorreoElectronico("iriolffi@gmail.com");
        usuarioAdmin.setRol(TipoRol.ADMIN);
        //repoUsuario.agregar(usuarioAdmin);

        /*--------------CUESTIONARIOS--------------*/
        //repoUsuario.agregar(usuario);
        Cuestionario cuestionario = new Cuestionario();

        RepoPregunta repoPregunta = RepoPregunta.INSTANCE;
        RepoCuestionario repoCuestionario = RepoCuestionario.INSTANCE;
        RepoOpcion repoOpcion = RepoOpcion.INSTANCE;

        // JURIDICO

        Cuestionario cuestionarioJuridico = new Cuestionario();
        cuestionarioJuridico.setDescripcion("Cuestionario Juridico");
        cuestionarioJuridico.setNombreCuestionario("Cuestionario Juridico");

        Pregunta preguntaRazonSocial = new Pregunta();
        preguntaRazonSocial.setNombre("Razon Social");
        preguntaRazonSocial.setTipoPregunta(TipoPregunta.STRING);
        preguntaRazonSocial.setDescripcionPregunta("Ingrese Su Razon Social");
        preguntaRazonSocial.setEsObligatoria(true);

        Pregunta preguntaTipoRazon = new Pregunta();
        preguntaTipoRazon.setNombre("Tipo Razon Social");
        preguntaTipoRazon.setTipoPregunta(TipoPregunta.MULTIPLECHOICE);
        preguntaTipoRazon.setDescripcionPregunta("Indique Su Tipo De Razon Social");
        preguntaTipoRazon.setEsObligatoria(true);

        repoPregunta.agregar(preguntaRazonSocial);
        repoPregunta.agregar(preguntaTipoRazon);

        Opcion opcionGubernamental = new Opcion("Gubernamental");
        Opcion opcionONG = new Opcion("ONG");
        Opcion opcionEmpresa = new Opcion("Empresa");
        Opcion opcionInstitucion = new Opcion("Institucion");

        opcionGubernamental.setPregunta(preguntaRazonSocial);
        opcionONG.setPregunta(preguntaTipoRazon);
        opcionEmpresa.setPregunta(preguntaTipoRazon);
        opcionInstitucion.setPregunta(preguntaRazonSocial);

        preguntaTipoRazon.getOpciones().add(opcionGubernamental);
        preguntaTipoRazon.getOpciones().add(opcionONG);
        preguntaTipoRazon.getOpciones().add(opcionEmpresa);
        preguntaTipoRazon.getOpciones().add(opcionInstitucion);

        repoOpcion.agregar(opcionGubernamental);
        repoOpcion.agregar(opcionONG);
        repoOpcion.agregar(opcionEmpresa);
        repoOpcion.agregar(opcionInstitucion);

        Pregunta preguntaRubroJuridico = new Pregunta();
        preguntaRubroJuridico.setNombre("Rubro");
        preguntaRubroJuridico.setDescripcionPregunta("Rubro de Persona Juridica");
        preguntaRubroJuridico.setTipoPregunta(TipoPregunta.STRING);
        preguntaRubroJuridico.setEsObligatoria(true);

        cuestionarioJuridico.agregarPregunta(preguntaRubroJuridico);
        cuestionarioJuridico.agregarPregunta(preguntaRazonSocial);
        cuestionarioJuridico.agregarPregunta(preguntaTipoRazon);

        //repoCuestionario.agregar(cuestionarioJuridico);

        //HUMANO


        //Cuestionario cuestionario = new Cuestionario();
        //Pregunta nombrePregunta = new Pregunta();
        //nombrePregunta.setNombre("nombre");
        //nombrePregunta.setDescripcionPregunta("Ingrese su nombre");
        //nombrePregunta.setTipoPregunta(TipoPregunta.STRING);
        //Pregunta apellidoPregunta = new Pregunta();
        //apellidoPregunta.setNombre("apellido");
        //apellidoPregunta.setDescripcionPregunta("Ingrese su apellido");
        //apellidoPregunta.setTipoPregunta(TipoPregunta.STRING);
        //RepoPregunta.INSTANCE.agregar(nombrePregunta);
        //RepoPregunta.INSTANCE.agregar(apellidoPregunta);
        //cuestionario.agregarPregunta(nombrePregunta);
        //cuestionario.agregarPregunta(apellidoPregunta);
        //Pregunta multipleChoicePregunta = new Pregunta();
        //multipleChoicePregunta.setNombre("Pregunta de opción múltiple");
        //multipleChoicePregunta.setTipoPregunta(TipoPregunta.MULTIPLECHOICE);
        //RepoPregunta.INSTANCE.agregar(multipleChoicePregunta);
        //Opcion opcion1 = new Opcion("Opción 1");
        //Opcion opcion2 = new Opcion("Opción 2");
        //Opcion opcion3 = new Opcion("Opción 3");
        //opcion1.setPregunta(multipleChoicePregunta);
        //opcion2.setPregunta(multipleChoicePregunta);
        //opcion3.setPregunta(multipleChoicePregunta);
        //multipleChoicePregunta.getOpciones().add(opcion1);
        //multipleChoicePregunta.getOpciones().add(opcion2);
        //multipleChoicePregunta.getOpciones().add(opcion3);
        //RepoOpcion.INSTANCE.agregar(opcion1);
        //RepoOpcion.INSTANCE.agregar(opcion2);
        //RepoOpcion.INSTANCE.agregar(opcion3);
        //cuestionario.agregarPregunta(multipleChoicePregunta);
        //Pregunta fechaPregunta = new Pregunta();
        //fechaPregunta.setNombre("fechaDeNacimiento");
        //fechaPregunta.setDescripcionPregunta("Ingrese su fecha de nacimiento");
        //fechaPregunta.setTipoPregunta(TipoPregunta.FECHA);
        //RepoPregunta.INSTANCE.agregar(fechaPregunta);
        //cuestionario.agregarPregunta(fechaPregunta);
        //RepoCuestionario.INSTANCE.agregar(cuestionario);


        //Colaborador colaborador = new Colaborador();
        //colaborador.setNombre("Ignacio Joaquin");
        //colaborador.setApellido("Riolffi");
        //usuario1.setNombre(colaborador.getNombre() + " " + colaborador.getApellido());
// Agregar pregunta de tipo fecha al cuestionario
        cuestionario.agregarPregunta(fechaPregunta);
// Persistir el cuestionario
        RepoCuestionario.INSTANCE.agregar(cuestionario);
/*
        Usuario usuario1 = new Usuario();
        usuario1.setCorreoElectronico("iriolffi@gmail.com");
        usuario1.setRol(TipoRol.ADMIN);
        usuario1.setContrasenia("1234");
        usuario1.setCuentaEliminada(false);
        Usuario usuario2 = new Usuario();
        usuario2.setCorreoElectronico("nacho2@gmail.com");
        usuario2.setRol(TipoRol.COLABORADOR_HUMANO);
        usuario2.setContrasenia("1234");
        Colaborador colaborador = new Colaborador();
        colaborador.setNombre("Ignacio Joaquin");
        colaborador.setApellido("Riolffi");
        usuario1.setNombre(colaborador.getNombre() + " " + colaborador.getApellido());
        repoUsuario = RepoUsuario.INSTANCE;
        repoUsuario.agregar(usuario1);
        colaborador.setNombre("Nacho");
        colaborador.setApellido("Nachito");
        usuario2.setNombre(colaborador.getNombre() + " " + colaborador.getApellido());
        repoUsuario = RepoUsuario.INSTANCE;
        //repoUsuario.agregar(usuario2);
        //colaborador.setNombre("Juan");
        //colaborador.setApellido("Perez");
        //usuario2.setNombre(colaborador.getNombre() + " " + colaborador.getApellido());
        //repoUsuario.agregar(usuario2);
        //colaborador.setTipoPersona(TipoPersona.HUMANA);
        //colaborador.setUsuario(usuario1);
        RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
        //repoColaborador.agregar(colaborador);
        //repoColaborador.agregar(colaborador);
        Tarjeta tarjeta = new Tarjeta();
        //tarjeta.setColaboradorAsignador(colaborador);
        Tarjeta tarjeta2 = new Tarjeta();
        tarjeta2.setColaboradorAsignador(colaborador);
        RepoTarjeta.INSTANCE.agregar(tarjeta);
        RepoTarjeta.INSTANCE.agregar(tarjeta2);
*/
/*
        //prueba de juridikoo
        Usuario usuario3 = new Usuario();
        usuario3.setCorreoElectronico("joaridiko@gmail.com");
        usuario3.setRol(TipoRol.COLABORADOR_JURIDICO);
        usuario3.setContrasenia("1234");
        usuario3.setNombre("Joa");
        repoUsuario.agregar(usuario3);
*/
        //tarjeta2.setColaboradorAsignador(colaborador);
        //RepoTarjeta.INSTANCE.agregar(tarjeta);
        //RepoTarjeta.INSTANCE.agregar(tarjeta2);

        ReceptorTemperatura receptorTemperatura = new ReceptorTemperatura();
        //     RepoReceptorTemperatura.INSTANCE.agregar(receptorTemperatura);

        Coordenada coordenadaHeladera = new Coordenada();
        coordenadaHeladera.setLatitud(-34.603722);
        coordenadaHeladera.setLongitud(-58.381592);
        // Save the coordenadaHeladera if necessary

        // Create and save the coordinates for the tecnico
        Coordenada coordenadaTecnico = new Coordenada();
        coordenadaTecnico.setLatitud(-34.615803);
        coordenadaTecnico.setLongitud(-58.433298);

        //    RepoCoordenada.INSTANCE.agregar(coordenadaHeladera);
        //   RepoCoordenada.INSTANCE.agregar(coordenadaTecnico);

        ModeloHeladera modeloHeladera = new ModeloHeladera();
        modeloHeladera.setTemperaturaMinima(0.0);
        modeloHeladera.setTemperaturaMaxima(10.0);
        //RepoModelo.INSTANCE.agregar(modeloHeladera);

        // Create the tecnico and associate the coordinate

        MedioDeComunicacion correo = new CorreoElectronico();


        Contacto contacto = new Contacto();
        contacto.setTipoContacto(TipoContacto.MAIL);
        contacto.setDescripcion("federperez@frba.utn.edu.ar");
        //    RepoContacto.INSTANCE.agregar(contacto);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Tecnico1");
        tecnico.setApellido("Apellido1");
        tecnico.setCoordenada(coordenadaTecnico);
        tecnico.setAreaCobertura((int) 10.0);
        tecnico.setDni(Integer.valueOf("12345678"));
        tecnico.setCuil(Integer.valueOf("12345678"));
        tecnico.setTipoDocumento(TipoDocumento.DNI);
        tecnico.setDisponible(true);
        tecnico.agregarContacto(contacto);
        tecnico.agregarMedioDeComunicacion(correo);
        //      RepoTecnico.INSTANCE.agregar(tecnico);

        Heladera heladera = new Heladera();
        heladera.setNombre("heladeraFede");
        heladera.setEstaActiva(true);
        heladera.setModelo(modeloHeladera);
        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera.setCoordenada(coordenadaHeladera);
        //RepoHeladeras.INSTANCE.agregar(heladera);

        RegistroTemperatura registroTemperatura = new RegistroTemperatura();
        registroTemperatura.setLectura(5.0F);
        registroTemperatura.setFechaHora(new Date());
        //   RepoRegistroTemperatura.INSTANCE.agregar(registroTemperatura);

        receptorTemperatura.agregarRegistro(registroTemperatura);
        //    RepoReceptorTemperatura.INSTANCE.modificar(receptorTemperatura);

        ModeloHeladera modeloPDF = new ModeloHeladera();
        modeloPDF.setTemperaturaMinima(3.0);
        modeloPDF.setTemperaturaMaxima(15.0);
        modeloPDF.setPeso(200.0);
        modeloPDF.setCantidadMaximaDeViandas(500);
        modeloPDF.setNombreModelo("Modelo PDF");
        //   RepoModelo.INSTANCE.agregar(modeloPDF);

        Vianda vianda3 = new Vianda();

        vianda3.setComida("Comida 1");
        vianda3.setFechaDonacion(new Date());

        RegistroApertura registroAperturaSACAR = new RegistroApertura();
        registroAperturaSACAR.setFechaApertura(new Date());
        registroAperturaSACAR.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroAperturaSACAR.setRetiroVianda(Boolean.TRUE);
        registroAperturaSACAR.setViandas(Collections.singletonList(vianda3));
        //   RepoRegistroApertura.INSTANCE.agregar(registroAperturaSACAR);

        Heladera heladera3 = new Heladera();
        heladera3.setNombre("Heladera Medrano");
        heladera3.setEstaActiva(true);
        heladera3.agregarApertura(registroAperturaSACAR);
        heladera3.setModelo(modeloPDF);
        //RepoHeladeras.INSTANCE.agregar(heladera3);

        vianda3.setHeladera(heladera3);
        //RepoViandas.INSTANCE.agregar(vianda3);

        Vianda vianda4 = new Vianda();
        vianda4.setHeladera(heladera3);
        vianda4.setComida("Comida 4");
        vianda4.setFechaDonacion(new Date());
        //RepoViandas.INSTANCE.agregar(vianda4);

        RegistroApertura registroAperturaPONER = new RegistroApertura();
        registroAperturaPONER.setFechaApertura(new Date());
        registroAperturaPONER.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroAperturaPONER.setRetiroVianda(Boolean.FALSE);
        registroAperturaPONER.setViandas(Collections.singletonList(vianda4));
        //   RepoRegistroApertura.INSTANCE.agregar(registroAperturaPONER);

        Incidente incidente3 = new Incidente();
        incidente3.setDescripcion("Incidente de prueba 1");
        incidente3.setPathFoto(null);
        incidente3.setTipoIncidente(TipoIncidente.FALLA);
        incidente3.setTipoAlerta(null);

        //  RepoHeladeras.INSTANCE.agregar(heladera3);
        incidente3.setHeladera(heladera3);
        //  RepoIncidente.INSTANCE.agregar(incidente3);
        heladera3.agregarRegistroDeAlerta(incidente3);
        //RepoHeladeras.INSTANCE.modificar(heladera3);

        DonacionVianda donacionVianda3 = new DonacionVianda();
        donacionVianda3.setFechaColaboracion(new Date());
        donacionVianda3.setVianda(vianda3);

        DonacionVianda donacionVianda4 = new DonacionVianda();
        donacionVianda4.setFechaColaboracion(new Date());
        donacionVianda4.setVianda(vianda4);


        Colaborador colaborador3 = new Colaborador();
        colaborador3.setNombre("Juan");
        colaborador3.setApellido("Perez");
        colaborador3.setTipoPersona(TipoPersona.HUMANA);
        vianda3.setColaborador(colaborador3);
        colaborador3.agregarColaboracionRealizada(donacionVianda3);
        colaborador3.agregarColaboracionRealizada(donacionVianda4);
        //RepoColaborador.INSTANCE.agregar(colaborador3);
        //RepoDonacionVianda.INSTANCE.agregar(donacionVianda3);
        // RepoDonacionVianda.INSTANCE.agregar(donacionVianda4);

        Heladera heladera4 = new Heladera();
        heladera4.setNombre("Heladera de Fede");
        heladera4.setEstaActiva(true);
        heladera4.setModelo(modeloPDF);
        heladera4.agregarApertura(registroAperturaPONER);
        //RepoHeladeras.INSTANCE.agregar(heladera4);

        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setFechaColaboracion(new Date());
        distribucionVianda.setCantidadViandas(10);
        distribucionVianda.setMotivo(MotivoDistribucion.FALTA_VIANDAS_H_DESTINO);
        distribucionVianda.setHeladeraOrigen(heladera3);
        distribucionVianda.setHeladeraDestino(heladera4);
        distribucionVianda.setTipoColaboracion(TipoColaboracion.REDISTRIBUCION_VIANDAS);
        distribucionVianda.setColaborador(colaborador3);
        //RepoDistribucionVianda.INSTANCE.agregar(distribucionVianda);
    }


}
