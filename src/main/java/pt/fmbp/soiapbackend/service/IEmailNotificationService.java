package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.HoraAtencion;

import javax.mail.MessagingException;
import java.util.List;

public interface IEmailNotificationService {

    // Envío de recordatorio de horario de atención
    void sendEmailNotification(List<HoraAtencion> appointments) throws MessagingException;
}
