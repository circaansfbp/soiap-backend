package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.Paciente;
import pt.fmbp.soiapbackend.entity.SesionTerapia;
import pt.fmbp.soiapbackend.repository.IPacienteRepository;
import pt.fmbp.soiapbackend.service.IPacienteService;

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

    // Buscar paciente(s) por nombre entre todos los pacientes (activos e inactivos), paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesByName(String name, Pageable pageable) {
        if (name != null) return pacienteRepository.findAllByNombreContaining(name, pageable);
        else
            return null;
    }

    // Buscar paciente(s) por apellido entre todos los pacientes (activos e inactivos), paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesByLastname(String apellido, Pageable pageable) {
        if (apellido != null) return pacienteRepository.findAllByApellidoContaining(apellido, pageable);
        else
            return null;
    }

    // Buscar paciente(s) por nombre y apellido entre todos los pacientes (activos e inactivos), paginados
    @Override
    public Page<Paciente> getPacientesByNameAndLastname(String nombre, String apellido, Pageable pageable) {
        if (nombre != null && apellido != null) {
            return pacienteRepository.findAllByNombreContainingAndApellidoContaining(nombre, apellido, pageable);
        }
        else
            return null;
    }

    // Obtener uno o más pacientes activos por nombre, sin paginar
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> getPacientesActivosPorNombreSinPaginar(String name) {
        if (name != null) return pacienteRepository.findAllByEstadoAndNombreContaining("Activo", name);
        else
            return null;
    }

    // Obtener uno o más pacientes por nombre y estado, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesByNameAndEstado(String estado, String name, Pageable pageable) {
        if (name != null) return pacienteRepository.findAllByEstadoAndNombreContaining(estado, name, pageable);
        else
            return null;
    }

    // Obtener uno o más pacientes activos por su apellido, sin paginar
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> getPacientesActivosPorApellidoSinPaginar(String apellido) {
        if (apellido != null) return pacienteRepository.findAllByEstadoAndApellidoContaining("Activo", apellido);
        else
            return null;
    }

    // Obtener uno o más pacientes por su apellido y estado, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesPorApellidoAndEstado(String estado, String apellido, Pageable pageable) {
        if (apellido != null) return pacienteRepository.findAllByEstadoAndApellidoContaining(estado, apellido, pageable);
        else
            return null;
    }

    // Obtener uno o más pacientes activos por su nombre y apellido, sin paginar
    @Override
    @Transactional(readOnly = true)
    public List<Paciente> getPacientesActivosPorNombreApellidoSinPaginar(String nombre, String apellido) {
        if (nombre != null && apellido != null) return pacienteRepository.
                findAllByEstadoAndNombreContainingAndApellidoContaining("Activo", nombre, apellido);
        else
            return null;
    }

    // Obtener uno o más pacientes por su nombre, apellido y estado, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesPorNombreApellidoAndEstado(String estado, String nombre, String apellido, Pageable pageable) {
        if (nombre != null && apellido != null) return pacienteRepository.
                findAllByEstadoAndNombreContainingAndApellidoContaining(estado, nombre, apellido, pageable);
        else
            return null;
    }

    // Obtener un paciente específico por su ID
    @Override
    @Transactional(readOnly = true)
    public Paciente getPacienteById(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElse(null);
    }

    // Obtener todos los pacientes, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientes(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
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
