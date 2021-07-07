package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.Anamnesis;
import pt.fmbp.soiapbackend.exception.InvalidIdException;
import pt.fmbp.soiapbackend.exception.ResourceNotFoundException;
import pt.fmbp.soiapbackend.service.IAnamnesisService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/anamnesis")
public class AnamnesisController {

    @Autowired
    private IAnamnesisService anamnesisService;

    // Actualizar una anamnesis
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("/update/{idAnamnesis}")
    public ResponseEntity<Anamnesis> updateAnamnesis(@PathVariable(value = "idAnamnesis") Long idAnamnesis,
                                                     @Valid @RequestBody Anamnesis anamnesis) {
        if (anamnesis != null) {
            return new ResponseEntity<>(anamnesisService.updateAnamnesis(anamnesis, idAnamnesis), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Eliminación lógica de una anamnesis
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("/delete/{idAnamnesis}")
    public ResponseEntity<Anamnesis> deleteAnamnesis(@PathVariable(value = "idAnamnesis") Long idAnamnesis) {
        if (idAnamnesis != null && idAnamnesis != 0) {
            Anamnesis anamnesisToDelete = anamnesisService.deleteAnamnesis(idAnamnesis);

            if (anamnesisToDelete != null) return new ResponseEntity<>(anamnesisToDelete, HttpStatus.OK);
            else throw new ResourceNotFoundException("No se ha encontrado una anamnesis con el ID proporcionado.");
        }
        else
            throw new InvalidIdException("El ID de la anamnesis no es válido.");
    }

    // Reintegración lógica de una anamnesis
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("/integrate/{idAnamnesis}")
    public ResponseEntity<Anamnesis> reintegrarAnamnesis(@PathVariable(value = "idAnamnesis") Long idAnamnesis) {
        if (idAnamnesis != 0 && idAnamnesis != null) {
            Anamnesis anamnesisToIntegrate = anamnesisService.reintegrarAnamnesis(idAnamnesis);

            if (anamnesisToIntegrate != null) return new ResponseEntity<>(anamnesisToIntegrate, HttpStatus.OK);
            else
                throw new ResourceNotFoundException("No se ha encontrado una anamnesis con el ID proporcionado.");
        }
        else
            throw new InvalidIdException("El ID de la anamnesis no es válido.");
    }
}
