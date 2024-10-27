package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.ModeloDTO;
import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Calle;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Direccion;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.Georef;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.georef.responseClases.GeorefInitializer;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.*;

import java.util.*;

public class HeladeraService {

    RepoModelo repoModelo = RepoModelo.INSTANCE;
    RepoHeladeras repoHeladeras = RepoHeladeras.INSTANCE;
    RepoCoordenada repoCoordenada = RepoCoordenada.INSTANCE;
    RepoCalle repoCalle = RepoCalle.INSTANCE;
    RepoDireccion repoDireccion = RepoDireccion.INSTANCE;
    RepoViandas repoViandas = RepoViandas.INSTANCE;

    public List<ModeloHeladera> obtenerModelosHeladera() {
        return repoModelo.buscarTodos();
    }

    public List<HeladeraOutputDTO> obtenerHeladeras() {
        List<Heladera> heladeras = repoHeladeras.buscarTodos();
        List<HeladeraOutputDTO> heladerasDTO = new ArrayList<>();
        for (Heladera h : heladeras) {
            if (!h.getDadaDeBaja()) {
                HeladeraOutputDTO heladeraDTO = new HeladeraOutputDTO();
                heladeraDTO.setId(String.valueOf(h.getId()));
                heladeraDTO.setNombre(h.getNombre());
                heladeraDTO.setCapacidad(h.getModelo().getCantidadMaximaDeViandas());
                heladeraDTO.setViandasRestantes(String.valueOf(h.getViandasDisponibles()));
                heladeraDTO.setLatitud(h.getCoordenada().getLatitud());
                heladeraDTO.setLongitud(h.getCoordenada().getLongitud());
                heladeraDTO.setDireccion(h.getDireccion().getCalle().getCalle() + " " + h.getDireccion().getAltura() + " Piso:" + h.getDireccion().getPiso());
                heladerasDTO.add(heladeraDTO);
            }
        }
        return heladerasDTO;
    }

    public void darDeAltaHeladera(HeladeraInputDTO heladeraInputDTO) {
        ModeloHeladera modelo = this.repoModelo.buscar(Long.valueOf(heladeraInputDTO.getModelo()));
        Heladera heladera = new Heladera();
        heladera.setNombre(heladeraInputDTO.getNombre());
        heladera.setModelo(modelo);
        Georef georef = GeorefInitializer.initializeGeoref();
        Coordenada coordenada = georef.obtenerCoordenadasPorDireccion(heladeraInputDTO.getCalle() + heladeraInputDTO.getAltura());
        repoCoordenada.agregar(coordenada);
        heladera.setCoordenada(coordenada);
        heladera.setViandasDisponibles(0);
        heladera.setDadaDeBaja(false);
        heladera.setFechaPuestaFunc(new Date());
        heladera.setEstaActiva(true);
        Direccion direccion = new Direccion();
        direccion.setAltura(Integer.valueOf(heladeraInputDTO.getAltura()));
        Calle calle = new Calle();
        calle.setCalle(heladeraInputDTO.getCalle());
        repoCalle.agregar(calle);
        direccion.setCalle(calle);
        direccion.setPiso(Integer.valueOf(heladeraInputDTO.getPiso()));
        repoDireccion.agregar(direccion);
        heladera.setDireccion(direccion);
        heladera.setReceptorTemperatura(new ReceptorTemperatura());
        heladera.setReceptorMovimiento(new ReceptorMovimiento());
        repoHeladeras.agregar(heladera);
    }

    public void cargarModelo(ModeloDTO modeloDTO) {
        ModeloHeladera modeloHeladera = new ModeloHeladera();
        modeloHeladera.setNombreModelo(modeloDTO.getNombre());
        modeloHeladera.setPeso(Double.valueOf(modeloDTO.getPeso()));
        modeloHeladera.setTemperaturaMaxima(Double.valueOf(modeloDTO.getTempMax()));
        modeloHeladera.setTemperaturaMinima(Double.valueOf(modeloDTO.getTempMin()));
        modeloHeladera.setCantidadMaximaDeViandas(Integer.valueOf(modeloDTO.getCantidadViandas()));
        repoModelo.agregar(modeloHeladera);
    }

    public Map<String, Object> mostrarHeladera(Long id) {
        Heladera heladera = repoHeladeras.buscar(id);
        Map<String, Object> model = new HashMap<>();
        if (heladera == null) {
            return null;
        }
        model.put("nombre", heladera.getNombre());
        model.put("direccion", heladera.getDireccion().getCalle().getCalle() + " " +
                " " + heladera.getDireccion().getAltura().toString() +
                " Piso: " + heladera.getDireccion().getPiso().toString());
        model.put("latitud", heladera.getCoordenada().getLatitud());
        model.put("longitud", heladera.getCoordenada().getLongitud());
        model.put("viandas", repoViandas.buscarViandasPorHeladeraId(heladera.getId()));
        model.put("aperturas", heladera.getAperturas());
        // model.put("alertas", heladera.getAlertas());

        return model;
    }

    public void darDeBajaHeladera(long id) {
        Heladera heladera = repoHeladeras.buscar(id);
        heladera.setEstaActiva(false);
        heladera.setDadaDeBaja(true);
        repoHeladeras.modificar(heladera);
    }

    public boolean modificarHeladera(long id, HeladeraInputDTO heladeraInputDTO) {
        Heladera heladera = repoHeladeras.buscar(id);
        if (!heladeraInputDTO.getNombre().isEmpty()) {
            if (yaHayHeladeraConEseNombre(heladeraInputDTO.getNombre())) {
                return false;
            } else {
                heladera.setNombre(heladeraInputDTO.getNombre());
            }
        }
        if (heladeraInputDTO.getModelo() != null && !heladeraInputDTO.getModelo().isEmpty()) {
            ModeloHeladera modeloHeladera = repoModelo.buscar(Long.valueOf(heladeraInputDTO.getModelo()));
            heladera.setModelo(modeloHeladera);
        }
        if (heladeraInputDTO.getCalle() != null && heladeraInputDTO.getAltura() != null && !heladeraInputDTO.getCalle().isEmpty() && !heladeraInputDTO.getAltura().isEmpty()) {
            Calle calle = new Calle();
            calle.setCalle(heladeraInputDTO.getCalle());
            repoCalle.agregar(calle);
            heladera.getDireccion().setCalle(calle);
            heladera.getDireccion().setAltura(Integer.valueOf(heladeraInputDTO.getAltura()));
            heladera.getDireccion().setPiso(Integer.valueOf(heladeraInputDTO.getPiso()));
        }
        repoHeladeras.modificar(heladera);
        return true;
    }

    public boolean yaHayHeladeraConEseNombre(String nombre) {
        List<Heladera> heladeras = repoHeladeras.buscarTodos();
        for (Heladera heladera : heladeras) {
            if (heladera.getNombre().equals(nombre) && !heladera.getDadaDeBaja()) {
                return true;
            }
        }
        return false;
    }

}
