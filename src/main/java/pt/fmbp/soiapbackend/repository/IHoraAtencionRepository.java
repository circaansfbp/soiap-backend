package pt.fmbp.soiapbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import pt.fmbp.soiapbackend.entity.HoraAtencion;

import java.util.Date;
import java.util.List;

public interface IHoraAtencionRepository extends JpaRepository<HoraAtencion, Long> {
    // SELECT * FROM hora_atencion WHERE fecha_atencion = ?
    List<HoraAtencion> findByFechaAtencionOrderByHoraAtencionAsc(Date fechaAtencion);
}
