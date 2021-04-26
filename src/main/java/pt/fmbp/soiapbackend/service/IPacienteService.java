package pt.fmbp.soiapbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    // Actualizar pacientes
    Paciente updatePaciente(Paciente paciente, Long idPaciente);

    // Obtener un paciente por su ID
    Paciente getPacienteById(Long idPaciente);

    // Crear un paciente
    Paciente savePaciente(Paciente paciente);

    // Obtener todos los pacientes, por nombre
    Page<Paciente> getPacientesByName(String name, Pageable pageable);

    // Obtener todos los pacientes paginados
    Page<Paciente> getPacientes(Pageable pageable);

    // Obtener lista de pacientes activos por su nombre (sin paginar)
    List<Paciente> getPacientesActivosPorNombreSinPaginar(String name);

    // Obtener todos los pacientes activos por su nombre, paginados
    Page<Paciente> getPacientesByNameActivos(String name, Pageable pageable);

    // Obtener todos los pacientes activos por su apellido, sin paginar
    List<Paciente> getPacientesActivosPorApellidoSinPaginar(String apellido);

    // Obtener todos los pacientes activos por su apellido, paginados
    Page<Paciente> getPacientesActivosPorApellido(String apellido, Pageable pageable);

    // Obtener todos los pacientes activos por nombre y apellido, sin paginar
    List<Paciente> getPacientesActivosPorNombreApellidoSinPaginar(String nombre, String apellido);

    // Obtener todos los pacientes activos por nombre y apellido, paginados
    Page<Paciente> getPacientesActivosPorNombreApellido(String nombre, String apellido, Pageable pageable);

    // Obtener todos los pacientes activos, paginados
    Page<Paciente> getPacientesActivos(Pageable pageable);

    // Eliminar un paciente (eliminación lógica)
    Paciente deletePaciente(Long idPaciente);

}
