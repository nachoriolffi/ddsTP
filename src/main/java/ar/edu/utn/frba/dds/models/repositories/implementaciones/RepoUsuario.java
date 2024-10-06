package ar.edu.utn.frba.dds.models.repositories.implementaciones;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;

import java.util.Optional;

public class RepoUsuario extends RepoGenerico<Usuario>{

    public static RepoUsuario INSTANCE = new RepoUsuario();
    public RepoUsuario() {
        super(Usuario.class);
    }
    // buscamos al usuario usando el mail
    public Optional<Usuario> buscarPorEmail(String email){
        return entityManager().createQuery("select e from Usuario e where e.correoElectronico = :email", Usuario.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    };

    // Colaborador
    // Tecnico
    // Administrador

}
