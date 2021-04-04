package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.repository.IHoraAtencionRepository;
import pt.fmbp.soiapbackend.service.IHoraAtencionService;

@Service
public class HoraAtencionService implements IHoraAtencionService {

    @Autowired
    private IHoraAtencionRepository horaAtencionRepository;

    // Guardar un nuevo horario de atención
    @Override
    @Transactional
    public HoraAtencion guardarHoraAtencion (HoraAtencion horaAtencion) {
        return horaAtencionRepository.save(horaAtencion);
    }
}
