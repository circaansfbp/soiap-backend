package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    Paciente updatePaciente(Paciente paciente, Long idPaciente);

    Paciente getPacienteById(Long idPaciente);

    Paciente savePaciente(Paciente paciente);

    List<Paciente> getPacienteByName(String name);

    Paciente deletePaciente(Long idPaciente);
}
