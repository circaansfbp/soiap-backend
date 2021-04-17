package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // Obtener uno o más pacientes por un nombre
    @GetMapping("/get/{nombre}")
    public ResponseEntity<List<Paciente>> getPacientePorNombre (@PathVariable(value = "nombre") String nombre) {
        List<Paciente> pacientes = pacienteService.getPacienteByName(nombre);

        if (!pacientes.isEmpty()) return new ResponseEntity<>(pacientes, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener todos los pacientes, paginados
    @GetMapping("/get/page/{pageNumber}")
    public ResponseEntity<Page<Paciente>> getPacientes (@PathVariable(value = "pageNumber") Integer nroPagina) {
        Page pageOfPatients = pacienteService.getPacientes(PageRequest.of(nroPagina, 5));

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
