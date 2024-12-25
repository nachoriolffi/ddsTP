package ar.edu.utn.frba.dds.services;

import ar.edu.utn.frba.dds.dtos.UsuarioDTO;
import ar.edu.utn.frba.dds.models.entities.colaborador.Colaborador;
import ar.edu.utn.frba.dds.models.entities.colaborador.TipoJuridiccion;
import ar.edu.utn.frba.dds.models.entities.contacto.Contacto;
import ar.edu.utn.frba.dds.models.entities.contacto.TipoContacto;
import ar.edu.utn.frba.dds.models.entities.usuario.Usuario;
import ar.edu.utn.frba.dds.models.entities.validador.ValidadorDeContrasenia;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoColaborador;
import ar.edu.utn.frba.dds.models.repositories.implementaciones.RepoUsuario;

import java.util.List;

public class UserService {

    RepoColaborador repoColaborador = RepoColaborador.INSTANCE;
    RepoUsuario repoUsuario = RepoUsuario.INSTANCE;

    public UsuarioDTO obtenerUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();
        Colaborador colaborador;

        switch (usuario.getRol()) {
            case ADMIN:
                usuarioDTO.setNombre(nombre);
                usuarioDTO.setApellido(apellido);
                usuarioDTO.setClave(usuario.getContrasenia());
                usuarioDTO.setEmail(usuario.getCorreoElectronico());
                usuarioDTO.setProvincia("-");
                usuarioDTO.setTelefono("-");
                usuarioDTO.setDireccion("-");
                usuarioDTO.setLocalidad("-");
                break;
            case COLABORADOR_HUMANO:
                colaborador = repoColaborador.buscarPorIdUsuario(usuario.getId());
                if (colaborador != null) {
                    usuarioDTO.setNombre(nombre);
                    usuarioDTO.setApellido(apellido);
                    usuarioDTO.setEmail(usuario.getCorreoElectronico());
                    usuarioDTO.setClave(usuario.getContrasenia());
                    if (colaborador.getDireccion() != null) {
                        //usuarioDTO.setProvincia(colaborador.getDireccion().getUbicacion().getProvincia().toString());
                        //usuarioDTO.setLocalidad(colaborador.getDireccion().getUbicacion().getLocalidad().toString());
                        usuarioDTO.setDireccion(colaborador.obtenerDireccion());
                    }
                    List<Contacto> contactosConTelefono = colaborador.getContacto().stream().filter(contacto -> !contacto.getTipoContacto().equals(TipoContacto.MAIL)).toList();
                    if (contactosConTelefono.size() > 0) {
                        usuarioDTO.setTelefono(contactosConTelefono.get(0).getDescripcion());
                    }
                }
                break;
            case COLABORADOR_JURIDICO:
                colaborador = repoColaborador.buscarPorIdUsuario(usuario.getId());
                if (colaborador != null) {
                    usuarioDTO.setRazonSocial(colaborador.getRazonSocial());
                    usuarioDTO.setTipoJurisdiccion(String.valueOf(colaborador.getTipoJuridiccion()));
                    usuarioDTO.setEmail(usuario.getCorreoElectronico());
                    usuarioDTO.setClave(usuario.getContrasenia());
                    if (colaborador.getDireccion() != null) {
                        //usuarioDTO.setProvincia(colaborador.getDireccion().getUbicacion().getProvincia().toString());
                        //usuarioDTO.setLocalidad(colaborador.getDireccion().getUbicacion().getLocalidad().toString());
                        usuarioDTO.setDireccion(colaborador.obtenerDireccion());
                    }
                    List<Contacto> contactosConTelefono = colaborador.getContacto().stream().filter(contacto -> !contacto.getTipoContacto().equals(TipoContacto.MAIL)).toList();
                    if (contactosConTelefono.size() > 0) {
                        usuarioDTO.setTelefono(contactosConTelefono.get(0).getDescripcion());
                    }
                }
                break;
            default:
                break;
        }
        return usuarioDTO;
    }

    public boolean validarContrasenia(String clave, String confirmarClave) {
        return clave != null && clave.equals(confirmarClave);
    }

    public boolean validarContraseniaSegura(String clave, Usuario usuario) {
        ValidadorDeContrasenia validadorDeContrasenia = new ValidadorDeContrasenia();
        validadorDeContrasenia.instanciarFormaValidar();
        return validadorDeContrasenia.validarContraseniaModificar(clave, usuario);
    }

    public void actualizarUsuario(Usuario usuario, UsuarioDTO usuarioDTO) {
        Colaborador colaborador;
        switch (usuario.getRol()) {
            case ADMIN:
                usuario.setNombre(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido());
                usuario.setCorreoElectronico(usuarioDTO.getEmail());
                usuario.setContrasenia(usuarioDTO.getClave());
                break;
            case COLABORADOR_HUMANO:
                colaborador = repoColaborador.buscarPorIdUsuario(usuario.getId());
                if (colaborador != null) {
                    colaborador.setNombre(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido());
                    //colaborador.getDireccion().getUbicacion().setProvincia(usuarioDTO.getProvincia());
                    //colaborador.getDireccion().getUbicacion().setLocalidad(usuarioDTO.getLocalidad());
                    //colaborador.setDireccion(usuarioDTO.getDireccion());
                    // TODO: Actualizar el teléfono cuando esté disponible en el DTO
                    // colaborador.setTelefono(usuarioDTO.getTelefono());
                    usuario.setCorreoElectronico(usuarioDTO.getEmail());
                    usuario.setContrasenia(usuarioDTO.getClave());
                    repoColaborador.modificar(colaborador);
                }
                break;
            case COLABORADOR_JURIDICO:
                colaborador = repoColaborador.buscarPorIdUsuario(usuario.getId());
                if (colaborador != null) {
                    colaborador.setRazonSocial(usuarioDTO.getRazonSocial());
                    colaborador.setTipoJuridiccion(TipoJuridiccion.valueOf(usuarioDTO.getTipoJurisdiccion()));
                    //colaborador.getDireccion().getUbicacion().setProvincia(usuarioDTO.getProvincia());
                    //colaborador.getDireccion().getUbicacion().setLocalidad(usuarioDTO.getLocalidad());
                    //colaborador.setDireccion(usuarioDTO.getDireccion());
                    // TODO: Actualizar el teléfono cuando esté disponible en el DTO
                    // colaborador.setTelefono(usuarioDTO.getTelefono());

                    usuario.setCorreoElectronico(usuarioDTO.getEmail());
                    usuario.setContrasenia(usuarioDTO.getClave());
                    repoColaborador.modificar(colaborador);
                }
                break;
            default:
                break;
        }
        repoUsuario.modificar(usuario);
    }


}
