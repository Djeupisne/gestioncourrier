package tg.inseed.gestioncourrier.gestioncourrier.decharge;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les décharges.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link DechargeService}.
 * 
 * Une décharge est signée par un utilisateur au moment de la réception d’un courrier.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-decharges")
public class DechargeController {

    @Autowired
    private final DechargeService dechargeService;

    /**
     * Constructeur avec injection du service
     *
     * @param dechargeService Service de gestion des décharges
     */
    public DechargeController(DechargeService dechargeService) {
        this.dechargeService = dechargeService;
    }

    /**
     * Endpoint POST pour créer une nouvelle décharge.
     *
     * @param decharge Données de la décharge à créer
     * @return La décharge créée
     */
    @PostMapping("/ajouter")
    public Decharge createDecharge(@RequestBody Decharge decharge) {
        return dechargeService.createDecharge(decharge);
    }

    /**
     * Endpoint GET pour récupérer toutes les décharges.
     *
     * @return Liste de toutes les décharges enregistrées
     */
    @GetMapping("/list")
    public List<Decharge> getAllDecharges() {
        return dechargeService.getAllDecharges();
    }

    /**
     * Endpoint GET pour récupérer une décharge par son identifiant.
     *
     * @param id Identifiant unique de la décharge
     * @return La décharge correspondante
     */
    @GetMapping("/{id}")
    public Decharge getDechargeById(@PathVariable Long id) {
        return dechargeService.getDechargeById(id);
    }

    /**
     * Endpoint PUT pour mettre à jour une décharge existante.
     *
     * @param id Identifiant de la décharge à modifier
     * @param decharge Nouvelles données de la décharge
     * @return La décharge mise à jour
     */
    @PutMapping("/update/{id}")
    public Decharge updateDecharge(@PathVariable Long id, @RequestBody Decharge decharge) {
        return dechargeService.updateDecharge(id, decharge);
    }

    /**
     * Endpoint DELETE pour supprimer une décharge.
     *
     * @param id Identifiant de la décharge à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDecharge(@PathVariable Long id) {
        dechargeService.deleteDecharge(id);
    }
}