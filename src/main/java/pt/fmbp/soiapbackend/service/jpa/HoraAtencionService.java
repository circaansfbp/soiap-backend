package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.exception.AppointmentConflictException;
import pt.fmbp.soiapbackend.exception.ResourceDeletionNotPossibleException;
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
    public HoraAtencion updateHoraAtencion(HoraAtencion horaAtencion, Long idAtencion) {
        HoraAtencion hourToUpdate = getHoraAtencion(idAtencion);
        List<HoraAtencion> horasExistentes;

        // Actualiza asistencia
        if (hourToUpdate.getAsistencia() != horaAtencion.getAsistencia())
            hourToUpdate.setAsistencia(horaAtencion.getAsistencia());

        // Actualiza confirmación de la asistencia
        if (hourToUpdate.getConfirmaAsistencia() != horaAtencion.getConfirmaAsistencia())
            hourToUpdate.setConfirmaAsistencia(
                    horaAtencion.getConfirmaAsistencia()
            );

        // Actualiza fecha y hora de la atención
        if (!hourToUpdate.getHoraAtencion().equals(horaAtencion.getHoraAtencion()) || !hourToUpdate.getFechaAtencion().equals(horaAtencion.getFechaAtencion())) {
            horasExistentes = getHorasPorFecha(horaAtencion.getFechaAtencion());

            if (!horasExistentes.isEmpty()) {
                for (HoraAtencion horario : horasExistentes) {
                    System.out.println("Hora horario existente: " + horario.getHoraAtencion());
                    System.out.println("Hora horario que se desea actualizar: " + horaAtencion.getHoraAtencion());
                    if (horario.getHoraAtencion().equals(horaAtencion.getHoraAtencion())) {
                        System.out.println("Entré al if.");
                        return null;
                    }
                }
            }

            hourToUpdate.setHoraAtencion(horaAtencion.getHoraAtencion());
            hourToUpdate.setFechaAtencion(horaAtencion.getFechaAtencion());
        }

        // Actualizar el número de la sesión
        if (hourToUpdate.getNroConsulta() != horaAtencion.getNroConsulta()) hourToUpdate.setNroConsulta(
                horaAtencion.getNroConsulta()
        );

        // Actualizar el pago de una hora de atención
        if (hourToUpdate.getPago() != horaAtencion.getPago()) hourToUpdate.setPago(horaAtencion.getPago());

        horaAtencionRepository.save(hourToUpdate);
        return hourToUpdate;
    }

    // Eliminar una hora de atención (ELIMINACIÓN FÍSICA)
    @Override
    @Transactional
    public void deleteHoraAtencion(Long idAtencion) {

        HoraAtencion hourToDelete = getHoraAtencion(idAtencion);
        if (hourToDelete.getPago() != null)
            throw new ResourceDeletionNotPossibleException("El horario de atención fue pagado, por lo que no es posible eliminarlo.");

        if (hourToDelete.getAsistencia() != 0)
            throw new ResourceDeletionNotPossibleException("No es posible eliminar el horario, ya que su asistencia ya fue registrada");

        if (hourToDelete.getFechaAtencion().isBefore(LocalDate.now()))
            throw new ResourceDeletionNotPossibleException("No es posible eliminar un horario con fecha ya pasada.");

        horaAtencionRepository.deleteById(idAtencion);
    }

    // Envío de recordatorio de un horario de atención
    @Transactional
    @Scheduled(cron = "0 00 17 * * 1-5")
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
