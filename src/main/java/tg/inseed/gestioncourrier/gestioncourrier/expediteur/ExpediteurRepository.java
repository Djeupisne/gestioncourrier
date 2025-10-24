package tg.inseed.gestioncourrier.gestioncourrier.expediteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les expéditeurs.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface ExpediteurRepository extends JpaRepository<Expediteur, Long> {
    // Méthodes personnalisées possibles : findByNomDeStructure, existsByAdresseEmail, etc.
}