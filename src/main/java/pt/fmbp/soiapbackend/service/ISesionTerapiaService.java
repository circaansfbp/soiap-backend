package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.SesionTerapia;

public interface ISesionTerapiaService {

    // Crear una sesión
    SesionTerapia createTherapySession(SesionTerapia sesionTerapia);

    // Obtener una sesión de terapia específica
    SesionTerapia getSesionTerapiaById(Long idSesion);

    // Actualizar una sesión de terapia
    SesionTerapia updateSesionTerapia(SesionTerapia sesionTerapia, Long idSesion);
}
