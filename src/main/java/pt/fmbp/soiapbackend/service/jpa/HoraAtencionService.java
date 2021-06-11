package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.repository.IHoraAtencionRepository;
import pt.fmbp.soiapbackend.service.IEmailNotificationService;
import pt.fmbp.soiapbackend.service.IHoraAtencionService;

import javax.mail.MessagingException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class HoraAtencionService implements IHoraAtencionService {

    @Autowired
    private IHoraAtencionRepository horaAtencionRepository;

    @Autowired
    private IEmailNotificationService emailNotificationService;

    // Guardar un nuevo horario de atención
    @Override
    @Transactional
    public HoraAtencion saveHoraAtencion(HoraAtencion horaAtencion) {
        if (horaAtencion != null) {

            List<HoraAtencion> horasExistentes = getHorasPorFecha(horaAtencion.getFechaAtencion());

            for (HoraAtencion hora : horasExistentes) {
                if (hora.getHoraAtencion().equals(horaAtencion.getHoraAtencion())) {
                    return null;
                }
            }

            return horaAtencionRepository.save(horaAtencion);
        } else
            return null;
    }

    // Obtener un horario de atención específico
    @Override
    @Transactional
    public HoraAtencion getHoraAtencion(Long idAtencion) {
        return horaAtencionRepository.findById(idAtencion).orElse(null);
    }

    // Obtener horarios de atencion por una fecha determinada
    @Override
    @Transactional(readOnly = true)
    public List<HoraAtencion> getHorasPorFecha(LocalDate fechaAtencion) {
        return horaAtencionRepository.findByFechaAtencionOrderByHoraAtencionAsc(fechaAtencion);
    }

    // Actualizar una hora de atención
    @Override
    @Transactional
    public HoraAtencion updateHoraAtencion (HoraAtencion horaAtencion, Long idAtencion) {
        HoraAtencion hourToUpdate = getHoraAtencion(idAtencion);

        try {
            // Actualiza asistencia
            if (hourToUpdate.getAsistencia() != horaAtencion.getAsistencia()) hourToUpdate.setAsistencia(horaAtencion.getAsistencia());

            // Actualiza confirmación de la asistencia
            if (hourToUpdate.getConfirmaAsistencia() != horaAtencion.getConfirmaAsistencia()) hourToUpdate.setConfirmaAsistencia(
                    horaAtencion.getConfirmaAsistencia()
            );

            // Actualiza hora de la atención
            if (hourToUpdate.getHoraAtencion() != horaAtencion.getHoraAtencion()) hourToUpdate.setHoraAtencion(
                    horaAtencion.getHoraAtencion()
            );

            // Actualizar la fecha de la atención
            if (hourToUpdate.getFechaAtencion() != horaAtencion.getFechaAtencion()) hourToUpdate.setFechaAtencion(
                    horaAtencion.getFechaAtencion()
            );

            // Actualizar el número de la sesión
            if (hourToUpdate.getNroConsulta() != horaAtencion.getNroConsulta()) hourToUpdate.setNroConsulta(
                    horaAtencion.getNroConsulta()
            );

            // Actualizar el pago de una hora de atención
            if (hourToUpdate.getPago() != horaAtencion.getPago()) hourToUpdate.setPago(horaAtencion.getPago());

            horaAtencionRepository.save(hourToUpdate);
            return hourToUpdate;
        }
        catch (NullPointerException e) { return null; }
    }

    // Eliminar una hora de atención (ELIMINACIÓN FÍSICA)
    @Override
    @Transactional
    public void deleteHoraAtencion(Long idAtencion) {
        horaAtencionRepository.deleteById(idAtencion);
    }

    // Envío de recordatorio de un horario de atención
    @Transactional
    @Scheduled(cron = "0 34 13 * * 1-5")
    public void sendScheduledEmailNotification() throws MessagingException {
        // Para obtener el día actual
        LocalDate today = LocalDate.now();
        List<HoraAtencion> appointmentsToNotify;

        if (!today.getDayOfWeek().equals(DayOfWeek.FRIDAY)) appointmentsToNotify = getHorasPorFecha(today.plusDays(1));
        else
            appointmentsToNotify = getHorasPorFecha(today.plusDays(3));


        if (!appointmentsToNotify.isEmpty()) {
            this.emailNotificationService
                    .sendEmailNotification(appointmentsToNotify);
        }
    }
}
