package tg.inseed.gestioncourrier.gestioncourrier.regleDeConservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les règles de conservation.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface RegleDeConservationRepository extends JpaRepository<RegleDeConservation, Long> {
    // Méthodes personnalisées possibles : findByDureeConservation, findByDescription, etc.
}