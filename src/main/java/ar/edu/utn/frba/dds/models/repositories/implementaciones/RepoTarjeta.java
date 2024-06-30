package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.tarjeta.Tarjeta;
import ar.edu.utn.frba.dds.models.repositories.interfaces.IRepoTarjeta;
import java.util.List;


public class RepoTarjeta implements IRepoTarjeta {

    private static RepoTarjeta instancia = null;
    private List<Tarjeta> tarjetas;

    public static RepoTarjeta getInstancia(){
        if(instancia == null){
            instancia = new RepoTarjeta();
        }
        return instancia;
    }
    public void agregarTarjeta(Tarjeta tarjeta) { this.tarjetas.add(tarjeta); }
    public void eliminarTarjeta(Tarjeta tarjeta) { this.tarjetas.remove(tarjeta); }
    public Tarjeta buscarTarjeta(Integer id) {return this.tarjetas.get(id);}
}
