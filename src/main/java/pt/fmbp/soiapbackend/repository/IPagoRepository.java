package pt.fmbp.soiapbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.Pago;

@Repository
public interface IPagoRepository extends JpaRepository<Pago, Long> {
}
