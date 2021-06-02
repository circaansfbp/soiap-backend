package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.SesionTerapia;

public interface ISesionTerapiaService {

    // Crear una sesión
    SesionTerapia createTherapySession(SesionTerapia sesionTerapia);
}
