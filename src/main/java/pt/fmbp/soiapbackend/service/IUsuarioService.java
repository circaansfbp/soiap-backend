package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.Usuario;

public interface IUsuarioService {

    /** Devuelve un usuario por su username */
    Usuario getUserByUsername(String username);
}
