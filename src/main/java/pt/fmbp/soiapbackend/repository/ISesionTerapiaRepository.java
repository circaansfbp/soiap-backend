package pt.fmbp.soiapbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.SesionTerapia;

@Repository
public interface ISesionTerapiaRepository extends JpaRepository<SesionTerapia, Long> {
}
