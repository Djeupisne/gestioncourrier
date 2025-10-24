package tg.inseed.gestioncourrier.gestioncourrier.dossier;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les dossiers.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link DossierService}.
 * 
 * Exemple d’usage : regrouper les courriers par thème ou par service.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-dossiers")
public class DossierController {

    @Autowired
    private final DossierService dossierService;

    /**
     * Constructeur avec injection du service
     *
     * @param dossierService Service de gestion des dossiers
     */
    public DossierController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    /**
     * Endpoint POST pour créer un nouveau dossier.
     *
     * @param dossier Données du dossier à créer
     * @return Le dossier créé
     */
    @PostMapping("/ajouter")
    public Dossier createDossier(@RequestBody Dossier dossier) {
        return dossierService.createDossier(dossier);
    }

    /**
     * Endpoint GET pour récupérer tous les dossiers.
     *
     * @return Liste de tous les dossiers enregistrés
     */
    @GetMapping("/list")
    public List<Dossier> getAllDossiers() {
        return dossierService.getAllDossiers();
    }

    /**
     * Endpoint GET pour récupérer un dossier par son identifiant.
     *
     * @param id Identifiant unique du dossier
     * @return Le dossier correspondant
     */
    @GetMapping("/{id}")
    public Dossier getDossierById(@PathVariable Long id) {
        return dossierService.getDossierById(id);
    }

    /**
     * Endpoint PUT pour mettre à jour un dossier existant.
     *
     * @param id Identifiant du dossier à modifier
     * @param dossier Nouvelles données du dossier
     * @return Le dossier mis à jour
     */
    @PutMapping("/update/{id}")
    public Dossier updateDossier(@PathVariable Long id, @RequestBody Dossier dossier) {
        return dossierService.updateDossier(id, dossier);
    }

    /**
     * Endpoint DELETE pour supprimer un dossier.
     *
     * @param id Identifiant du dossier à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDossier(@PathVariable Long id) {
        dossierService.deleteDossier(id);
    }
}