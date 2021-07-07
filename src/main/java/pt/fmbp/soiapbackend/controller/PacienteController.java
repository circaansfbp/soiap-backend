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

    // Buscar pacientes por nombre entre todos los pacientes (activos e inactivos), paginados
    /*@Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/all/by-name/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> searchInAllPatientsByName(@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                    @RequestParam String nombre) {
        Page pageOfPatientsNamed = pacienteService.getPacientesByName(nombre, PageRequest.of(nroPagina, 5));

        if (!pageOfPatientsNamed.isEmpty()) return new ResponseEntity<>(pageOfPatientsNamed, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Buscar pacientes por apellido entre todos los pacientes (activos e inactivos), paginados
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/all/by-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> searchInAllPatientsByLastname(@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                        @RequestParam String apellido) {
        Page pageOfPatientsWithLastname = pacienteService.getPacientesByLastname(apellido, PageRequest.of(nroPagina, 5));

        if(!pageOfPatientsWithLastname.isEmpty()) return new ResponseEntity<>(pageOfPatientsWithLastname, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Buscar pacientes por nombre y apellido entre todos los pacientes (activos e inactivos), paginados
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/all/by-name-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> searchInAllPatientsByNameAndLastname(@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                               @RequestParam String nombre, @RequestParam String apellido) {
        Page pageOfPatients = pacienteService.getPacientesByNameAndLastname(nombre, apellido, PageRequest.of(nroPagina, 5));

        if (!pageOfPatients.isEmpty()) return new ResponseEntity<>(pageOfPatients, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    // Obtener uno o más pacientes activos, por nombre, paginados
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-name/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesPorNombre (@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                @RequestParam String nombre) {
        if (nombre == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page pageOfPatientsNamed = pacienteService.getPacientesByNameAndEstado("Activo", nombre, PageRequest.of(nroPagina, 5));

        if (!pageOfPatientsNamed.isEmpty()) return new ResponseEntity<>(pageOfPatientsNamed, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes activos, por nombre, sin paginar
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-name")
    public ResponseEntity<List<Paciente>> getPacientesPorNombreSinPaginar (@RequestParam String nombre) {
        if (nombre == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List patientsNamed = pacienteService.getPacientesActivosPorNombreSinPaginar(nombre);

        if (!patientsNamed.isEmpty()) return new ResponseEntity<>(patientsNamed, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes inactivos, por nombre, paginados
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/get/inactive/by-name/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesInactivosPorNombre(@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                         @RequestParam String nombre) {
        if (nombre == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page pageOfPatients = pacienteService.getPacientesByNameAndEstado("Inactivo", nombre, PageRequest.of(nroPagina, 5));

        if (!pageOfPatients.isEmpty()) return new ResponseEntity<>(pageOfPatients, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes inactivos, por apellido, paginados
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/get/inactive/by-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesInactivosPorApellido(@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                           @RequestParam String apellido) {
        if (apellido == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page pageOfPatients = pacienteService.getPacientesPorApellidoAndEstado("Inactivo", apellido, PageRequest.of(nroPagina, 5));

        if(!pageOfPatients.isEmpty()) return new ResponseEntity<>(pageOfPatients, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes inactivos, por nombre y apellido, paginados
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/get/inactive/by-name-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesInactivosPorNombreApellido(@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                                 @RequestParam String nombre,
                                                                                 @RequestParam String apellido) {
        if (nombre == null || apellido == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Page pageOfPatienes = pacienteService.getPacientesPorNombreApellidoAndEstado("Inactivo", nombre, apellido,
                PageRequest.of(nroPagina, 5));

        if (!pageOfPatienes.isEmpty()) return new ResponseEntity<>(pageOfPatienes, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes activos, por apellido, paginados
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesPorApellido (@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                               @RequestParam String apellido) {
        if (nroPagina != null && apellido != null) {
            Page pageOfPatientsWhoseLastnameIs = pacienteService.getPacientesPorApellidoAndEstado("Activo", apellido, PageRequest.of(nroPagina, 5));

            if (!pageOfPatientsWhoseLastnameIs.isEmpty()) return new ResponseEntity<>(pageOfPatientsWhoseLastnameIs, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener uno o más pacientes activos, por apellido, sin paginar
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-lastname")
    public ResponseEntity<List<Paciente>> getPacientesPorApellidoSinPaginar (@RequestParam String apellido) {
        if (apellido != null) {
            List patientsWhoseLastnameIs = pacienteService.getPacientesActivosPorApellidoSinPaginar(apellido);

            if (!patientsWhoseLastnameIs.isEmpty()) return new ResponseEntity<>(patientsWhoseLastnameIs, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener uno o más pacientes activos, por nombre y apellido, paginados
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-name-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesPorNombreApellido(@PathVariable(value = "pageNumber") Integer nroPagina, @RequestParam String nombre,
                                                                        @RequestParam String apellido) {
        if (nroPagina != null && nombre != null && apellido != null) {
            Page patientsByNameAndLastname = pacienteService.getPacientesPorNombreApellidoAndEstado("Activo", nombre, apellido, PageRequest.of(nroPagina, 5));

            if (!patientsByNameAndLastname.isEmpty()) return new ResponseEntity<>(patientsByNameAndLastname, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener uno o más pacientes activos, por nombre y apellido, sin paginar
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/by-name-lastname")
    public ResponseEntity<List<Paciente>> getPacientesPorNombreApellidoSinPaginar(@RequestParam String nombre, @RequestParam String apellido) {
        if (nombre != null && apellido != null) {
            List<Paciente> patientsByNameAndLastname = pacienteService.getPacientesActivosPorNombreApellidoSinPaginar(nombre, apellido);

            if (!patientsByNameAndLastname.isEmpty()) return new ResponseEntity<>(patientsByNameAndLastname, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
