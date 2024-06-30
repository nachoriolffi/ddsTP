package ar.edu.utn.frba.dds.services.imp;

import ar.edu.utn.frba.dds.dtos.inputs.TarjetaInputDTO;
import ar.edu.utn.frba.dds.dtos.outputs.TarjetaOutputDTO;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoTarjeta;
import ar.edu.utn.frba.dds.services.interfaces.ITarjetaService;

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
