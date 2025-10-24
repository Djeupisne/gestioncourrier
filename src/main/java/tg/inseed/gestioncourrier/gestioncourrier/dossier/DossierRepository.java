package tg.inseed.gestioncourrier.gestioncourrier.dossier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les dossiers.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {
    // Méthodes personnalisées possibles : findByNom, existsByNom, etc.
}