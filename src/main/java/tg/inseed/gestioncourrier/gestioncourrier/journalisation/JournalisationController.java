package tg.inseed.gestioncourrier.gestioncourrier.journalisation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour gérer les opérations CRUD sur les journalisations.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link JournalisationService}.
 * 
 * Il permet de tracer les actions réalisées par les utilisateurs dans le système.
 * 
 * Exemple : Connexion, création, modification, suppression.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-journalisations")
public class JournalisationController {

    @Autowired
    private final JournalisationService journalisationService;

    public JournalisationController(JournalisationService journalisationService) {
        this.journalisationService = journalisationService;
    }

    @PostMapping("/ajouter")
    public Journalisation createJournalisation(@RequestBody Journalisation journalisation) {
        return journalisationService.createJournalisation(journalisation);
    }

    @GetMapping("/list")
    public List<Journalisation> getAllJournalisations() {
        return journalisationService.getAllJournalisations();
    }

    @GetMapping("/{id}")
    public Journalisation getJournalisationById(@PathVariable Long id) {
        return journalisationService.getJournalisationById(id);
    }

    @PutMapping("/update/{id}")
    public Journalisation updateJournalisation(@PathVariable Long id, @RequestBody Journalisation journalisation) {
        return journalisationService.updateJournalisation(id, journalisation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteJournalisation(@PathVariable Long id) {
        journalisationService.deleteJournalisation(id);
    }
}