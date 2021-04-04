package pt.fmbp.soiapbackend.service;

import pt.fmbp.soiapbackend.entity.HoraAtencion;

import java.util.Date;
import java.util.List;

public interface IHoraAtencionService {

    HoraAtencion saveHoraAtencion(HoraAtencion horaAtencion);

    List<HoraAtencion> getHorasPorFecha(Date fechaAtencion);

    HoraAtencion updateHoraAtencion (HoraAtencion horaAtencion, Long idAtencion);

    HoraAtencion deleteHoraAtencion (Long idAtencion);

}
