package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.SesionTerapia;
import pt.fmbp.soiapbackend.repository.ISesionTerapiaRepository;
import pt.fmbp.soiapbackend.service.ISesionTerapiaService;

@Service
public class SesionTerapiaService implements ISesionTerapiaService {

    @Autowired
    private ISesionTerapiaRepository sesionTerapiaRepository;

    // Crear una sesión de terapia
    @Override
    @Transactional
    public SesionTerapia createTherapySession(SesionTerapia sesionTerapia) {
        if (sesionTerapia != null) {
            return sesionTerapiaRepository.save(sesionTerapia);
        }
        else
            return null;
    }
}
