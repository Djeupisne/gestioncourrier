package tg.inseed.gestioncourrier.gestioncourrier.destinataitre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les destinataires.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface DestinataireRepository extends JpaRepository<Destinataire, Long> {
    // Méthodes personnalisées possibles : findByNomDeStructure, existsByAdresseEmail, etc.
}