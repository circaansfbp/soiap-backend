package pt.fmbp.soiapbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.fmbp.soiapbackend.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente updatePaciente(Paciente paciente, Long idPaciente);

    Paciente getPacienteById(Long idPaciente);

    Paciente savePaciente(Paciente paciente);

    List<Paciente> getPacienteByName(String name);

    Page<Paciente> getPacientes(Pageable pageable);

    Paciente deletePaciente(Long idPaciente);
}
