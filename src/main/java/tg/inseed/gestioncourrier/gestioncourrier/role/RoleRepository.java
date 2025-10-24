package tg.inseed.gestioncourrier.gestioncourrier.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les rôles.
 * Étend JpaRepository pour bénéficier des méthodes standards.
 * 
 * @author KENKOU
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Méthodes personnalisées possibles : findByLibelle, existsByLibelle, etc.
}