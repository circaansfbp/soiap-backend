package pt.fmbp.soiapbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.FichaTratamiento;

@Repository
public interface IFichaTratamientoRepository extends JpaRepository<FichaTratamiento, Long> {
}
