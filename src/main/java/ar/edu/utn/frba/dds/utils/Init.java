package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.RubroColaborador;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.TipoPregunta;
import ar.edu.utn.frba.dds.models.entities.generadorCodigo.GeneradorDeCodigo;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
<<<<<<< HEAD
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.ObserverColaborador;
import ar.edu.utn.frba.dds.models.entities.heladera.suscripcion.TipoSuscripcion;
=======
>>>>>>> 4f9c085fc6ebc55a61e790fec35a161d59c87a3e
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.util.List;

public class Init implements WithSimplePersistenceUnit {

    public static void iniciar() {

        /*--------------MODELOS--------------*/

        /*RepoModelo repoModelo = new RepoModelo();

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
        repoModelo.agregar(modeloHeladera3);*/

        /*--------------USUARIOS--------------*/

        RepoUsuario repoUsuario = RepoUsuario.INSTANCE;

        // ADMIN
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setNombre("Ignacio Joaquin");
        usuarioAdmin.setApellido("Riolffi");
        usuarioAdmin.setContrasenia("Nacho_2024_ADMIN_DDS");
        usuarioAdmin.setCuentaEliminada(false);
        usuarioAdmin.setCorreoElectronico("iriolffi@gmail.com");
        usuarioAdmin.setRol(TipoRol.ADMIN);
        repoUsuario.agregar(usuarioAdmin);

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

     /*   // JURIDICO

        Cuestionario cuestionarioJuridico = new Cuestionario();
        cuestionarioJuridico.setDescripcion("Cuestionario Juridico");
        cuestionarioJuridico.setNombreCuestionario("Cuestionario Juridico");

        Pregunta preguntaRazonSocial = new Pregunta();
        preguntaRazonSocial.setNombre("razonSocial");
        preguntaRazonSocial.setTipoPregunta(TipoPregunta.STRING);
        preguntaRazonSocial.setDescripcionPregunta("Ingrese Su Razon Social");
        preguntaRazonSocial.setEsObligatoria(true);

        Pregunta preguntaTipoRazon = new Pregunta();
        preguntaTipoRazon.setNombre("tipoJuridiccion");
        preguntaTipoRazon.setTipoPregunta(TipoPregunta.RESPUESTAUNICA);
        preguntaTipoRazon.setDescripcionPregunta("Indique Su Tipo De Razon Social");
        preguntaTipoRazon.setEsObligatoria(true);

        Opcion opcionGubernamental = new Opcion("GUBERNAMENTAL");
        Opcion opcionONG = new Opcion("ONG");
        Opcion opcionEmpresa = new Opcion("EMPRESA");
        Opcion opcionInstitucion = new Opcion("INSTITUCION");


        repoPregunta.agregar(preguntaRazonSocial);
        repoPregunta.agregar(preguntaTipoRazon);


        opcionGubernamental.setPregunta(preguntaTipoRazon);
        opcionONG.setPregunta(preguntaTipoRazon);
        opcionEmpresa.setPregunta(preguntaTipoRazon);
        opcionInstitucion.setPregunta(preguntaTipoRazon);

        preguntaTipoRazon.getOpciones().add(opcionGubernamental);
        preguntaTipoRazon.getOpciones().add(opcionONG);
        preguntaTipoRazon.getOpciones().add(opcionEmpresa);
        preguntaTipoRazon.getOpciones().add(opcionInstitucion);

        repoOpcion.agregar(opcionGubernamental);
        repoOpcion.agregar(opcionONG);
        repoOpcion.agregar(opcionEmpresa);
        repoOpcion.agregar(opcionInstitucion);

        Pregunta preguntaRubroJuridico = new Pregunta();
        preguntaRubroJuridico.setNombre("rubroColaborador");
        preguntaRubroJuridico.setDescripcionPregunta("Rubro de Persona Juridica");
        preguntaRubroJuridico.setTipoPregunta(TipoPregunta.STRING);
        preguntaRubroJuridico.setEsObligatoria(true);

        cuestionarioJuridico.agregarPregunta(preguntaRubroJuridico);
        cuestionarioJuridico.agregarPregunta(preguntaRazonSocial);
        cuestionarioJuridico.agregarPregunta(preguntaTipoRazon);

        repoCuestionario.agregar(cuestionarioJuridico);*/

        //HUMANO

       /* Cuestionario cuestionarioHumano = new Cuestionario();
        cuestionarioHumano.setNombreCuestionario("Cuestionario Humano");
        cuestionarioHumano.setDescripcion("Este es el cuestionario para registrar colaboradores humanos");

        Pregunta preguntaNombre = new Pregunta();
        preguntaNombre.setEsObligatoria(true);
        preguntaNombre.setNombre("nombre");
        preguntaNombre.setTipoPregunta(TipoPregunta.STRING);
        preguntaNombre.setDescripcionPregunta("Ingrese su nombre");

        Pregunta preguntaApellido = new Pregunta();
        preguntaApellido.setEsObligatoria(true);
        preguntaApellido.setNombre("apellido");
        preguntaApellido.setTipoPregunta(TipoPregunta.STRING);
        preguntaApellido.setDescripcionPregunta("Ingrese su Apellido");

        RepoPregunta.INSTANCE.agregar(preguntaNombre);
        ;
        RepoPregunta.INSTANCE.agregar(preguntaApellido);
        cuestionarioHumano.agregarPregunta(preguntaNombre);
        cuestionarioHumano.agregarPregunta(preguntaApellido);

        Pregunta fechaPregunta = new Pregunta();
        fechaPregunta.setNombre("fechaDeNacimiento");
        fechaPregunta.setDescripcionPregunta("Ingrese su fecha de nacimiento");
        fechaPregunta.setTipoPregunta(TipoPregunta.FECHA);
        RepoPregunta.INSTANCE.agregar(fechaPregunta);
        cuestionarioHumano.agregarPregunta(fechaPregunta);

        RepoCuestionario.INSTANCE.agregar(cuestionarioHumano);*/


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

        //Suscripciones
       /* Usuario usuarioSuscripcion = new Usuario();
        usuarioSuscripcion.setNombre("Nahuel");
        usuarioSuscripcion.setApellido("Lazarte");
        usuarioSuscripcion.setContrasenia("1234");
        usuarioSuscripcion.setCuentaEliminada(false);
        usuarioSuscripcion.setCorreoElectronico("clazarte@frba.utn.edu.ar");
        usuarioSuscripcion.setRol(TipoRol.COLABORADOR_HUMANO);
        repoUsuario.agregar(usuarioSuscripcion);

        Colaborador colaborador1 = new Colaborador();
        colaborador1.setNombre("Martin");
        colaborador1.setApellido("Fierro");
        colaborador1.setTipoDocumento(TipoDocumento.DNI);
        colaborador1.setNumeroDocumento(12345845);
        colaborador1.setTipoPersona(TipoPersona.HUMANA);

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto(TipoContacto.MAIL, "clazarte@frba.utn.edu.ar"));
        contactos.add(new Contacto(TipoContacto.TELEGRAM, "7166927758"));
        colaborador1.setContacto(contactos);

        List<MedioDeComunicacion> mediosDeComunicacion = new ArrayList<>();
        mediosDeComunicacion.add(new CorreoElectronico(new AdapterCorreo()));
        mediosDeComunicacion.add(new Telegram(new AdapterTelegram()));
        colaborador1.setMediosDeComunicacion(mediosDeComunicacion);
        colaborador1.setUsuario(usuarioSuscripcion);
        RepoColaborador.INSTANCE.agregar(colaborador1);

        Coordenada coordenada = new Coordenada();
        coordenada.setLatitud(-34.603722);
        coordenada.setLongitud(-58.381592);
        RepoCoordenada.INSTANCE.agregar(coordenada);

        ReceptorTemperatura receptorTemperatura = new ReceptorTemperatura();
        RepoReceptorTemperatura.INSTANCE.agregar(receptorTemperatura);

        ReceptorMovimiento receptorMovimiento = new ReceptorMovimiento();
        RepoRegistroMovimiento.INSTANCE.agregar(receptorMovimiento);

        RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
        Direccion direccion1 = new Direccion("Medrano", 951, 1);
        RepoDireccion.INSTANCE.agregar(direccion1);
        Heladera heladera1 = new Heladera();
        heladera1.setDireccion(direccion1);
        heladera1.setReceptorTemperatura(receptorTemperatura);
        heladera1.setReceptorMovimiento(receptorMovimiento);
        heladera1.setNombre("UTN MEDRANO");
        heladera1.setFechaPuestaFunc(new Date());
        heladera1.setCoordenada(coordenada);
        heladera1.setEstaActiva(Boolean.TRUE);
        heladera1.setModelo(modeloHeladera1);
        heladera1.setDadaDeBaja(Boolean.FALSE);
        heladera1.setViandasDisponibles(modeloHeladera1.getCantidadMaximaDeViandas());
        heladera1.agregarVianda();
        heladera1.agregarVianda();


        ObserverColaborador observer = new ObserverColaborador();
        observer.setTipoSuscripcion(TipoSuscripcion.DESPERFECTO);
        observer.setSuscriptor(colaborador1);
        RepoSuscriptorHeladera.INSTANCE.agregar(observer);

        ObserverColaborador observer2 = new ObserverColaborador();
        observer2.setTipoSuscripcion(TipoSuscripcion.MUCHAS_VIANDAS);
        observer2.setCantidadViandas(8);
        observer2.setSuscriptor(colaborador1);
        RepoSuscriptorHeladera.INSTANCE.agregar(observer2);

        ObserverColaborador observer3 = new ObserverColaborador();
        observer3.setTipoSuscripcion(TipoSuscripcion.VIANDAS_DISPONIBLES);
        observer3.setCantidadViandas(2);
        observer3.setSuscriptor(colaborador1);
        RepoSuscriptorHeladera.INSTANCE.agregar(observer3);

        heladera1.agregarColaborador(observer);
        heladera1.agregarColaborador(observer2);
        heladera1.agregarColaborador(observer3);
        repoHeladeras.agregar(heladera1);*/

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