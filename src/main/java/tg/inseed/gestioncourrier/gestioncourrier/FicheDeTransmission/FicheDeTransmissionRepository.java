package tg.inseed.gestioncourrier.gestioncourrier.FicheDeTransmission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository pour accéder aux fiches de transmission.
 * Hérite des méthodes CRUD de JpaRepository.
 */
@Repository // Déclare ce composant comme un bean Spring
public interface FicheDeTransmissionRepository extends JpaRepository<FicheDeTransmission, Long> {
    // Méthodes personnalisées possibles : findByDateEnvoi, findByReference, etc.
}