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

    Page<Paciente> getPacientesByName(String name, Pageable pageable);

    Page<Paciente> getPacientes(Pageable pageable);

    // Obtener lista de pacientes activos por su nombre (sin paginar)
    List<Paciente> getPacientesActivosPorNombreSinPaginar(String name);

    Page<Paciente> getPacientesActivos(Pageable pageable);

    Page<Paciente> getPacientesByNameActivos(String name, Pageable pageable);

    Paciente deletePaciente(Long idPaciente);

    // Obtener los horarios de atención asociados a un paciente
    Page<HoraAtencion> getHorariosAsociadosPaciente(Long idPaciente, Pageable pageable);
}
