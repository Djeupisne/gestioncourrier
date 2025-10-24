package tg.inseed.gestioncourrier.gestioncourrier.archive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les archives.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface ArchiveRepository extends JpaRepository<Archive, Long> {
    // Méthodes personnalisées possibles : findByCourrier, findByDateArchivage, etc.
}