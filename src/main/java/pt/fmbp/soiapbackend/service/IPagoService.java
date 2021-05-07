package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.Pago;

public interface IPagoService {

    // Registrar el pago de una o más atenciones
    Pago registerPayment(Pago pago);
}
