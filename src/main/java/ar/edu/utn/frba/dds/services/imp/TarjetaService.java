package ar.edu.utn.frba.dds.services.imp;

import ar.edu.utn.frba.dds.dtos.TarjetaInputDTO;
import ar.edu.utn.frba.dds.dtos.TarjetaOutputDTO;
import ar.edu.utn.frba.dds.models.entities.heladera.Heladera;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoTarjeta;
import ar.edu.utn.frba.dds.services.ITarjetaService;

public class TarjetaService implements ITarjetaService {

    private IRepoTarjeta tarjetaRepository;
    private IRepoColaborador colaboradorRepository;

    public TarjetaService(IRepoTarjeta tarjetaRepository, IRepoColaborador colaboradorRepository) {//IRepoVulnerable vulnerableRepository
        this.tarjetaRepository = tarjetaRepository;
        this.colaboradorRepository = colaboradorRepository;
    }


    @Override
    public TarjetaOutputDTO crear(TarjetaInputDTO dto) {

//       Tarjeta nuevaTarjeta = new Tarjeta(
//                dto.getIdTarjeta(),
//                //dto.getIdPersonaAsociada(),
//                //colaboradorRepositorydto..buscar(getIdColaboradorAsociado())
//        );


        return null;
    }

    @Override
    public TarjetaOutputDTO modificar(TarjetaInputDTO dto) {
        return null;
    }

    @Override
    public void eliminar(TarjetaInputDTO dto) {
//        Tarjeta tarjeta = Tarjeta (
//                dto.getIdTarjeta(),
//               //dto.getIdPersonaAsociada(),   buscar por Id
//               //dto.getIdColaboradorAsociado() buscar por Id
//
                //this.tarjetaRepository.eliminarTarjeta(tarjeta);

//        )
    }
}
