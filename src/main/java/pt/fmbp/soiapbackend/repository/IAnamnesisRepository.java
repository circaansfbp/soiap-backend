package pt.fmbp.soiapbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pt.fmbp.soiapbackend.entity.Anamnesis;

@Repository
public interface IAnamnesisRepository extends JpaRepository<Anamnesis, Long> {
}
