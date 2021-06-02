package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.fmbp.soiapbackend.entity.SesionTerapia;
import pt.fmbp.soiapbackend.service.ISesionTerapiaService;

@RestController
@RequestMapping("/api/sesion-terapia")
public class SesionTerapiaController {

    @Autowired
    private ISesionTerapiaService sesionTerapiaService;

    // Crear una nueva sesión de terapia
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
}
