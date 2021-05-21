package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.service.IHoraAtencionService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/atenciones")
public class HoraAtencionController {

    @Autowired
    private IHoraAtencionService horaAtencionService;

    // Crear un nuevo horario de atención
    @PostMapping("")
    public ResponseEntity<HoraAtencion> createHoraAtencion (@RequestBody HoraAtencion horaAtencion) throws ParseException {
        if (horaAtencion != null) {
            HoraAtencion horaAtencionCreada = horaAtencionService.saveHoraAtencion(horaAtencion);

            // Si la hora y fecha de la atención provista coincide con una ya existente
            if (horaAtencionCreada == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
            else
                return new ResponseEntity<>(horaAtencionCreada, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener un horario de atención específico
    @GetMapping("/{idAtencion}")
    public ResponseEntity<HoraAtencion> getHoraAtencion (@PathVariable(value = "idAtencion") Long idAtencion) {
        HoraAtencion horaAtencionEncontrada = horaAtencionService.getHoraAtencion(idAtencion);

        if (horaAtencionEncontrada != null) {
            return new ResponseEntity<>(horaAtencionEncontrada, HttpStatus.OK);
        }
         else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener horarios de atención por fecha
    @GetMapping("/get/{fecha}")
    public ResponseEntity<List<HoraAtencion>> getHoraAtencionPorFecha(@PathVariable(value = "fecha") String fechaAtencion) {
        LocalDate localDate = LocalDate.parse(fechaAtencion);
        List<HoraAtencion> horarios = horaAtencionService.getHorasPorFecha(localDate);

        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    // Actualizar una hora de atención
    @PutMapping("/update/{idAtencion}")
    public ResponseEntity<HoraAtencion> updateHoraAtencion (@RequestBody HoraAtencion horaAtencion,
                                                            @PathVariable(value = "idAtencion") Long idAtencion) {
        HoraAtencion horaAtencionActualizada = horaAtencionService.updateHoraAtencion(horaAtencion, idAtencion);

        if (horaAtencionActualizada == null ) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(horaAtencionActualizada, HttpStatus.CREATED);
    }

    // Eliminación física de una hora de atención
    @DeleteMapping("/delete/{idAtencion}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHoraAtencion (@PathVariable(value = "idAtencion") Long idAtencion) {
        horaAtencionService.deleteHoraAtencion(idAtencion);
    }
}
