package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.*;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Cuestionario;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Opcion;
import ar.edu.utn.frba.dds.models.entities.cuestionario.Pregunta;
import ar.edu.utn.frba.dds.models.entities.cuestionario.TipoPregunta;
import ar.edu.utn.frba.dds.models.entities.generadorCodigo.GeneradorDeCodigo;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoPersona;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.tecnico.Tecnico;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.usuario.TipoRol;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;

import java.time.LocalDate;
import java.util.*;

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

        // HUMANO
        Usuario usuarioHumano = new Usuario();
        usuarioHumano.setNombre("Matias");
        usuarioHumano.setApellido("Tisco");
        usuarioHumano.setContrasenia("1234");
        usuarioHumano.setCuentaEliminada(false);
        usuarioHumano.setCorreoElectronico("mati@gmail.com");
        usuarioHumano.setRol(TipoRol.COLABORADOR_HUMANO);
        //repoUsuario.agregar(usuarioHumano);

        // JURIDICO
        Usuario usuarioJuridico = new Usuario();
        usuarioJuridico.setNombre("Samsung");
        usuarioJuridico.setContrasenia("1234");
        usuarioJuridico.setCuentaEliminada(false);
        usuarioJuridico.setCorreoElectronico("samsung@gmail.com");
        usuarioJuridico.setRol(TipoRol.COLABORADOR_JURIDICO);
        //repoUsuario.agregar(usuarioJuridico);

        // TECNICO
        Usuario usuarioTecnico = new Usuario();
        usuarioTecnico.setNombre("Federico");
        usuarioTecnico.setApellido("Perez");
        usuarioTecnico.setContrasenia("1234");
        usuarioTecnico.setCuentaEliminada(false);
        usuarioTecnico.setCorreoElectronico("fede@gmail.com");
        usuarioTecnico.setRol(TipoRol.TECNICO);
        //repoUsuario.agregar(usuarioTecnico);

        /*--------------TARJETAS--------------*/

        RepoTarjeta repoTarjeta = new RepoTarjeta();
        for (int i = 0; i < 50; i++) {
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setCodigo(GeneradorDeCodigo.getInstance().generarCodigoUnico());
            repoTarjeta.agregar(tarjeta);
        }

        /*--------------COLABORADORES--------------*/

        // COLABORADOR HUMANO
        /*List<TipoColaboracion> formaDeColaboraciones = new ArrayList<>();
        formaDeColaboraciones.add(TipoColaboracion.DONACION_VIANDAS);
        formaDeColaboraciones.add(TipoColaboracion.DINERO);
        formaDeColaboraciones.add(TipoColaboracion.REDISTRIBUCION_VIANDAS);
        formaDeColaboraciones.add(TipoColaboracion.ENTREGA_TARJETAS);
        usuarioHumano = repoUsuario.buscar(2L);
        Colaborador colaboradorHumano = new Colaborador();
        colaboradorHumano.setNombre("Ignacio Joaquin");
        colaboradorHumano.setApellido("Riolffi");
        colaboradorHumano.setUsuario(usuarioHumano);
        colaboradorHumano.setTipoPersona(TipoPersona.HUMANA);
        colaboradorHumano.setFechaDeNacimiento(LocalDate.now());
        colaboradorHumano.setFormasDeColaboracion(formaDeColaboraciones);
        colaboradorHumano.setFueCargaMasiva(false);
        RepoColaborador.INSTANCE.agregar(colaboradorHumano);
        // Asigno Tarjetas al colaborador para registrar vulnerables
        List<Tarjeta> tarjetas = repoTarjeta.buscarTodos();
        for (int i = 0; i < 10; i++) {
            Tarjeta tarjeta = tarjetas.get(i);
            if (tarjeta.getColaboradorAsignador() == null) {
                tarjeta.setColaboradorAsignador(colaboradorHumano);
                repoTarjeta.modificar(tarjeta);
            }
        }
        // Asigno tarjeta al colaborador para que pueda realizar donaciones y distribuciones
        for (Tarjeta tarjeta : tarjetas) {
            if (tarjeta.getColaboradorAsignador() == null && tarjeta.getColaboradorAsociado() == null) {
                tarjeta.setColaboradorAsociado(colaboradorHumano);
                repoTarjeta.modificar(tarjeta);
                break;
            }
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


        //---------TECNICO---------

        /*Coordenada coordenadaTecnico = new Coordenada();
        coordenadaTecnico.setLatitud(-34.615803);
        coordenadaTecnico.setLongitud(-58.433298);

        RepoCoordenada.INSTANCE.agregar(coordenadaTecnico);

        Tecnico tecnico = new Tecnico();
        tecnico.setNombre("Tecnico1");
        tecnico.setApellido("Apellido1");
        tecnico.setCoordenada(coordenadaTecnico);
        tecnico.setAreaCobertura((int) 10.0);
        tecnico.setDni(Integer.valueOf("12345678"));
        tecnico.setCuil(Integer.valueOf("12345678"));
        tecnico.setTipoDocumento(TipoDocumento.DNI);
        tecnico.setDisponible(true);
        Usuario u = RepoUsuario.INSTANCE.buscar(4L);
        tecnico.setUsuario(u);
        //tecnico.agregarMedioDeComunicacion(correo);
        RepoTecnico.INSTANCE.agregar(tecnico);*/


        //-------------------------

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

