package tg.inseed.gestioncourrier.gestioncourrier.utilisateurs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface permettant d'accéder aux opérations CRUD sur les utilisateurs
 * 
 * @author KENKOU
 */

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
     /**
     * Recherche d'un utilisateur par son email
     * @param emailUtilisateur Email à rechercher
     * @return Utilisateur correspondant
     */
    Utilisateur findByEmailUtilisateur(String emailUtilisateur);

    /**
     * Vérifie l'existence d'un utilisateur par email
     * @param emailUtilisateur Email à vérifier
     * @return true si l'utilisateur existe, false sinon
     */
    boolean existsByEmailUtilisateur(String emailUtilisateur);
}
