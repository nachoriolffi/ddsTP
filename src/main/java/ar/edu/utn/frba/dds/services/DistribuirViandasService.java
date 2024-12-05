package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.outputs.DistribucionViandaOutputDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.formasColab.DistribucionVianda;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.vianda.Vianda;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoViandas;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DistribuirViandasService {
    public List<Heladera> buscarHeladeras() {

        return RepoHeladeras.INSTANCE.buscarTodos().stream().
                filter(heladera -> heladera.getEstaActiva()
                        && !heladera.getDadaDeBaja()).collect(Collectors.toList());
    }

    public Colaborador buscarColaborador(Usuario usuario) {
        return RepoColaborador.INSTANCE.buscarPorIdUsuario(usuario.getId());
    }

    public List<DistribucionViandaOutputDTO> buscarDistribucionesVianda(Usuario usuario) {
        Colaborador colaborador = this.buscarColaborador(usuario);
        List<DistribucionVianda> distribuciones = colaborador.getColaboracionesRealizadas().stream()
                .filter(c -> c instanceof DistribucionVianda)
                .map(c -> (DistribucionVianda) c)
                .toList();

        List<DistribucionViandaOutputDTO> distribucionViandaOutputDTOS = new ArrayList<>();

        for (DistribucionVianda distribucion : distribuciones) {
            DistribucionViandaOutputDTO distribucionViandaOutputDTO = new DistribucionViandaOutputDTO();

            // Comprobar si distribucion.getCantidadViandas() es null, en cuyo caso asignar "0"
            distribucionViandaOutputDTO.setViandasMovidas(distribucion.getCantidadViandas() != null ? String.valueOf(distribucion.getCantidadViandas()) : "0");

            // Comprobar si distribucion.getMotivo() es null, en cuyo caso asignar ""
            distribucionViandaOutputDTO.setMotivo(distribucion.getMotivo() != null ? distribucion.getMotivo().toString() : "");

            // Comprobar si distribucion.getFechaDistribucion() es null, en cuyo caso asignar ""
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            distribucionViandaOutputDTO.setFechaDistribucion(distribucion.getFechaDistribucion() != null ? sdf.format(distribucion.getFechaDistribucion()) : "");

            // Comprobar si distribucion.getHeladeraOrigen() o distribucion.getHeladeraDestino() es null, en cuyo caso asignar ""
            distribucionViandaOutputDTO.setHeladeraOrigen(distribucion.getHeladeraOrigen() != null && distribucion.getHeladeraOrigen().getNombre() != null ? distribucion.getHeladeraOrigen().getNombre() : "");
            distribucionViandaOutputDTO.setHeladeraDestino(distribucion.getHeladeraDestino() != null && distribucion.getHeladeraDestino().getNombre() != null ? distribucion.getHeladeraDestino().getNombre() : "");

            distribucionViandaOutputDTOS.add(distribucionViandaOutputDTO);
        }

        return distribucionViandaOutputDTOS;
    }


    public List<Vianda> buscarViandas(long heladeraId) {
        return RepoViandas.INSTANCE.buscarViandasPorHeladeraId(heladeraId);
    }
}
