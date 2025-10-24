package tg.inseed.gestioncourrier.gestioncourrier.direction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les directions.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface DirectionRepository extends JpaRepository<Direction, Long> {
    // Méthodes personnalisées possibles : findByNom, existsByNom, etc.
}