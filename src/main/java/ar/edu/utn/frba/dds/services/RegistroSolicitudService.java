package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.RegistroSolicitudDTO;
import ar.edu.utn.frba.dds.models.entities.broker.Broker;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.RegistroSolicitud;
import ar.edu.utn.frba.dds.models.entities.heladera.TipoSolicitud;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoRegistroSolicitud;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.eclipse.paho.client.mqttv3.MqttException;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class RegistroSolicitudService {


    private Broker broker;


    public void registrarSolicitud() {
    }



    public void registrarSolicitud(TipoSolicitud tipoSolicitud, Tarjeta tarjeta, Heladera heladera, Vianda vianda) throws Exception {
        RegistroSolicitud registroSolicitud = new RegistroSolicitud();
        registroSolicitud.setFechaSolicitud(new Date());
        registroSolicitud.setSolicitud(tipoSolicitud);
        registroSolicitud.setRealizada(false);
        registroSolicitud.setTarjeta(tarjeta);
        registroSolicitud.setHeladeraAIr(heladera);
        registroSolicitud.setRetiroVianda(false);

//        List<Vianda> viandaAgregada = new ArrayList<Vianda>();
//        viandaAgregada.add(vianda);
//        registroSolicitud.setCantidadViandas(viandaAgregada);

        RegistroSolicitudDTO registroSolicitudDTO = new RegistroSolicitudDTO(registroSolicitud);


        RepoRegistroSolicitud.INSTANCE.agregar(registroSolicitud);

        this.publicarSolicitudApertura(registroSolicitudDTO);
    }




    public void publicarSolicitudApertura(RegistroSolicitudDTO registroSolicitudDTO) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        // Serializa el objeto a JSON
        String data = objectMapper.writeValueAsString(registroSolicitudDTO);

        // Escapa las comillas en el JSON generado
        //String data = rawData.replace("\"", "\\\""); //"hola";

        broker = new Broker();
        broker.connect("1");

        String topic = "dds25/topic_solicitudApertura/1"; // "dds25/topic_solicitudApertura/" + registroSolicitudDTO.getHeladeraId();

        broker.publish(topic, data); // Publica con el formato escapado

        broker.disconnect();
    }



}



/*
public void guardarApertura(PersonaHumana personaHumana,
                            TarjetaColaborador tarjetaColaborador,
                            Heladera heladera,
                            LocalDateTime horaSolicitud,
                            MotivoApertura motivoApertura) {

    AperturaHeladera aperturaHeladera = new AperturaHeladera();
    aperturaHeladera.setTarjeta(this.repositorioTarjetaColaborador.buscarPorColaborador(personaHumana).get(0));
    aperturaHeladera.setTarjeta(tarjetaColaborador);
    aperturaHeladera.setHoraSolicitud(horaSolicitud);
    aperturaHeladera.setHeladera(heladera);
    aperturaHeladera.setMotivo(motivoApertura);
    aperturaHeladera.setHoraLimite(horaSolicitud.plusHours(heladera.getHorasParaApertura()));
    aperturaHeladera.setEstado(EstadoApertura.SOLICITADA);
    aperturaHeladeraRepository.guardar(aperturaHeladera);

    this.enviarAperturaHeladera(AperturaHeladeraFactory.toBrokerDTO(aperturaHeladera));
}*/