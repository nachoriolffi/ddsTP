package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

import javax.persistence.NoResultException;

public class RepoColaborador extends RepoGenerico<Colaborador> {

    public static RepoColaborador INSTANCE = new RepoColaborador();

    public RepoColaborador() {
        super(Colaborador.class);
    }

    //nahue por las dudas no lo borro pero, en el repo base hay una funcion que hace esto. si queres ver el cambio en el codigo donde lo usas te cambie el nombre de la funcion a la que existe para que la veas :)
    public Colaborador buscarPorIdUsuario(Long id) {
        try {
            return entityManager()
                    .createQuery("SELECT c FROM Colaborador c WHERE c.usuario.id = :id", Colaborador.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
