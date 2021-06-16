package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.SesionTerapia;
import pt.fmbp.soiapbackend.service.ISesionTerapiaService;

@RestController
@RequestMapping("/api/sesion-terapia")
public class SesionTerapiaController {

    @Autowired
    private ISesionTerapiaService sesionTerapiaService;

    // Obtener una sesión de terapia específica
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @GetMapping("/{idSesion}")
    public ResponseEntity<SesionTerapia> getSesionById(@PathVariable(value = "idSesion") Long idSesion) {
        if (idSesion != null && idSesion != 0) {
            return new ResponseEntity<>(sesionTerapiaService.getSesionTerapiaById(idSesion), HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Crear una nueva sesión de terapia
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PostMapping("")
    public ResponseEntity<SesionTerapia> createTherapySession(@RequestBody SesionTerapia sesionTerapia) {
        if (sesionTerapia != null) {
            System.out.println(sesionTerapia.toString());
            SesionTerapia created = sesionTerapiaService.createTherapySession(sesionTerapia);

            if (created != null) {
                return new ResponseEntity(created, HttpStatus.CREATED);
            }
            else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Actualizar una sesión de terapia
    @Secured("ROLE_PSICOLOGO_TRATANTE")
    @PutMapping("/update/{idSesion}")
    public ResponseEntity<SesionTerapia> updateSesionTerapia(@PathVariable(value = "idSesion") Long idSesion,
                                                             @RequestBody SesionTerapia sesionTerapia) {
        if (sesionTerapia != null && idSesion != null && idSesion != 0) {
            SesionTerapia sesionToUpdate = sesionTerapiaService.updateSesionTerapia(sesionTerapia, idSesion);

            if (sesionToUpdate != null) {
                return new ResponseEntity<>(sesionToUpdate, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
