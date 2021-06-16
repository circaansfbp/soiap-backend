package pt.fmbp.soiapbackend.service.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.Usuario;
import pt.fmbp.soiapbackend.repository.IUsuarioRepository;
import pt.fmbp.soiapbackend.service.IUsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService, IUsuarioService {
    // Para manejar errores
    private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private IUsuarioRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username);

        if (user == null) {
            logger.error("Error en el inicio de sesión: El usuario " + username + " no se encuentra registrado en el sistema.");
            throw new UsernameNotFoundException("Error en el inicio de sesión: El usuario " + username + " no se encuentra registrado en el sistema.");
        }

        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info(authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true,
                true, true, authorities);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
