package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import pt.fmbp.soiapbackend.entity.Pago;
import pt.fmbp.soiapbackend.service.IPagoService;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    // Crear un pago
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PostMapping("")
    public ResponseEntity<Pago> registerPayment(@RequestBody Pago pago) {
        Pago pagoRealizado = pagoService.registerPayment(pago);

        if (pagoRealizado != null) return new ResponseEntity<>(pagoRealizado, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener un pago específico
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @GetMapping("/{idPago}")
    public ResponseEntity<Pago> getPaymentById(@PathVariable(value = "idPago") Long idPago) {
        if (idPago != null && idPago != 0) {
            return new ResponseEntity<>(pagoService.getPaymentById(idPago), HttpStatus.FOUND);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Actualizar un pago
    @Secured({"ROLE_PSICOLOGO_TRATANTE", "ROLE_COLABORADOR"})
    @PutMapping("/update/{idPago}")
    public ResponseEntity<Pago> updatePayment(@PathVariable(value = "idPago") Long idPago, @RequestBody Pago pago) {
        if (idPago != null & idPago != 0 && pago != null) {
            return new ResponseEntity<>(pagoService.updatePayment(idPago, pago), HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
