package tg.inseed.gestioncourrier.gestioncourrier.courriers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les courriers.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface CourrierRepository extends JpaRepository<Courrier, Long> {
    // Méthodes personnalisées possibles : findByStatut, findByExpediteur, etc.
}