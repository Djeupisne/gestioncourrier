package tg.inseed.gestioncourrier.gestioncourrier.expediteur;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les expéditeurs.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link ExpediteurService}.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-expediteurs")
public class ExpediteurController {

    @Autowired
    private final ExpediteurService expediteurService;

    /**
     * Constructeur avec injection du service
     *
     * @param expediteurService Service de gestion des expéditeurs
     */
    public ExpediteurController(ExpediteurService expediteurService) {
        this.expediteurService = expediteurService;
    }

    /**
     * Endpoint POST pour créer un nouvel expéditeur.
     *
     * @param expediteur Données de l’expéditeur à créer
     * @return L’expéditeur créé
     */
    @PostMapping("/ajouter")
    public Expediteur createExpediteur(@RequestBody Expediteur expediteur) {
        return expediteurService.createExpediteur(expediteur);
    }

    /**
     * Endpoint GET pour récupérer tous les expéditeurs.
     *
     * @return Liste de tous les expéditeurs enregistrés
     */
    @GetMapping("/liste")
    public List<Expediteur> getAllExpediteurs() {
        return expediteurService.getAllExpediteurs();
    }

    /**
     * Endpoint GET pour récupérer un expéditeur par son ID.
     *
     * @param id Identifiant de l’expéditeur
     * @return L’expéditeur correspondant
     */
    @GetMapping("/{id}")
    public Expediteur getExpediteurById(@PathVariable Long id) {
        return expediteurService.getExpediteurById(id);
    }

  /**
     * Endpoint PUT pour modifier un expéditeur existant.
     *
     * @param id Identifiant de l’expéditeur à modifier
     * @param expediteur Nouvelles données de l’expéditeur
     * @return L’expéditeur mis à jour
     */
    @PutMapping("/modify/{id}")
    public Expediteur updateExpediteur(@PathVariable Long id, @RequestBody Expediteur expediteur) {
        return expediteurService.updateExpediteur(id, expediteur);
    }

    /**
     * Endpoint DELETE pour supprimer un expéditeur.
     *
     * @param id Identifiant de l’expéditeur à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteExpediteur(@PathVariable Long id) {
        expediteurService.deleteExpediteur(id);
    }
}