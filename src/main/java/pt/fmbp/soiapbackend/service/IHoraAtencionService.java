package pt.fmbp.soiapbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.fmbp.soiapbackend.entity.HoraAtencion;

import java.util.Date;
import java.util.List;

public interface IHoraAtencionService {

    // Guardar un horario de atención
    HoraAtencion saveHoraAtencion(HoraAtencion horaAtencion);

    // Obtener un horario de atención por su ID
    HoraAtencion getHoraAtencion(Long idAtencion);

    // Obtener todos los horarios para una fecha determinada
    List<HoraAtencion> getHorasPorFecha(Date fechaAtencion);

    // Actualizar una hora de atención
    HoraAtencion updateHoraAtencion (HoraAtencion horaAtencion, Long idAtencion);

    // Eliminar una hora de atención
    void deleteHoraAtencion (Long idAtencion);
}
