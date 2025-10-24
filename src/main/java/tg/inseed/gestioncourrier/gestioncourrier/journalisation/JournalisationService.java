package tg.inseed.gestioncourrier.gestioncourrier.journalisation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les journalisations.
 * Ce service interagit avec {@link JournalisationRepository} pour effectuer les opérations en base.
 * 
 * Il permet de tracer les actions des utilisateurs dans le système.
 * 
 * @author INSEED
 * @version 1.0
 * @since 10/2025
 */
@Service
public class JournalisationService {

    @Autowired
    private final JournalisationRepository journalisationRepository;

    public JournalisationService(JournalisationRepository journalisationRepository) {
        this.journalisationRepository = journalisationRepository;
    }

    public Journalisation createJournalisation(Journalisation journalisation) {
        return journalisationRepository.save(journalisation);
    }

    public List<Journalisation> getAllJournalisations() {
        return journalisationRepository.findAll();
    }

    public Journalisation getJournalisationById(Long id) {
        return journalisationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Journalisation introuvable avec l'id: " + id));
    }

    public Journalisation updateJournalisation(Long id, Journalisation newJournalisation) {
        Journalisation journalisation = getJournalisationById(id);
        journalisation.setAction(newJournalisation.getAction());
        journalisation.setDateAction(newJournalisation.getDateAction());
        journalisation.setUtilisateur(newJournalisation.getUtilisateur());
        return journalisationRepository.save(journalisation);
    }

    public void deleteJournalisation(Long id) {
        if (!journalisationRepository.existsById(id)) {
            throw new RuntimeException("La journalisation avec l'id " + id + " n'existe pas.");
        }
        journalisationRepository.deleteById(id);
    }

    // Méthode pour récupérer tous les logs
    public List<Journalisation> getAllLogs() {
        return journalisationRepository.findAll();
    }
}