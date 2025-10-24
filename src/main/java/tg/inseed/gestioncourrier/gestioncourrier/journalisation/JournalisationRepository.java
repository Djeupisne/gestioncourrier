package tg.inseed.gestioncourrier.gestioncourrier.journalisation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les journalisations.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */

@Repository
public interface JournalisationRepository extends JpaRepository<Journalisation, Long> {
    // Méthodes personnalisées possibles : findByUtilisateur, findByDateAction, etc.
}