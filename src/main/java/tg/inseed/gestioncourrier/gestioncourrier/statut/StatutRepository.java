package tg.inseed.gestioncourrier.gestioncourrier.statut;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les statuts.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface StatutRepository extends JpaRepository<Statut, Long> {
    // Méthodes personnalisées possibles : findByLibelleStatut, etc.
}
