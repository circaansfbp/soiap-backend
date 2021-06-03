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

    // Obtener una sesión de terapia específica
    @Override
    @Transactional(readOnly = true)
    public SesionTerapia getSesionTerapiaById(Long idSesion) {
        if (idSesion != null && idSesion != 0) {
            return sesionTerapiaRepository.findById(idSesion).orElse(null);
        }
        else return null;
    }

    // Modificar una sesión de terapia
    @Override
    @Transactional
    public SesionTerapia updateSesionTerapia(SesionTerapia sesionTerapia, Long idSesion) {
        if (idSesion != null && idSesion != 0) {
            SesionTerapia sesionToUpdate = getSesionTerapiaById(idSesion);

            if (sesionToUpdate != null) {
                if (sesionToUpdate.getNroSesion() != sesionTerapia.getNroSesion()) sesionToUpdate.setNroSesion(
                        sesionTerapia.getNroSesion()
                );

                if (!sesionToUpdate.getObservaciones().equalsIgnoreCase(sesionTerapia.getObservaciones())) sesionToUpdate.setObservaciones(
                        sesionTerapia.getObservaciones()
                );

                return sesionTerapiaRepository.save(sesionToUpdate);
            }
            else return null;
        }
        else return null;
    }

    // Eliminación lógica de las sesiones de terapia de un paciente.
    @Override
    @Transactional
    public void deletedSessions(SesionTerapia[] sesiones) {
        for (SesionTerapia sesion : sesiones) {
            SesionTerapia sessionToDelete = getSesionTerapiaById(sesion.getIdSesion());
            sessionToDelete.setEstado("Inactivo");
            sesionTerapiaRepository.save(sessionToDelete);
        }
    }
}
