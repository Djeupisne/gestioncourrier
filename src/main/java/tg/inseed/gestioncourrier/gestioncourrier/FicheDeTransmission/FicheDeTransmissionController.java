package tg.inseed.gestioncourrier.gestioncourrier.FicheDeTransmission;

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
 * Contrôleur REST pour gérer les fiches de transmission.
 * Permet de créer, consulter, modifier et supprimer les fiches.
 */
@RestController // Indique que cette classe est un contrôleur REST
@RequestMapping("/api-fichestransmission") // Préfixe des routes
public class FicheDeTransmissionController {

    @Autowired // Injection du service
    private final FicheDeTransmissionService ficheService;

    /**
     * Constructeur avec injection du service
     *
     * @param ficheService Service métier pour les fiches
     */
    public FicheDeTransmissionController(FicheDeTransmissionService ficheService) {
        this.ficheService = ficheService;
    }

    /**
     * Endpoint POST pour créer une nouvelle fiche
     *
     * @param fiche Données reçues en JSON
     * @return Fiche créée
     */
    @PostMapping("/ajouter")
    public FicheDeTransmission createFiche(@RequestBody FicheDeTransmission fiche) {
        return ficheService.createFiche(fiche);
    }

    /**
     * Endpoint GET pour récupérer toutes les fiches
     *
     * @return Liste complète
     */
    @GetMapping("/list")
    public List<FicheDeTransmission> getAllFiches() {
        return ficheService.getAllFiches();
    }

    /**
     * Endpoint GET pour récupérer une fiche par ID
     *
     * @param id Identifiant unique
     * @return Fiche correspondante
     */
    @GetMapping("/{id}")
    public FicheDeTransmission getFicheById(@PathVariable Long id) {
        return ficheService.getFicheById(id);
    }

    /**
     * Endpoint PUT pour mettre à jour une fiche
     *
     * @param id Identifiant de la fiche
     * @param fiche Nouvelles données
     * @return Fiche mise à jour
     */
    @PutMapping("/update/{id}")
    public FicheDeTransmission updateFiche(@PathVariable Long id, @RequestBody FicheDeTransmission fiche) {
        return ficheService.updateFiche(id, fiche);
    }

    /**
     * Endpoint DELETE pour supprimer une fiche
     *
     * @param id Identifiant de la fiche
     */
    @DeleteMapping("/delete/{id}")
    public void deleteFiche(@PathVariable Long id) {
        ficheService.deleteFiche(id);
    }
}