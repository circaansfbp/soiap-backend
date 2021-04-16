package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.Paciente;
import pt.fmbp.soiapbackend.repository.IPacienteRepository;
import pt.fmbp.soiapbackend.service.IPacienteService;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    // Obtener un paciente específico por su ID
    @Override
    @Transactional(readOnly = true)
    public Paciente getPacienteById(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElse(null);
    }

    // Guardar un nuevo paciente
    @Override
    @Transactional
    public Paciente savePaciente(Paciente paciente) {
        if (paciente != null) return pacienteRepository.save(paciente);
        else
            return null;
    }

    // Obtener uno o más pacientes por un nombre
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> getPacienteByName(String name) {
        if (name != null) return pacienteRepository.findByNombre(name);
        else
            return null;
    }

    // Actualizar un paciente
    @Override
    @Transactional
    public Paciente updatePaciente(Paciente paciente, Long idPaciente) {
        Paciente pacienteToUpdate = getPacienteById(idPaciente);

        if (pacienteToUpdate != null) {
            // Actualiza el nombre del paciente
            if (!pacienteToUpdate.getNombre().equalsIgnoreCase(paciente.getNombre()))
                pacienteToUpdate.setNombre(paciente.getNombre());

            // Actualiza el apellido del paciente
            if (!pacienteToUpdate.getApellido().equalsIgnoreCase(paciente.getApellido()))
                pacienteToUpdate.setApellido(paciente.getApellido());

            // Actualiza número de teléfono
            if (!pacienteToUpdate.getTelefono().equals(paciente.getTelefono()))
                pacienteToUpdate.setTelefono(paciente.getTelefono());

            // Actualiza fecha de nacimiento
            if (!pacienteToUpdate.getFechaNacimiento().equals(paciente.getFechaNacimiento()))
                pacienteToUpdate.setFechaNacimiento(paciente.getFechaNacimiento());

            // Actualiza ocupación
            if (!pacienteToUpdate.getOcupacion().equalsIgnoreCase(paciente.getOcupacion()))
                pacienteToUpdate.setOcupacion(paciente.getOcupacion());

            // Actualiza institución
            if (!pacienteToUpdate.getInstitucion().equalsIgnoreCase(paciente.getInstitucion()))
                pacienteToUpdate.setInstitucion(paciente.getInstitucion());

            // Actualiza afiliación de salud
            if (!pacienteToUpdate.getAfiliacionSalud().equalsIgnoreCase(paciente.getAfiliacionSalud()))
                pacienteToUpdate.setAfiliacionSalud(paciente.getAfiliacionSalud());

            // Actualiza estado civil
            if (!pacienteToUpdate.getEstadoCivil().equalsIgnoreCase(paciente.getEstadoCivil()))
                pacienteToUpdate.setEstadoCivil(paciente.getEstadoCivil());

            // Actualiza familia nuclear
            if (!pacienteToUpdate.getFamiliaNuclear().equalsIgnoreCase(paciente.getFamiliaNuclear()))
                pacienteToUpdate.setFamiliaNuclear(paciente.getFamiliaNuclear());

            // AGREGAR ACTUALIZACIÓN DE HORAS DE ATENCIÓN

            pacienteRepository.save(pacienteToUpdate);
            return pacienteToUpdate;
        } else
            return null;
    }

    // Eliminar un paciente (lógica)
    @Override
    @Transactional
    public Paciente deletePaciente(Long idPaciente) {
        Paciente patientToDelete = getPacienteById(idPaciente);

        if (patientToDelete != null) {
            patientToDelete.setEstado("Inactivo");
            pacienteRepository.save(patientToDelete);
            return patientToDelete;
        }
        else
            return null;
    }
}
