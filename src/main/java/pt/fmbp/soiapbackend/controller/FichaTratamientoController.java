package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.FichaTratamiento;
import pt.fmbp.soiapbackend.exception.InvalidIdException;
import pt.fmbp.soiapbackend.exception.ResourceNotFoundException;
import pt.fmbp.soiapbackend.service.jpa.FichaTratamientoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ficha-tratamiento")
public class FichaTratamientoController {

    @Autowired
    private FichaTratamientoService fichaTratamientoService;

    // Obtener una ficha de tratamiento por su ID
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/{idFichaTratamiento}")
    public ResponseEntity<FichaTratamiento> getFichaById(@PathVariable(value = "idFichaTratamiento") Long idFichaTratamiento) {

        if (idFichaTratamiento != null && idFichaTratamiento != 0) {
            FichaTratamiento fichaEncontrada = fichaTratamientoService.getFichaTratamientoById(idFichaTratamiento);

            if (fichaEncontrada != null) return new ResponseEntity<>(fichaEncontrada, HttpStatus.OK);
            else throw new ResourceNotFoundException("No se ha encontrado una ficha de tratamiento con el ID proporcionado.");

        } else throw new InvalidIdException("El ID de la ficha de tratamiento no es válido.");
    }

    // Actualizar una ficha de tratamiento
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("/update/{idFichaTratamiento}")
    public ResponseEntity<FichaTratamiento> updateFichaTratamiento (@PathVariable(value = "idFichaTratamiento") Long idFichaTratamiento,
                                                                    @Valid @RequestBody FichaTratamiento fichaTratamiento) {
        if (idFichaTratamiento != null && idFichaTratamiento != 0 && fichaTratamiento != null) {
            return new ResponseEntity<FichaTratamiento>(fichaTratamientoService.updateFichaTratamiento(fichaTratamiento, idFichaTratamiento), HttpStatus.CREATED);
        }
         else
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Eliminación lógica de una ficha de tratamiento
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("/delete/{idFichaTratamiento}")
    public ResponseEntity<FichaTratamiento> deleteFichaTratamiento(@PathVariable(value = "idFichaTratamiento") Long idFichaTratamiento) {
        if (idFichaTratamiento != null && idFichaTratamiento != 0) {
            FichaTratamiento fichaToDelete = fichaTratamientoService.deleteFichaTratamiento(idFichaTratamiento);

            if (fichaToDelete != null) return new ResponseEntity<>(fichaToDelete, HttpStatus.OK);
            else throw new ResourceNotFoundException("No se ha encontrado una ficha de tratamiento con el ID proporcionado.");
        }
        else
            throw new InvalidIdException("El ID de la ficha de tratamiento no es válido.");
    }

    // Reintegración lógica de una ficha de tratamiento
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("/integrate/{idFichaTratamiento}")
    public ResponseEntity<FichaTratamiento> reintegrarFichaTratamiento(@PathVariable(value = "idFichaTratamiento") Long idFichaTratamiento) {
        if (idFichaTratamiento != null && idFichaTratamiento != 0) {
            FichaTratamiento fichaToIntegrate = fichaTratamientoService.reintegrarFichaTratamiento(idFichaTratamiento);

            if (fichaToIntegrate != null) return new ResponseEntity<>(fichaToIntegrate, HttpStatus.OK);
            else
                throw new ResourceNotFoundException("No se ha encontrado una ficha de tratamiento con el ID proporcionado.");
        }
        else
            throw new InvalidIdException("El ID de la ficha de tratamiento no es válido.");
    }
}
