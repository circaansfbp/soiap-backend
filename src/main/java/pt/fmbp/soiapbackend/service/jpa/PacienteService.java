package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.entity.Paciente;
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

    // Obtener uno o más pacientes por un nombre, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesByName(String name, Pageable pageable) {
        if (name != null) return pacienteRepository.findAllByNombreContaining(name, pageable);
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

    // Obtener uno o más pacientes por nombre, que se encuentren con estado 'Activo', paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesByNameActivos(String name, Pageable pageable) {
        if (name != null) return pacienteRepository.findAllByEstadoAndNombreContaining("Activo", name, pageable);
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

    // Obtener uno o más pacientes activos por su apellido, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesActivosPorApellido(String apellido, Pageable pageable) {
        if (apellido != null) return pacienteRepository.findAllByEstadoAndApellidoContaining("Activo", apellido, pageable);
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

    // Obtener uno o más pacientes activos por su nombre y apellido, paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesActivosPorNombreApellido(String nombre, String apellido, Pageable pageable) {
        if (nombre != null && apellido != null) return pacienteRepository.
                findAllByEstadoAndNombreContainingAndApellidoContaining("Activo", nombre, apellido, pageable);
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

    // Obtener todos los pacientes activos (que no han sido eliminados), paginados
    @Override
    @Transactional(readOnly = true)
    public Page<Paciente> getPacientesActivos(Pageable pageable) {
        return pacienteRepository.findAllByEstado("Activo", pageable);
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
            patientToDelete.setEstado("Inactivo");
            pacienteRepository.save(patientToDelete);
            return patientToDelete;
        }
        else
            return null;
    }
}
