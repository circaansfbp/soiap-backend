package pt.fmbp.soiapbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.Paciente;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
    // SELECT * FROM paciente WHERE nombre LIKE = %?%
    Page<Paciente> findAllByNombreContaining(String nombre, Pageable pageable);

    Page<Paciente> findAllByEstadoAndNombreContaining(String estado, String nombre, Pageable pageable);

    // SELECT * FROM paciente WHERE estado = ? (Retornar solo los pacientes activos)
    Page<Paciente> findAllByEstado(String estado, Pageable pageable);
}
