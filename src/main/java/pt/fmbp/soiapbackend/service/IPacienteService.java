package pt.fmbp.soiapbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.fmbp.soiapbackend.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    // Obtener todos los pacientes activos, paginados
    Page<Paciente> getPacientesActivos(Pageable pageable);

    // Obtener todos los pacientes inactivos, paginados
    Page<Paciente> getPacientesInactivos(Pageable pageable);

    // Obtener un paciente por su ID
    Paciente getPacienteById(Long idPaciente);

    // Crear un paciente
    Paciente savePaciente(Paciente paciente);

    // Obtener lista de pacientes activos por su nombre (sin paginar)
    List<Paciente> getPacientesPorEstadoPorNombreSinPaginar(String estado, String name);

    // Obtener todos los pacientes activos por su apellido, sin paginar
    List<Paciente> getPacientesPorEstadoPorApellidoSinPaginar(String estado, String apellido);

    // Obtener todos los pacientes activos por nombre y apellido, sin paginar
    List<Paciente> getPacientesPorEstadoPorNombreApellidoSinPaginar(String estado, String nombre, String apellido);

    // Actualizar pacientes
    Paciente updatePaciente(Paciente paciente, Long idPaciente);

    // Eliminar un paciente (eliminación lógica)
    Paciente deletePaciente(Long idPaciente);

    // Reintegrar un paciente (estado de "Inactivo" a "Activo")
    Paciente reintegrarPaciente(Long idPaciente);

}
