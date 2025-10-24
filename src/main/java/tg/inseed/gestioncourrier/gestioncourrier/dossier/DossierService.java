package tg.inseed.gestioncourrier.gestioncourrier.dossier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les dossiers.
 * Ce service interagit avec {@link DossierRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class DossierService {

    @Autowired
    private final DossierRepository dossierRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param dossierRepository Repository JPA pour l'entité Dossier
     */
    public DossierService(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    /**
     * Ajoute un nouveau dossier à la base de données.
     *
     * @param dossier Objet représentant le dossier à créer
     * @return Le dossier ajouté
     */
    public Dossier createDossier(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    /**
     * Récupère la liste de tous les dossiers enregistrés.
     *
     * @return Liste complète des dossiers
     */
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }

    /**
     * Récupère un dossier par son identifiant.
     *
     * @param id Identifiant unique du dossier
     * @return Le dossier correspondant
     * @throws RuntimeException si aucun dossier n’est trouvé
     */
    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Dossier introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’un dossier existant.
     *
     * @param id Identifiant du dossier à modifier
     * @param newDossier Nouvelles données du dossier
     * @return Le dossier mis à jour
     */
    public Dossier updateDossier(Long id, Dossier newDossier) {
        Dossier dossier = getDossierById(id);
        dossier.setNom(newDossier.getNom());
        dossier.setDescription(newDossier.getDescription());
        return dossierRepository.save(dossier);
    }

    /**
     * Supprime un dossier de la base de données.
     *
     * @param id Identifiant du dossier à supprimer
     * @throws RuntimeException si le dossier n’existe pas
     */
    public void deleteDossier(Long id) {
        if (!dossierRepository.existsById(id)) {
            throw new RuntimeException("Le dossier avec l'id " + id + " n'existe pas.");
        }
        dossierRepository.deleteById(id);
    }
}