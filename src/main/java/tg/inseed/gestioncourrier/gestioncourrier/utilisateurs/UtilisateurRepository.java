package tg.inseed.gestioncourrier.gestioncourrier.utilisateurs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmailUtilisateur(String emailUtilisateur);
}