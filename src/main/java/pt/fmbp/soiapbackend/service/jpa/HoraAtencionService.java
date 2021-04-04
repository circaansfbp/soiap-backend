package pt.fmbp.soiapbackend.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.fmbp.soiapbackend.entity.HoraAtencion;
import pt.fmbp.soiapbackend.repository.IHoraAtencionRepository;
import pt.fmbp.soiapbackend.service.IHoraAtencionService;

import java.util.Date;
import java.util.List;

@Service
public class HoraAtencionService implements IHoraAtencionService {

    @Autowired
    private IHoraAtencionRepository horaAtencionRepository;

    // Guardar un nuevo horario de atención
    @Override
    @Transactional
    public HoraAtencion saveHoraAtencion(HoraAtencion horaAtencion) {
        horaAtencion.setEstado("Activo");
        horaAtencion.setDisponible(false);
        return horaAtencionRepository.save(horaAtencion);
    }

    // Obtener horarios de atencion por una fecha determinada
    @Override
    @Transactional(readOnly = true)
    public List<HoraAtencion> getHorasPorFecha(Date fechaAtencion) {
        return horaAtencionRepository.findByFechaAtencion(fechaAtencion);
    }

    // Actualizar una hora de atención
    @Override
    @Transactional
    public HoraAtencion updateHoraAtencion (HoraAtencion horaAtencion, Long idAtencion) {
        HoraAtencion hourToUpdate = horaAtencionRepository.findById(idAtencion).orElse(null);

        try {
            // Actualiza asistencia
            if (hourToUpdate.isAsistencia() != horaAtencion.isAsistencia()) hourToUpdate.setAsistencia(horaAtencion.isAsistencia());

            // Actualiza confirmación de la asistencia
            if (hourToUpdate.isConfirmaAsistencia() != horaAtencion.isConfirmaAsistencia()) hourToUpdate.setConfirmaAsistencia(
                    horaAtencion.isConfirmaAsistencia()
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

            // FALTA ACTUALIZACIÓN DE UN PAGO POR LA ATENCIÓN Y MANEJAR LA DISPONIBILIDAD DE UNA HORA DE ATENCIÓN

            horaAtencionRepository.save(hourToUpdate);
            return hourToUpdate;
        }
        catch (NullPointerException e) { return null; }
    }

    // Eliminar una hora de atención
    @Override
    public HoraAtencion deleteHoraAtencion(Long idAtencion) {
        HoraAtencion hourToDelete = horaAtencionRepository.findById(idAtencion).orElse(null);

        try {
            hourToDelete.setEstado("Inactivo");
            hourToDelete.setDisponible(true);

            horaAtencionRepository.save(hourToDelete);
            return hourToDelete;
        }
        catch (NullPointerException e) { return null; }
    }
}
