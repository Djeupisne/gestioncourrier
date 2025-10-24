package tg.inseed.gestioncourrier.gestioncourrier.utilisateurs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Service métier pour la gestion des utilisateurs
 * 
 * @author KENKOU
 */
@Service
public class UtilisateurService {

    @Autowired
    private final UtilisateurRepository utilisateurRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param utilisateurRepository Repository JPA pour l'entité Utilisateur
     */
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Ajoute un nouvel utilisateur à la base de données.
     *
     * @param utilisateur Objet représentant l'utilisateur à créer
     * @return L'utilisateur ajouté
     */
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Récupère la liste de tous les utilisateurs enregistrés.
     *
     * @return Une liste contenant tous les utilisateurs
     */
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id Identifiant unique de l'utilisateur
     * @return L'utilisateur correspondant à l'identifiant
     * @throws RuntimeException si aucun utilisateur n'est trouvé
     */
    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param id Identifiant de l'utilisateur à mettre à jour
     * @param newUtilisateur Objet contenant les nouvelles données
     * @return L'utilisateur mis à jour
     */
    public Utilisateur updateUtilisateur(Long id, Utilisateur newUtilisateur) {
        Utilisateur utilisateur = getUtilisateurById(id);
        utilisateur.setNomUtilisateur(newUtilisateur.getNomUtilisateur());
        utilisateur.setPrenomUtilisateur(newUtilisateur.getPrenomUtilisateur());
        utilisateur.setEmailUtilisateur(newUtilisateur.getEmailUtilisateur());
        utilisateur.setMotDePasse(newUtilisateur.getMotDePasse());
        utilisateur.setRole(newUtilisateur.getRole());
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Supprime un utilisateur de la base de données.
     *
     * @param id Identifiant unique de l'utilisateur à supprimer
     * @throws RuntimeException si l'utilisateur n'existe pas
     */
    public void deleteUtilisateur(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("L'utilisateur avec l'id " + id + " n'existe pas.");
        }
        utilisateurRepository.deleteById(id);
    }
}
