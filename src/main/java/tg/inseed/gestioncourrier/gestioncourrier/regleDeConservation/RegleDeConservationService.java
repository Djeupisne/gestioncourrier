package tg.inseed.gestioncourrier.gestioncourrier.regleDeConservation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les règles de conservation.
 * Ce service interagit avec {@link RegleDeConservationRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class RegleDeConservationService {

    @Autowired
    private final RegleDeConservationRepository regleRepository;

    public RegleDeConservationService(RegleDeConservationRepository regleRepository) {
        this.regleRepository = regleRepository;
    }

    public RegleDeConservation createRegle(RegleDeConservation regle) {
        return regleRepository.save(regle);
    }

    public List<RegleDeConservation> getAllRegles() {
        return regleRepository.findAll();
    }

    public RegleDeConservation getRegleById(Long id) {
        return regleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Règle introuvable avec l'id: " + id));
    }

    public RegleDeConservation updateRegle(Long id, RegleDeConservation newRegle) {
        RegleDeConservation regle = getRegleById(id);
        regle.setDescription(newRegle.getDescription());
        regle.setDureeConservation(newRegle.getDureeConservation());
        return regleRepository.save(regle);
    }

    public void deleteRegle(Long id) {
        if (!regleRepository.existsById(id)) {
            throw new RuntimeException("La règle avec l'id " + id + " n'existe pas.");
        }
        regleRepository.deleteById(id);
    }
}
