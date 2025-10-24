package tg.inseed.gestioncourrier.gestioncourrier.decharge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les décharges.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface DechargeRepository extends JpaRepository<Decharge, Long> {
    // Méthodes personnalisées possibles : findByCourrier, findByUtilisateur, etc.
}