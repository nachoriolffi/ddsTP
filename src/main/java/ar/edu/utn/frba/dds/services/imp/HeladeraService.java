package ar.edu.utn.frba.dds.services.imp;

import ar.edu.utn.frba.dds.dtos.inputs.HeladeraInputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.heladera.ModeloHeladera;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorMovimiento;
import ar.edu.utn.frba.dds.models.entities.heladera.receptor.ReceptorTemperatura;
import ar.edu.utn.frba.dds.models.entities.ubicacionGeografica.Coordenada;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoHeladeras;
import ar.edu.utn.frba.dds.services.interfaces.IHeladeraService;

import java.util.ArrayList;
import java.util.Date;

public class HeladeraService implements IHeladeraService {
    private RepoHeladeras heladeraRepository;

    public HeladeraService(RepoHeladeras heladeraRepository) {
        this.heladeraRepository = heladeraRepository;
    }

    private Coordenada coordenada;

    @Override
    public Heladera crear(HeladeraInputDTO dtoHeladera) {

        Heladera nuevaHeladera = new Heladera();
        nuevaHeladera.setNombre(dtoHeladera.getNombre());
        nuevaHeladera.setDireccion(dtoHeladera.getDireccion());
        nuevaHeladera.setCapacidad(dtoHeladera.getCapacidad());
        nuevaHeladera.setFechaPuestaFunc(new Date());
        nuevaHeladera.setViandas(new ArrayList<>());
        nuevaHeladera.setIncidentes(new ArrayList<>());
        nuevaHeladera.setAperturas(new ArrayList<>());
        nuevaHeladera.setAperturas(new ArrayList<>());
        nuevaHeladera.setEstaActiva(Boolean.TRUE);
        nuevaHeladera.setTempActual(dtoHeladera.getTempActual());
        nuevaHeladera.setReceptorMovimiento( new ReceptorMovimiento());
        nuevaHeladera.setReceptorTemperatura( new ReceptorTemperatura());
        ModeloHeladera modelo =null;
        nuevaHeladera.setModelo(modelo);
        nuevaHeladera.setCoordenada(dtoHeladera.getCoordenada());
        this.heladeraRepository.agregar(nuevaHeladera);
        return nuevaHeladera;
    }

    @Override
    public Heladera modificar(Integer id,HeladeraInputDTO dto) {

        //Optional <Heladera> posibleHeladera = this.heladeraRepository.buscarHeladera(id) pasar todos los buscar a buscar por id
//        if(posibleHeladera.isEmpty()) {
//            //throw new HeladeraNoEncontrada(); crear excepcion
//        }
        //Heladera heladeraAModificar = posibleHeladera.get();

        //cambiar datos

        //guardar en repo modificacion

        //heladeraRepository.actualizar(heladeraAModificar)
//        HeladeraOutputDTO output = new HeladeraOutputDTO();

        //tiene sentido esto??
//        output.setDireccion(heladeraAModificar.getDireccion());
//        output.setCoordenada(heladeraAModificar.getCoordenada());
//        output.setCapacidad(heladeraAModificar.getCapacidad());
//        output.setEstaActiva(heladeraAModificar.getEstaActiva());
//
//        return output;
        return null;
    }

    @Override
    public void eliminar(Integer id) { //long id
       /* Heladera nuevaHeladera = new Heladera(
                dtoHeladera.getDireccion(),
                dtoHeladera.getCoordenada(),
                dtoHeladera.getCapacidad(),
                dtoHeladera.getEstaActiva()
                //dtoHeladera.getIdViandas(), Ver como buscamos en el repo por id, deberia tener un Repo Viandas?? o como??
        );

        this.heladeraRepository.eliminarHeladera(nuevaHeladera);*/


    }
}