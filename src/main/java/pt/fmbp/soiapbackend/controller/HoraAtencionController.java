package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.service.IHoraAtencionService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HoraAtencionController {

    @Autowired
    private IHoraAtencionService horaAtencionService;

    // Crear un nuevo horario de atención
    @PostMapping("/atenciones")
    public ResponseEntity<HoraAtencion> createHoraAtencion (@RequestBody HoraAtencion horaAtencion) {
        HoraAtencion horaAtencionCreada = horaAtencionService.saveHoraAtencion(horaAtencion);

        return new ResponseEntity<>(horaAtencionCreada, HttpStatus.CREATED);
    }

    // Obtener horarios de atención por fecha
    // MANEJAR EXCEPCIÓN
    @GetMapping("/atenciones/get/{fecha}")
    public ResponseEntity<List<HoraAtencion>> getHoraAtencionPorFecha(@PathVariable(value = "fecha") String fechaAtencion)
            throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fechaAtencion);
        List<HoraAtencion> horarios = horaAtencionService.getHorasPorFecha(date);

        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    // Actualizar una hora de atención
    @PutMapping("/atenciones/update/{idAtencion}")
    public ResponseEntity<HoraAtencion> updateHoraAtencion (@RequestBody HoraAtencion horaAtencion,
                                                            @PathVariable(value = "idAtencion") Long idAtencion) {
        HoraAtencion horaAtencionActualizada = horaAtencionService.updateHoraAtencion(horaAtencion, idAtencion);

        if (horaAtencionActualizada == null ) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(horaAtencionActualizada, HttpStatus.ACCEPTED);
    }

    // Eliminar una hora de atención
    @PutMapping("/atenciones/delete/{idAtencion}")
    public ResponseEntity<HoraAtencion> deleteHoraAtencion (@PathVariable(value = "idAtencion") Long idAtencion) {
        HoraAtencion horaAtencionEliminada = horaAtencionService.deleteHoraAtencion(idAtencion);

        if (horaAtencionEliminada == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(horaAtencionEliminada, HttpStatus.ACCEPTED);
    }
}
