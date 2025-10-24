package tg.inseed.gestioncourrier.gestioncourrier.affectation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service métier pour gérer les affectations.
 * Contient la logique métier et les appels au repository.
 */
@Service // Déclare cette classe comme un service Spring
public class AffectationService {

    @Autowired // Injection du repository
    private final AffectationRepository affectationRepository;

    public AffectationService(AffectationRepository affectationRepository) {
        this.affectationRepository = affectationRepository;
    }

    public Affectation createAffectation(Affectation affectation) {
        return affectationRepository.save(affectation);
    }

    public List<Affectation> getAllAffectations() {
        return affectationRepository.findAll();
    }

    public Affectation getAffectationById(Long id) {
        return affectationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Affectation introuvable avec l'id: " + id));
    }

    public Affectation updateAffectation(Long id, Affectation newAffectation) {
        Affectation affectation = getAffectationById(id);
        affectation.setCourrier(newAffectation.getCourrier());
        affectation.setUtilisateur(newAffectation.getUtilisateur());
        affectation.setDateAffectation(newAffectation.getDateAffectation());
        affectation.setMotif(newAffectation.getMotif());
        return affectationRepository.save(affectation);
    }

    public void deleteAffectation(Long id) {
        if (!affectationRepository.existsById(id)) {
            throw new RuntimeException("L’affectation avec l'id " + id + " n'existe pas.");
        }
        affectationRepository.deleteById(id);
    }
}
