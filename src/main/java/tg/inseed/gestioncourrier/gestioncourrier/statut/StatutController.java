package tg.inseed.gestioncourrier.gestioncourrier.statut;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les statuts.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link StatutService}.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-statuts")
public class StatutController {

    @Autowired
    private final StatutService statutService;

    /**
     * Constructeur avec injection du service
     *
     * @param statutService Service de gestion des statuts
     */
    public StatutController(StatutService statutService) {
        this.statutService = statutService;
    }

    /**
     * Endpoint POST pour créer un nouveau statut.
     *
     * @param statut Données du statut à créer
     * @return Le statut créé
     */
    @PostMapping("/ajouter")
    public Statut createStatut(@RequestBody Statut statut) {
        return statutService.createStatut(statut);
    }

    /**
     * Endpoint GET pour récupérer tous les statuts.
     *
     * @return Liste de tous les statuts enregistrés
     */
    @GetMapping("/lister")
    public List<Statut> getAllStatuts() {
        return statutService.getAllStatuts();
    }

    /**
     * Endpoint GET pour récupérer un statut par son ID.
     *
     * @param id Identifiant du statut
     * @return Le statut correspondant
     */
    @GetMapping("/{id}")
    public Statut getStatutById(@PathVariable Long id) {
        return statutService.getStatutById(id);
    }

    /**
     * Endpoint PUT pour modifier un statut existant.
     *
     * @param id Identifiant du statut à modifier
     * @param statut Nouvelles données du statut
     * @return Le statut mis à jour
     */
    @PutMapping("/modify/{id}")
    public Statut updateStatut(@PathVariable Long id, @RequestBody Statut statut) {
        return statutService.updateStatut(id, statut);
    }

    /**
     * Endpoint DELETE pour supprimer un statut.
     *
     * @param id Identifiant du statut à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteStatut(@PathVariable Long id) {
        statutService.deleteStatut(id);
    }
}