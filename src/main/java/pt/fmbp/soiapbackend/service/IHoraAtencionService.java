package pt.fmbp.soiapbackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pt.fmbp.soiapbackend.entity.HoraAtencion;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface IHoraAtencionService {

    // Guardar un horario de atención
    HoraAtencion saveHoraAtencion(HoraAtencion horaAtencion) throws ParseException;

    // Obtener un horario de atención por su ID
    HoraAtencion getHoraAtencion(Long idAtencion);

    // Obtener todos los horarios para una fecha determinada
    List<HoraAtencion> getHorasPorFecha(LocalDate fechaAtencion);

    // Actualizar una hora de atención
    HoraAtencion updateHoraAtencion (HoraAtencion horaAtencion, Long idAtencion);

    // Eliminar una hora de atención
    void deleteHoraAtencion (Long idAtencion);

    // Envío de recordatorio mediante e-mail
    void sendScheduledEmailNotification() throws MessagingException;
}
