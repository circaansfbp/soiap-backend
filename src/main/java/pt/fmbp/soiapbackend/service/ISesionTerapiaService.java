package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.SesionTerapia;

public interface ISesionTerapiaService {

    // Crear una sesión
    SesionTerapia createTherapySession(SesionTerapia sesionTerapia);

    // Obtener una sesión de terapia específica
    SesionTerapia getSesionTerapiaById(Long idSesion);

    // Actualizar una sesión de terapia
    SesionTerapia updateSesionTerapia(SesionTerapia sesionTerapia, Long idSesion);

    // Eliminación lógica de las sesiones de terapia del paciente
    void deletedSessions(SesionTerapia[] sesiones);
}
