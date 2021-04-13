package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.Paciente;

public interface IPacienteService {
    Paciente updatePaciente(Paciente paciente, Long idPaciente);

    Paciente getPacienteById(Long idPaciente);
}
