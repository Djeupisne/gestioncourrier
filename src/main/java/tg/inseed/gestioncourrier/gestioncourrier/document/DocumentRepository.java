package tg.inseed.gestioncourrier.gestioncourrier.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les documents.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    // Méthodes personnalisées possibles : findByNom, findByCourrier, etc.
}