package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.Pago;
import pt.fmbp.soiapbackend.exception.InvalidIdException;
import pt.fmbp.soiapbackend.exception.ResourceNotFoundException;
import pt.fmbp.soiapbackend.service.IPagoService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    // Crear un pago
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PostMapping("")
    public ResponseEntity<Pago> registerPayment(@Valid @RequestBody Pago pago) {
        Pago pagoRealizado = pagoService.registerPayment(pago);

        if (pagoRealizado != null) return new ResponseEntity<>(pagoRealizado, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // Obtener un pago específico
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/{idPago}")
    public ResponseEntity<Pago> getPaymentById(@PathVariable(value = "idPago") Long idPago) {
        if (idPago != null && idPago != 0) {
            Pago pagoBuscado = pagoService.getPaymentById(idPago);

            if (pagoBuscado != null) return new ResponseEntity<>(pagoBuscado, HttpStatus.OK);
            else throw new ResourceNotFoundException("No se ha encontrado un pago con el ID ingresado.");

        } else
            throw new InvalidIdException("EL ID del pago ingresado no es válido.");
    }

    // Actualizar un pago
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PutMapping("/update/{idPago}")
    public ResponseEntity<Pago> updatePayment(@PathVariable(value = "idPago") Long idPago, @Valid @RequestBody Pago pago) {
        if (idPago != null & idPago != 0 && pago != null) {
            return new ResponseEntity<>(pagoService.updatePayment(idPago, pago), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
