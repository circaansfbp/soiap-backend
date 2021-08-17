package pt.fmbp.soiapbackend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.Paciente;

import java.util.List;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {

    // Retorna pacientes de acuerdo a su estado
    // SELECT * FROM paciente WHERE estado = ? (Retornar solo los pacientes activos)
    Page<Paciente> findAllByEstado(String estado, Pageable pageable);

    // Obtiene los pacientes activos por nombre, sin paginar
    // SELECT * FROM paciente WHERE nombre LIKE = %?% (Pacientes sin paginar)
    List<Paciente> findAllByEstadoAndNombreContaining(String estado, String nombre);

    // Obtiene todos los pacientes activos, por apellido, sin paginar
    // SELECT * FROM paciente WHERE estado = ? AND apellido LIKE = %?%
    List<Paciente> findAllByEstadoAndApellidoContaining(String estado, String apellido);

    // Obtiene todos los pacientes activos, por nombre y apellido, sin paginar
    // SELECT * FROM paciente WHERE estado = ? AND nombre LIKE = %?% AND apellido LIKE =%?%
    List<Paciente> findAllByEstadoAndNombreContainingAndApellidoContaining(String estado, String nombre, String apellido);
}
