package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.Anamnesis;
import pt.fmbp.soiapbackend.service.IAnamnesisService;

@RestController
@RequestMapping("/api/anamnesis")
public class AnamnesisController {

    @Autowired
    private IAnamnesisService anamnesisService;

    // Actualizar una anamnesis
    @PutMapping("/update/{idAnamnesis}")
    public ResponseEntity<Anamnesis> updateAnamnesis(@PathVariable(value = "idAnamnesis") Long idAnamnesis,
                                                     @RequestBody Anamnesis anamnesis) {
        if (anamnesis != null) {
            return new ResponseEntity<>(anamnesisService.updateAnamnesis(anamnesis, idAnamnesis), HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Eliminación lógica de una anamnesis
    @PutMapping("/delete/{idAnamnesis}")
    public ResponseEntity<Anamnesis> deleteAnamnesis(@PathVariable(value = "idAnamnesis") Long idAnamnesis) {
        if (idAnamnesis != null && idAnamnesis != 0) {
            return new ResponseEntity<>(anamnesisService.deleteAnamnesis(idAnamnesis), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Reintegración lógica de una anamnesis
    @PutMapping("/integrate/{idAnamnesis}")
    public ResponseEntity<Anamnesis> reintegrarAnamnesis(@PathVariable(value = "idAnamnesis") Long idAnamnesis) {
        if (idAnamnesis != 0 && idAnamnesis != null) {
            Anamnesis anamnesisToIntegrate = anamnesisService.reintegrarAnamnesis(idAnamnesis);

            if (anamnesisToIntegrate != null) return new ResponseEntity<>(anamnesisToIntegrate, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
