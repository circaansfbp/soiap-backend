package pt.fmbp.soiapbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.Paciente;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    // SELECT * FROM paciente WHERE nombre = ?
    List<Paciente> findByNombre(String nombre);
}
