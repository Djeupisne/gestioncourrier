package tg.inseed.gestioncourrier.gestioncourrier.regleDeConservation;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les règles de conservation.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link RegleDeConservationService}.
 * 
 * Permet de définir les durées de conservation des courriers et documents selon leur nature.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-reglesdeconservation")
public class RegleDeConservationController {

    @Autowired
    private final RegleDeConservationService regleService;

    public RegleDeConservationController(RegleDeConservationService regleService) {
        this.regleService = regleService;
    }

    @PostMapping("/ajouter")
    public RegleDeConservation createRegle(@RequestBody RegleDeConservation regle) {
        return regleService.createRegle(regle);
    }

    @GetMapping("/list")
    public List<RegleDeConservation> getAllRegles() {
        return regleService.getAllRegles();
    }

    @GetMapping("/{id}")
    public RegleDeConservation getRegleById(@PathVariable Long id) {
        return regleService.getRegleById(id);
    }

    @PutMapping("/update/{id}")
    public RegleDeConservation updateRegle(@PathVariable Long id, @RequestBody RegleDeConservation regle) {
        return regleService.updateRegle(id, regle);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRegle(@PathVariable Long id) {
        regleService.deleteRegle(id);
    }
}