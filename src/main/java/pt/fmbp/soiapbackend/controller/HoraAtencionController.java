package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.service.IHoraAtencionService;

@RestController
@RequestMapping("/api")
public class HoraAtencionController {

    @Autowired
    private IHoraAtencionService horaAtencionService;

    // Crear un nuevo horario de atención
    @PostMapping("/atenciones")
    public ResponseEntity<HoraAtencion> crearHoraAtencion (@RequestBody HoraAtencion horaAtencion) {
        HoraAtencion horaAtencionCreada = horaAtencionService.guardarHoraAtencion(horaAtencion);
        return new ResponseEntity<>(horaAtencionCreada, HttpStatus.CREATED);
    }
}
