package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.exception.AppointmentConflictException;
import pt.fmbp.soiapbackend.exception.InvalidIdException;
import pt.fmbp.soiapbackend.exception.InvalidParameterException;
import pt.fmbp.soiapbackend.exception.ResourceNotFoundException;
import pt.fmbp.soiapbackend.service.IHoraAtencionService;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/atenciones")
public class HoraAtencionController {

    @Autowired
    private IHoraAtencionService horaAtencionService;

    // Crear un nuevo horario de atención
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PostMapping("")
    public ResponseEntity<HoraAtencion> createHoraAtencion (@Valid @RequestBody HoraAtencion horaAtencion) throws ParseException {
        if (horaAtencion != null) {
            HoraAtencion horaAtencionCreada = horaAtencionService.saveHoraAtencion(horaAtencion);

            // Si la hora y fecha de la atención provista coincide con una ya existente
            if (horaAtencionCreada == null) {
                throw new AppointmentConflictException("La hora o fecha de la atención ingresada ya se encuentra registrada.");
            }
            else
                return new ResponseEntity<>(horaAtencionCreada, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener un horario de atención específico
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/{idAtencion}")
    public ResponseEntity<HoraAtencion> getHoraAtencion (@PathVariable(value = "idAtencion") Long idAtencion) {
        if (idAtencion == null || idAtencion == 0) {
            throw new InvalidIdException("El ID del horario de atención ingresado no es válido.");
        }

        HoraAtencion horaAtencionEncontrada = horaAtencionService.getHoraAtencion(idAtencion);

        if (horaAtencionEncontrada != null) {
            return new ResponseEntity<>(horaAtencionEncontrada, HttpStatus.OK);
        }
         else throw new ResourceNotFoundException("El horario de atención no ha sido encontrado.");
    }

    // Obtener horarios de atención por fecha
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/get/{fecha}")
    public ResponseEntity<List<HoraAtencion>> getHoraAtencionPorFecha(@PathVariable(value = "fecha") String fechaAtencion) {
        if (fechaAtencion == null) throw new InvalidParameterException("Debe ingresar una fecha para realizar la búsqueda.");

        LocalDate localDate = LocalDate.parse(fechaAtencion);
        List<HoraAtencion> horarios = horaAtencionService.getHorasPorFecha(localDate);

        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    // Actualizar una hora de atención
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PutMapping("/update/{idAtencion}")
    public ResponseEntity<HoraAtencion> updateHoraAtencion (@Valid @RequestBody HoraAtencion horaAtencion,
                                                            @PathVariable(value = "idAtencion") Long idAtencion) {
        HoraAtencion horaAtencionActualizada = horaAtencionService.updateHoraAtencion(horaAtencion, idAtencion);

        if (horaAtencionActualizada == null ) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(horaAtencionActualizada, HttpStatus.CREATED);
    }

    // Eliminación física de una hora de atención
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @DeleteMapping("/delete/{idAtencion}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHoraAtencion (@PathVariable(value = "idAtencion") Long idAtencion) {
        if (idAtencion == null || idAtencion == 0) throw new InvalidIdException("El ID de la atención proporcionado no es válido.");

        horaAtencionService.deleteHoraAtencion(idAtencion);
    }
}
