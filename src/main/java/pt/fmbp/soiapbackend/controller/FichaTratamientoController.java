package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.FichaTratamiento;
import pt.fmbp.soiapbackend.service.jpa.FichaTratamientoService;

@RestController
@RequestMapping("/api/ficha-tratamiento")
public class FichaTratamientoController {

    @Autowired
    private FichaTratamientoService fichaTratamientoService;

    // Actualizar una ficha de tratamiento
    @PutMapping("/update/{idFichaTratamiento}")
    public ResponseEntity<FichaTratamiento> updateFichaTratamiento (@PathVariable(value = "idFichaTratamiento") Long idFichaTratamiento,
                                                                    @RequestBody FichaTratamiento fichaTratamiento) {
        if (idFichaTratamiento != null && idFichaTratamiento != 0 && fichaTratamiento != null) {
            return new ResponseEntity<FichaTratamiento>(fichaTratamientoService.updateFichaTratamiento(fichaTratamiento, idFichaTratamiento), HttpStatus.CREATED);
        }
         else
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
