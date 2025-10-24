package tg.inseed.gestioncourrier.gestioncourrier.decharge;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les décharges.
 * Ce service interagit avec {@link DechargeRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class DechargeService {

    @Autowired
    private final DechargeRepository dechargeRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param dechargeRepository Repository JPA pour l'entité Decharge
     */
    public DechargeService(DechargeRepository dechargeRepository) {
        this.dechargeRepository = dechargeRepository;
    }

    /**
     * Ajoute une nouvelle décharge à la base de données.
     *
     * @param decharge Objet représentant la décharge à créer
     * @return La décharge ajoutée
     */
    public Decharge createDecharge(Decharge decharge) {
        return dechargeRepository.save(decharge);
    }

    /**
     * Récupère la liste de toutes les décharges enregistrées.
     *
     * @return Liste complète des décharges
     */
    public List<Decharge> getAllDecharges() {
        return dechargeRepository.findAll();
    }

    /**
     * Récupère une décharge par son identifiant.
     *
     * @param id Identifiant unique de la décharge
     * @return La décharge correspondante
     * @throws RuntimeException si aucune décharge n’est trouvée
     */
    public Decharge getDechargeById(Long id) {
        return dechargeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Décharge introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’une décharge existante.
     *
     * @param id Identifiant de la décharge à modifier
     * @param newDecharge Nouvelles données de la décharge
     * @return La décharge mise à jour
     */
    public Decharge updateDecharge(Long id, Decharge newDecharge) {
        Decharge decharge = getDechargeById(id);
        decharge.setCourrier(newDecharge.getCourrier());
        decharge.setUtilisateur(newDecharge.getUtilisateur());
        decharge.setDateSignature(newDecharge.getDateSignature());
        decharge.setObservation(newDecharge.getObservation());
        return dechargeRepository.save(decharge);
    }

    /**
     * Supprime une décharge de la base de données.
     *
     * @param id Identifiant de la décharge à supprimer
     * @throws RuntimeException si la décharge n’existe pas
     */
    public void deleteDecharge(Long id) {
        if (!dechargeRepository.existsById(id)) {
            throw new RuntimeException("La décharge avec l'id " + id + " n'existe pas.");
        }
        dechargeRepository.deleteById(id);
    }
}