/*
        ReceptorTemperatura receptorTemperatura = new ReceptorTemperatura();
             RepoReceptorTemperatura.INSTANCE.agregar(receptorTemperatura);

        Coordenada coordenadaHeladera = new Coordenada();
        coordenadaHeladera.setLatitud(-34.603722);
        coordenadaHeladera.setLongitud(-58.381592);
        // Save the coordenadaHeladera if necessary

        Contacto contacto = new Contacto();
        contacto.setTipoContacto(TipoContacto.MAIL);
        contacto.setDescripcion("federperez@frba.utn.edu.ar");
            RepoContacto.INSTANCE.agregar(contacto);

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
              RepoTecnico.INSTANCE.agregar(tecnico);

        Heladera heladera = new Heladera();
        heladera.setNombre("heladeraFede");
        heladera.setEstaActiva(true);
        heladera.setModelo(modeloHeladera);
        heladera.setReceptorTemperatura(receptorTemperatura);
        heladera.setCoordenada(coordenadaHeladera);
        RepoHeladeras.INSTANCE.agregar(heladera);

        RegistroTemperatura registroTemperatura = new RegistroTemperatura();
        registroTemperatura.setLectura(5.0F);
        registroTemperatura.setFechaHora(new Date());
          RepoRegistroTemperatura.INSTANCE.agregar(registroTemperatura);

        receptorTemperatura.agregarRegistro(registroTemperatura);
            RepoReceptorTemperatura.INSTANCE.modificar(receptorTemperatura);


        RegistroApertura registroAperturaSACAR = new RegistroApertura();
        registroAperturaSACAR.setFechaApertura(new Date());
        registroAperturaSACAR.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroAperturaSACAR.setRetiroVianda(Boolean.TRUE);
        registroAperturaSACAR.setViandas(Collections.singletonList(vianda3));
           RepoRegistroApertura.INSTANCE.agregar(registroAperturaSACAR);

        RegistroApertura registroAperturaPONER = new RegistroApertura();
        registroAperturaPONER.setFechaApertura(new Date());
        registroAperturaPONER.setSolicitud(TipoSolicitud.REDISTRIBUCION_VIANDAS);
        registroAperturaPONER.setRetiroVianda(Boolean.FALSE);
        registroAperturaPONER.setViandas(Collections.singletonList(vianda4));
           RepoRegistroApertura.INSTANCE.agregar(registroAperturaPONER);

        Incidente incidente3 = new Incidente();
        incidente3.setDescripcion("Incidente de prueba 1");
        incidente3.setPathFoto(null);
        incidente3.setTipoIncidente(TipoIncidente.FALLA);
        incidente3.setTipoAlerta(null);


        Heladera heladera4 = new Heladera();
        heladera4.setNombre("Heladera de Fede");
        heladera4.setEstaActiva(true);
        heladera4.setModelo(modeloPDF);
        heladera4.agregarApertura(registroAperturaPONER);
        RepoHeladeras.INSTANCE.agregar(heladera4);

        DistribucionVianda distribucionVianda = new DistribucionVianda();
        distribucionVianda.setFechaColaboracion(new Date());
        distribucionVianda.setCantidadViandas(10);
        distribucionVianda.setMotivo(MotivoDistribucion.FALTA_VIANDAS_H_DESTINO);
        distribucionVianda.setHeladeraOrigen(heladera3);
        distribucionVianda.setHeladeraDestino(heladera4);
        distribucionVianda.setTipoColaboracion(TipoColaboracion.REDISTRIBUCION_VIANDAS);
        distribucionVianda.setColaborador(colaborador3);
        RepoDistribucionVianda.INSTANCE.agregar(distribucionVianda);
}
 */
