package pt.fmbp.soiapbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    // SELECT usuario WHERE username = ?
    Usuario findByUsername(String username);
}
