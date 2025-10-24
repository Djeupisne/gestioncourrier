package tg.inseed.gestioncourrier.gestioncourrier.statut;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les statuts.
 * Ce service interagit avec {@link StatutRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class StatutService {

    @Autowired
    private final StatutRepository statutRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param statutRepository Repository JPA pour l'entité Statut
     */
    public StatutService(StatutRepository statutRepository) {
        this.statutRepository = statutRepository;
    }

    /**
     * Ajoute un nouveau statut à la base de données.
     *
     * @param statut Objet représentant le statut à créer
     * @return Le statut ajouté
     */
    public Statut createStatut(Statut statut) {
        return statutRepository.save(statut);
    }

    /**
     * Récupère la liste de tous les statuts enregistrés.
     *
     * @return Liste complète des statuts
     */
    public List<Statut> getAllStatuts() {
        return statutRepository.findAll();
    }

    /**
     * Récupère un statut par son identifiant.
     *
     * @param id Identifiant unique du statut
     * @return Le statut correspondant
     * @throws RuntimeException si aucun statut n'est trouvé
     */
    public Statut getStatutById(Long id) {
        return statutRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Statut introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’un statut existant.
     *
     * @param id Identifiant du statut à modifier
     * @param newStatut Nouvelles données du statut
     * @return Le statut mis à jour
     */
    public Statut updateStatut(Long id, Statut newStatut) {
        Statut statut = getStatutById(id);
        statut.setLibelleStatut(newStatut.getLibelleStatut());
        return statutRepository.save(statut);
    }

    /**
     * Supprime un statut de la base de données.
     *
     * @param id Identifiant du statut à supprimer
     * @throws RuntimeException si le statut n'existe pas
     */
    public void deleteStatut(Long id) {
        if (!statutRepository.existsById(id)) {
            throw new RuntimeException("Le statut avec l'id " + id + " n'existe pas.");
        }
        statutRepository.deleteById(id);
    }
}