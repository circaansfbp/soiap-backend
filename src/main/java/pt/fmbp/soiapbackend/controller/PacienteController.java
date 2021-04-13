package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.Paciente;
import pt.fmbp.soiapbackend.service.IPacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    // Actualizar los datos de un paciente
    @PutMapping("/update/{idPaciente}")
    public ResponseEntity<Paciente> updatePaciente (@RequestBody Paciente paciente, @PathVariable (value = "idPaciente") Long idPaciente) {
        Paciente updatedPatient = pacienteService.updatePaciente(paciente, idPaciente);

        if (updatedPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<>(updatedPatient, HttpStatus.CREATED);
    }
}
