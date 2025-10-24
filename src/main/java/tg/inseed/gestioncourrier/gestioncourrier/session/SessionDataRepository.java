package tg.inseed.gestioncourrier.gestioncourrier.session;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les sessions.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface SessionDataRepository extends JpaRepository<Session, Long> {
    // Méthodes personnalisées possibles : findByUtilisateur, findByDateConnexion, etc.
}