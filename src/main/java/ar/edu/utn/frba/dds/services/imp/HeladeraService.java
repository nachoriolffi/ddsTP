package ar.edu.utn.frba.dds.services.imp;

import ar.edu.utn.frba.dds.dtos.HeladeraInputDTO;
import ar.edu.utn.frba.dds.dtos.HeladeraOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoHeladeras;
import ar.edu.utn.frba.dds.services.IHeladeraService;

public class HeladeraService implements IHeladeraService {
    private IRepoHeladeras heladeraRepository;


    public HeladeraService(IRepoHeladeras heladeraRepository) {
        this.heladeraRepository = heladeraRepository;
    }

    @Override
    public HeladeraOutputDTO crear(HeladeraInputDTO dtoHeladera) {
        Heladera nuevaHeladera = new Heladera(
                dtoHeladera.getDireccion(),
                dtoHeladera.getCoordenada(),
                dtoHeladera.getCapacidad(),
                dtoHeladera.getEstaActiva()
                //dtoHeladera.getIdViandas(), Ver como buscamos en el repo por id, deberia tener un Repo Viandas?? o como??
        );

        this.heladeraRepository.agregarHeladera(nuevaHeladera);

        HeladeraOutputDTO output = new HeladeraOutputDTO();

        //tiene sentido esto??
        output.setDireccion(nuevaHeladera.getDireccion());
        output.setCoordenada(nuevaHeladera.getCoordenada());
        output.setCapacidad(nuevaHeladera.getCapacidad());
        output.setEstaActiva(nuevaHeladera.getEstaActiva());


        return output;
    }

    @Override
    public HeladeraOutputDTO modificar(HeladeraInputDTO dto) {

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
    public void eliminar(HeladeraInputDTO dtoHeladera) { //long id
        Heladera nuevaHeladera = new Heladera(
                dtoHeladera.getDireccion(),
                dtoHeladera.getCoordenada(),
                dtoHeladera.getCapacidad(),
                dtoHeladera.getEstaActiva()
                //dtoHeladera.getIdViandas(), Ver como buscamos en el repo por id, deberia tener un Repo Viandas?? o como??
        );

        this.heladeraRepository.eliminarHeladera(nuevaHeladera);


    }
}


