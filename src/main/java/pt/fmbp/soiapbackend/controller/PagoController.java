package pt.fmbp.soiapbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.fmbp.soiapbackend.entity.Pago;
import pt.fmbp.soiapbackend.service.IPagoService;

@RestController
@RequestMapping("/api/pago")
public class PagoController {

    @Autowired
    private IPagoService pagoService;

    @PostMapping("")
    public ResponseEntity<Pago> registerPayment(@RequestBody Pago pago) {
        Pago pagoRealizado = pagoService.registerPayment(pago);

        if (pagoRealizado != null) return new ResponseEntity<>(pagoRealizado, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
