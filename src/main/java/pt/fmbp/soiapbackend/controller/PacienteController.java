package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import pt.fmbp.soiapbackend.entity.Paciente;
import pt.fmbp.soiapbackend.exception.InvalidIdException;
import pt.fmbp.soiapbackend.exception.InvalidParameterException;
import pt.fmbp.soiapbackend.exception.ResourceNotFoundException;
import pt.fmbp.soiapbackend.service.IPacienteService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    // Crear un nuevo paciente
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PostMapping("")
    public ResponseEntity<Paciente> savePaciente (@Valid @RequestBody Paciente paciente) {
        if (paciente != null) {
            Paciente patientToCreate = pacienteService.savePaciente(paciente);
            return new ResponseEntity<>(patientToCreate, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener un paciente por su ID
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/{idPaciente}")
    public ResponseEntity<Paciente> getPaciente (@PathVariable(value = "idPaciente") Long idPaciente) {
        if (idPaciente == null || idPaciente == 0) throw new InvalidIdException("El ID del paciente ingresado no es válido.");

        Paciente patientToReturn  = pacienteService.getPacienteById(idPaciente);

        if (patientToReturn != null) {
            return new ResponseEntity<>(patientToReturn, HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("No se ha encontrado un paciente con el ID ingresado.");
    }

    // Obtener todos los pacientes activos, paginados
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientes (@PathVariable(value = "pageNumber") Integer nroPagina) {
        Page pageOfPatients = pacienteService.getPacientesActivos(PageRequest.of(nroPagina, 5));

        if (!pageOfPatients.isEmpty()) return new ResponseEntity<>(pageOfPatients, HttpStatus.OK);
        else
            throw new InvalidParameterException("El número de página proporcionado no es válido.");
    }

    // Obtener todos los pacientes inactivos, paginados
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/get/inactive/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesInactivos (@PathVariable(value = "pageNumber") Integer nroPagina) {
        Page inactivePatients = pacienteService.getPacientesInactivos(PageRequest.of(nroPagina, 5));

        if (!inactivePatients.isEmpty()) return new ResponseEntity<>(inactivePatients, HttpStatus.OK);
        else
            throw new InvalidParameterException("El número de página proporcionado no es válido.");
    }

    // Obtener uno o más pacientes activos, por nombre, sin paginar
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-name")
    public ResponseEntity<List<Paciente>> getPacientesPorNombreSinPaginar (@RequestParam String nombre) {
        if (nombre == null) throw new InvalidParameterException("Se debe ingresar el nombre del paciente.");

        List patientsNamed = pacienteService.getPacientesPorEstadoPorNombreSinPaginar("Activo", nombre);

        if (!patientsNamed.isEmpty()) return new ResponseEntity<>(patientsNamed, HttpStatus.OK);
        else
            throw new ResourceNotFoundException("No se ha encontrado un paciente con el nombre ingresado.");
    }

    // Obtener uno o más pacientes inactivos, por nombre, sin paginar
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/get/inactive/by-name")
    public ResponseEntity<List<Paciente>> getPacientesInactivosPorNombre(@RequestParam String nombre) {

        if (nombre == null) throw new InvalidParameterException("Se debe ingresar el nombre del paciente.");

        List inactivePatientsNamed = pacienteService.getPacientesPorEstadoPorNombreSinPaginar("Inactivo", nombre);

        if (!inactivePatientsNamed.isEmpty()) return new ResponseEntity<>(inactivePatientsNamed, HttpStatus.OK);
        else
            throw new ResourceNotFoundException("No se ha encontrado un paciente con el nombre ingresado.");
    }

    // Obtener uno o más pacientes inactivos, por apellido, sin paginar
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/get/inactive/by-lastname")
    public ResponseEntity<List<Paciente>> getPacientesInactivosPorApellido(@RequestParam String apellido) {
        if (apellido == null) throw new InvalidParameterException("Se debe ingresar un apellido.");

        List inactivePatients = pacienteService.getPacientesPorEstadoPorApellidoSinPaginar("Inactivo", apellido);

        if(!inactivePatients.isEmpty()) return new ResponseEntity<>(inactivePatients, HttpStatus.OK);
        else
            throw new ResourceNotFoundException("No se han encontrado pacientes con el apellido ingresado.");
    }

    // Obtener uno o más pacientes inactivos, por nombre y apellido, sin paginar
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/get/inactive/by-name-lastname")
    public ResponseEntity<List<Paciente>> getPacientesInactivosPorNombreApellido(@RequestParam String nombre,
                                                                                 @RequestParam String apellido) {
        if (nombre == null || apellido == null) throw new InvalidParameterException("Se debe ingresar el nombre y el apellido.");

        List inactivePatients = pacienteService.getPacientesPorEstadoPorNombreApellidoSinPaginar("Inactivo", nombre, apellido);

        if (!inactivePatients.isEmpty()) return new ResponseEntity<>(inactivePatients, HttpStatus.OK);
        else
            throw new ResourceNotFoundException("No se han encontrado pacientes con el nombre y apellido ingresado.");
    }

    // Obtener uno o más pacientes activos, por apellido, sin paginar
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-lastname")
    public ResponseEntity<List<Paciente>> getPacientesPorApellidoSinPaginar (@RequestParam String apellido) {
        if (apellido != null) {
            List patientsWhoseLastnameIs = pacienteService.getPacientesPorEstadoPorApellidoSinPaginar("Activo", apellido);

            if (!patientsWhoseLastnameIs.isEmpty()) return new ResponseEntity<>(patientsWhoseLastnameIs, HttpStatus.OK);
            else
                throw new ResourceNotFoundException("No se han encontrado pacientes con el apellido ingresado.");
        }
        else
            throw new InvalidParameterException("Se debe ingresar un apellido.");
    }

    // Obtener uno o más pacientes activos, por nombre y apellido, sin paginar
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-name-lastname")
    public ResponseEntity<List<Paciente>> getPacientesPorNombreApellidoSinPaginar(@RequestParam String nombre, @RequestParam String apellido) {
        if (nombre != null && apellido != null) {
            List<Paciente> patientsByNameAndLastname = pacienteService.getPacientesPorEstadoPorNombreApellidoSinPaginar("Activo", nombre, apellido);

            if (!patientsByNameAndLastname.isEmpty()) return new ResponseEntity<>(patientsByNameAndLastname, HttpStatus.OK);
            else
                throw new ResourceNotFoundException("No se han encontrado pacientes con el nombre y apellido ingresado.");
        }
        else
            throw new InvalidParameterException("Se debe ingresar el nombre y el apellido.");
    }

    // Actualizar los datos de un paciente
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PutMapping("/update/{idPaciente}")
    public ResponseEntity<Paciente> updatePaciente (@Valid @RequestBody Paciente paciente, @PathVariable (value = "idPaciente") Long idPaciente) {
        if (idPaciente == null || idPaciente == 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Paciente updatedPatient = pacienteService.updatePaciente(paciente, idPaciente);

        if (updatedPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(updatedPatient, HttpStatus.CREATED);
    }

    // Eliminación lógica de un paciente
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("delete/{idPaciente}")
    public ResponseEntity<Paciente> deletePaciente(@PathVariable(value = "idPaciente") Long idPaciente) {
        if (idPaciente == null || idPaciente == 0) {
            throw new InvalidIdException("Se debe ingresar un identificador válido.");
        }

        Paciente patientToDelete = pacienteService.deletePaciente(idPaciente);

        if (patientToDelete != null) return new ResponseEntity<>(patientToDelete, HttpStatus.OK);
        else
            throw new ResourceNotFoundException("El identificador del paciente ingresado no se encuentra registrado en el sistema.");
    }

    // Reintegrar un paciente a la consulta (de INACTIVO a ACTIVO)
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("integrate/{idPaciente}")
    public ResponseEntity<Paciente> reintegrarPaciente(@PathVariable(value = "idPaciente") Long idPaciente) {
        if (idPaciente == null || idPaciente == 0) throw new InvalidIdException("Se debe ingresar un identificador válido.");

        Paciente pacienteToIntegrate = pacienteService.reintegrarPaciente(idPaciente);

        if (pacienteToIntegrate == null) throw new ResourceNotFoundException("El identificador del paciente ingresado no se encuentra registrado en el sistema.");

        return new ResponseEntity<>(pacienteToIntegrate, HttpStatus.OK);
    }
}
