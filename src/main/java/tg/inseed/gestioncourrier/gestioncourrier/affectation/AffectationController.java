package tg.inseed.gestioncourrier.gestioncourrier.affectation;

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
 * Contrôleur REST pour gérer les affectations.
 * Permet de créer, consulter, modifier et supprimer les affectations.
 */
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("/api-affectations") // Préfixe des routes
public class AffectationController {

    @Autowired // Injection du service
    private final AffectationService affectationService;

    public AffectationController(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    @PostMapping("/ajouter")
    public Affectation createAffectation(@RequestBody Affectation affectation) {
        return affectationService.createAffectation(affectation);
    }

    @GetMapping("/list")
    public List<Affectation> getAllAffectations() {
        return affectationService.getAllAffectations();
    }

    @GetMapping("/{id}")
    public Affectation getAffectationById(@PathVariable Long id) {
        return affectationService.getAffectationById(id);
    }

    @PutMapping("/update/{id}")
    public Affectation updateAffectation(@PathVariable Long id, @RequestBody Affectation affectation) {
        return affectationService.updateAffectation(id, affectation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAffectation(@PathVariable Long id) {
        affectationService.deleteAffectation(id);
    }
}