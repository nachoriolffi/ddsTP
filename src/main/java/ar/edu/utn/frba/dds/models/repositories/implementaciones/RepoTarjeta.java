package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import java.util.List;

public class RepoTarjeta extends RepoGenerico<Tarjeta> {

    public static RepoTarjeta INSTANCE = new RepoTarjeta();

    public RepoTarjeta() {
        super(Tarjeta.class);
    }

    public List<Tarjeta> buscarTarjetasColaborador(Long idColaborador){

        return entityManager()
                .createQuery("SELECT t FROM Tarjeta t WHERE t.colaboradorAsignador.id = :colaborador_id", Tarjeta.class)
                .setParameter("colaborador_id", idColaborador)
                .getResultList();

    }
}
