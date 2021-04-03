package pt.fmbp.soiapbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.fmbp.soiapbackend.entity.HoraAtencion;

public interface HoraAtencionRepository extends JpaRepository<HoraAtencion, Long> { }
