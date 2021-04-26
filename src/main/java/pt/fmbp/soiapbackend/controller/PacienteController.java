package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.Paciente;
import pt.fmbp.soiapbackend.service.IPacienteService;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    // Crear un nuevo paciente
    @PostMapping("")
    public ResponseEntity<Paciente> savePaciente (@RequestBody Paciente paciente) {
        if (paciente != null) {
            Paciente patientToCreate = pacienteService.savePaciente(paciente);
            return new ResponseEntity<>(patientToCreate, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener un paciente por su ID
    @GetMapping("/{idPaciente}")
    public ResponseEntity<Paciente> getPaciente (@PathVariable(value = "idPaciente") Long idPaciente) {
        Paciente patientToReturn  = pacienteService.getPacienteById(idPaciente);

        if (patientToReturn != null) {
            return new ResponseEntity<>(patientToReturn, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes activos, por nombre, paginados
    @GetMapping("/get/by-name/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesPorNombre (@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                @RequestParam String nombre) {
        Page pageOfPatientsNamed = pacienteService.getPacientesByNameActivos(nombre, PageRequest.of(nroPagina, 5));

        if (!pageOfPatientsNamed.isEmpty()) return new ResponseEntity<>(pageOfPatientsNamed, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes activos, por nombre, sin paginar
    @GetMapping("/get/by-name")
    public ResponseEntity<List<Paciente>> getPacientesPorNombreSinPaginar (@RequestParam String nombre) {
        List patientsNamed = pacienteService.getPacientesActivosPorNombreSinPaginar(nombre);

        if (!patientsNamed.isEmpty()) return new ResponseEntity<>(patientsNamed, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener uno o más pacientes activos, por apellido, paginados
    @GetMapping("/get/by-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesPorApellido (@PathVariable(value = "pageNumber") Integer nroPagina,
                                                                               @RequestParam String apellido) {
        if (nroPagina != null && apellido != null) {
            Page pageOfPatientsWhoseLastnameIs = pacienteService.getPacientesActivosPorApellido(apellido, PageRequest.of(nroPagina, 5));

            if (!pageOfPatientsWhoseLastnameIs.isEmpty()) return new ResponseEntity<>(pageOfPatientsWhoseLastnameIs, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener uno o más pacientes activos, por apellido, sin paginar
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
    @GetMapping("/get/by-name-lastname/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientesPorNombreApellido(@PathVariable(value = "pageNumber") Integer nroPagina, @RequestParam String nombre,
                                                                        @RequestParam String apellido) {
        if (nroPagina != null && nombre != null && apellido != null) {
            Page patientsByNameAndLastname = pacienteService.getPacientesActivosPorNombreApellido(nombre, apellido, PageRequest.of(nroPagina, 5));

            if (!patientsByNameAndLastname.isEmpty()) return new ResponseEntity<>(patientsByNameAndLastname, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener uno o más pacientes activos, por nombre y apellido, sin paginar
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

    // Obtener todos los pacientes, paginados (DEVUELVE SOLO AQUELLOS CON ESTADO 'ACTIVO')
    @GetMapping("/get/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientes (@PathVariable(value = "pageNumber") Integer nroPagina) {
        Page pageOfPatients = pacienteService.getPacientesActivos(PageRequest.of(nroPagina, 5));

        return new ResponseEntity<>(pageOfPatients, HttpStatus.OK);
    }

    // Actualizar los datos de un paciente
    @PutMapping("/update/{idPaciente}")
    public ResponseEntity<Paciente> updatePaciente (@RequestBody Paciente paciente, @PathVariable (value = "idPaciente") Long idPaciente) {
        Paciente updatedPatient = pacienteService.updatePaciente(paciente, idPaciente);

        if (updatedPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(updatedPatient, HttpStatus.CREATED);
    }

    // Eliminación lógica de un paciente
    @PutMapping("delete/{idPaciente}")
    public ResponseEntity<Paciente> deletePaciente(@PathVariable(value = "idPaciente") Long idPaciente) {
        Paciente patientToDelete = pacienteService.deletePaciente(idPaciente);

        if (patientToDelete != null) return new ResponseEntity<>(patientToDelete, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
