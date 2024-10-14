package ar.edu.utn.frba.dds.models.repositories.implementaciones;

import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;

public class RepoColaborador extends RepoGenerico<Colaborador> {

    public static RepoColaborador INSTANCE = new RepoColaborador();

    public RepoColaborador() {
        super(Colaborador.class);
    }

    public Colaborador buscarPorIdUsuario(Long id) {
        return entityManager()
                .createQuery("select c from Colaborador c where c.usuario.id = :id", Colaborador.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
