package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.Pago;
import pt.fmbp.soiapbackend.repository.IPagoRepository;
import pt.fmbp.soiapbackend.service.IPagoService;

@Service
public class PagoService implements IPagoService {

    @Autowired
    private IPagoRepository pagoRepository;

    // Crear un nuevo pago (INNECESARIO?)
    @Override
    @Transactional
    public Pago registerPayment(Pago pago) {
        if (pago != null) return pagoRepository.save(pago);
        else
            return null;
    }

    // Obtener un pago específico
    @Override
    @Transactional(readOnly = true)
    public Pago getPaymentById(Long idPago) {
        if (idPago != null && idPago != 0) {
            Pago foundPayment = pagoRepository.findById(idPago).orElse(null);
            return foundPayment;
        } else
            return null;
    }

    // Actualizar un pago
    @Override
    @Transactional
    public Pago updatePayment(Long idPago, Pago pago) {
        if (idPago != null && idPago != 0 && pago != null) {
            Pago paymentToUpdate = getPaymentById(idPago);

            if (paymentToUpdate != null) {
                // Actualiza la afiliación de salud del paciente que está asociada al pago realizado
                if (!paymentToUpdate.getAfiliacionPaciente().equalsIgnoreCase(pago.getAfiliacionPaciente())) paymentToUpdate.setAfiliacionPaciente(
                        pago.getAfiliacionPaciente()
                );

                // Actualiza el medio de pago
                if (!paymentToUpdate.getMedioPago().equalsIgnoreCase(pago.getMedioPago())) paymentToUpdate.setMedioPago(
                        pago.getMedioPago()
                );

                // Actualiza el monto del pago
                if (!pago.getMedioPago().equalsIgnoreCase("Efectivo")) {
                    paymentToUpdate.setMontoPago(null);
                } else
                    if (paymentToUpdate.getMontoPago() != pago.getMontoPago()) paymentToUpdate.setMontoPago(
                        pago.getMontoPago()
                );

                // Actualiza la cantidad de horas asociadas al pago
                if (paymentToUpdate.getCantidadHorasPagadas() != pago.getCantidadHorasPagadas()) paymentToUpdate.setCantidadHorasPagadas(
                        pago.getCantidadHorasPagadas()
                );

                return pagoRepository.save(paymentToUpdate);

            } else
                return null;
        } else
            return null;
    }

    // Eliminación física de un pago
    @Override
    @Transactional
    public void deletePayment(Long idPago) {
        if (idPago != 0 && idPago != null) {
            pagoRepository.deleteById(idPago);
        }
    }
}
