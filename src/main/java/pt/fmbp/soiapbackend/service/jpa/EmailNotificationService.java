package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.service.IEmailNotificationService;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class EmailNotificationService implements IEmailNotificationService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmailNotification(List<HoraAtencion> appointments) throws MessagingException {
        for (HoraAtencion horaAtencion : appointments) {
            if (horaAtencion.getPaciente().getEmail() != null && horaAtencion.getPaciente().getEmail() != " ") {
                SimpleMailMessage msg = new SimpleMailMessage();

                msg.setFrom("pt.fmbp.soiap2021@gmail.com");
                msg.setTo(horaAtencion.getPaciente().getEmail());
                msg.setSubject("Recordatorio hora de atención psicológica.");
                msg.setText(
                        "Hola " + horaAtencion.getPaciente().getNombre() + " " + horaAtencion.getPaciente().getApellido() + ".\n\n" +
                                "Le recordamos que mañana " +
                                horaAtencion.getFechaAtencion().toString() + " a las " + horaAtencion.getHoraAtencion().toString() +
                                " hrs. usted tiene un horario de atención psicológico agendado. Por favor, procure llegar a tiempo a su cita.\n\n" +
                                "Le saluda atentamente,\n" +
                                "--\n" +
                                "Dr. Patricio Lara Hadi.\n" +
                                "Psicólogo clínico y laboral.\n" +
                                "Vega de Saldías 824, Chillán."
                );

                mailSender.send(msg);
                System.out.println("Message sent!");
            }
        }
    }
}
