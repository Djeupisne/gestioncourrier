package tg.inseed.gestioncourrier.gestioncourrier.typeCourrier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les types de courrier.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface TypeCourrierRepository extends JpaRepository<TypeCourrier, Long> {
    // Méthodes personnalisées possibles : findByLibelle, existsByLibelle, etc.
}