package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.entity.Paciente;
import pt.fmbp.soiapbackend.entity.SesionTerapia;
import pt.fmbp.soiapbackend.exception.ResourceDeletedException;
import pt.fmbp.soiapbackend.exception.ResourceDeletionNotPossibleException;
import pt.fmbp.soiapbackend.repository.IPacienteRepository;
import pt.fmbp.soiapbackend.service.IPacienteService;

import java.time.LocalDate;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private IPacienteRepository pacienteRepository;

    // Guardar un nuevo paciente
    @Override
    @Transactional
    public Paciente savePaciente(Paciente paciente) {
        if (paciente != null) return pacienteRepository.save(paciente);
        else
            return null;
    }

    // Obtener todos los pacientes activos (que no han sido eliminados), paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesActivos(Pageable pageable) {
        return pacienteRepository.findAllByEstado("Activo", pageable);
    }

    // Obtener todos los pacientes inactivos, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesInactivos(Pageable pageable) {
        return pacienteRepository.findAllByEstado("Inactivo", pageable);
    }

    // Obtener uno o más pacientes por nombre y estado, sin paginar
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> getPacientesPorEstadoPorNombreSinPaginar(String estado, String name) {
        if (name != null) return pacienteRepository.findAllByEstadoAndNombreContaining(estado, name);
        else
            return null;
    }

    // Obtener uno o más pacientes por apellido y estado, sin paginar
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> getPacientesPorEstadoPorApellidoSinPaginar(String estado, String apellido) {
        if (apellido != null) return pacienteRepository.findAllByEstadoAndApellidoContaining(estado, apellido);
        else
            return null;
    }

    // Obtener uno o más pacientes por su nombre, apellido y estado, sin paginar
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> getPacientesPorEstadoPorNombreApellidoSinPaginar(String estado, String nombre, String apellido) {
        if (nombre != null && apellido != null) return pacienteRepository.
                findAllByEstadoAndNombreContainingAndApellidoContaining(estado, nombre, apellido);
        else
            return null;
    }

    // Obtener un paciente específico por su ID
    @Override
    @Transactional(readOnly = true)
    public Paciente getPacienteById(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElse(null);
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
                pacienteToUpdate.setTelefono("+569" + paciente.getTelefono());

            // Actualiza el e-mail
            if (pacienteToUpdate.getEmail() == null && paciente.getEmail() != null) {
                pacienteToUpdate.setEmail(paciente.getEmail());
            }
            else if (pacienteToUpdate.getEmail() != null) {
                if (!pacienteToUpdate.getEmail().equals(paciente.getEmail())) {
                    pacienteToUpdate.setEmail(paciente.getEmail());
                }
            }

            // Actualiza fecha de nacimiento
            if (pacienteToUpdate.getFechaNacimiento() != paciente.getFechaNacimiento())
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

            // Actualiza la anamnesis del paciente
            if (pacienteToUpdate.getAnamnesis() != paciente.getAnamnesis()) pacienteToUpdate.setAnamnesis(
                    paciente.getAnamnesis()
            );

            // Actualiza la ficha de tratamiento del paciente
            if (pacienteToUpdate.getFichaTratamiento() != paciente.getFichaTratamiento()) pacienteToUpdate.setFichaTratamiento(
                    paciente.getFichaTratamiento()
            );

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

        if (patientToDelete.getEstado().equalsIgnoreCase("Inactivo")) {
            throw new ResourceDeletedException("El paciente ya se encuentra registrado como 'Inactivo'.");
        }

        if (!patientToDelete.getAtenciones().isEmpty()) {
            for (HoraAtencion atencion : patientToDelete.getAtenciones()) {
                if (atencion.getPago() == null && atencion.getFechaAtencion().isBefore(LocalDate.now()) && atencion.getAsistencia() == 1) {
                    throw new ResourceDeletionNotPossibleException("El paciente no puede ser eliminado. Presenta atenciones impagadas");
                }
            }
        }

        if (patientToDelete != null) {

            // Se setea como 'Inactivo' al paciente
            patientToDelete.setEstado("Inactivo");

            // Eliminación lógica de su anamnesis
            if (patientToDelete.getAnamnesis() != null) patientToDelete.getAnamnesis().setEstado("Inactivo");

            // Eliminación lógica de su ficha de tratamiento
            if (patientToDelete.getFichaTratamiento() != null) {
                patientToDelete.getFichaTratamiento().setEstado("Inactivo");

                // Se setean como inactivas las sesiones de terapia que tenga que el paciente
                if (patientToDelete.getFichaTratamiento().getSesionesDeTerapia().size() > 0 || patientToDelete.getFichaTratamiento().getSesionesDeTerapia() != null) {
                    for (SesionTerapia sesion : patientToDelete.getFichaTratamiento().getSesionesDeTerapia()) {
                        sesion.setEstado("Inactivo");
                    }
                }
            }

            // Se guardan los cambios y se retorna el paciente eliminado
            return pacienteRepository.save(patientToDelete);
        }
        else
            return null;
    }

    // Reintegrar un paciente a la consulta (de INACTIVO a ACTIVO)
    @Override
    @Transactional
    public Paciente reintegrarPaciente(Long idPaciente) {
        Paciente pacienteToIntegrate = getPacienteById(idPaciente);

        if (pacienteToIntegrate != null) {

            // Se reintegra el paciente
            pacienteToIntegrate.setEstado("Activo");

            // Si existe una anamnesis registrada, esta es reintegrada
            if (pacienteToIntegrate.getAnamnesis() != null) pacienteToIntegrate.getAnamnesis().setEstado("Activo");

            // Si existe una ficha de tratamiento registrada, esta es reintegrada
            if (pacienteToIntegrate.getFichaTratamiento() != null) {
                pacienteToIntegrate.getFichaTratamiento().setEstado("Activo");

                // Si existen sesiones de terapia registradas, estas son reintegradas
                if (pacienteToIntegrate.getFichaTratamiento().getSesionesDeTerapia() != null || pacienteToIntegrate.getFichaTratamiento().getSesionesDeTerapia().size() > 0) {
                    for (SesionTerapia sesion : pacienteToIntegrate.getFichaTratamiento().getSesionesDeTerapia()) {
                        sesion.setEstado("Activo");
                    }
                }
            }

            // Se guardan los cambios
            return pacienteRepository.save(pacienteToIntegrate);
        }
        else
            return null;
    }
}
