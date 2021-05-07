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

    @Override
    @Transactional
    public Pago registerPayment(Pago pago) {
        if (pago != null) return pagoRepository.save(pago);
        else
            return null;
    }
}
