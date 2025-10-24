package tg.inseed.gestioncourrier.gestioncourrier.utilisateurs;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les utilisateurs.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link UtilisateurService}.
 *
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-utilisateurs")
public class UtilisateurController {
    @Autowired
    private final UtilisateurService utilisateurService;

    /**
     * Constructeur avec injection du service
     *
     * @param utilisateurService Service de gestion des utilisateurs
     */
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /**
     * Endpoint POST pour créer un nouvel utilisateur.
     *
     * @param utilisateur Données de l'utilisateur à créer
     * @return L'utilisateur créé
     */
    @PostMapping("/ajouter")
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    /**
     * Endpoint GET pour récupérer tous les utilisateurs.
     *
     * @return Liste de tous les utilisateurs enregistrés
     */
    @GetMapping("/list")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    /**
     * Endpoint GET pour récupérer un utilisateur par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur
     * @return L'utilisateur correspondant
     */
    @GetMapping("/{id}")
    public Utilisateur getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    /**
     * Endpoint PUT pour mettre à jour un utilisateur existant.
     *
     * @param id Identifiant de l'utilisateur à modifier
     * @param utilisateur Nouvelles données de l'utilisateur
     * @return L'utilisateur mis à jour
     */
    @PutMapping("/update/{id}")
    public Utilisateur updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    /**
     * Endpoint DELETE pour supprimer un utilisateur.
     *
     * @param id Identifiant de l'utilisateur à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
