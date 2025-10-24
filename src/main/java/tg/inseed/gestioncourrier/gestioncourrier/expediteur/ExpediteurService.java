package tg.inseed.gestioncourrier.gestioncourrier.expediteur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les expéditeurs.
 * Ce service interagit avec {@link ExpediteurRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class ExpediteurService {

    @Autowired
    private final ExpediteurRepository expediteurRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param expediteurRepository Repository JPA pour l'entité Expediteur
     */
    public ExpediteurService(ExpediteurRepository expediteurRepository) {
        this.expediteurRepository = expediteurRepository;
    }

    /**
     * Ajoute un nouvel expéditeur à la base de données.
     *
     * @param expediteur Objet représentant l’expéditeur à créer
     * @return L’expéditeur ajouté
     */
    public Expediteur createExpediteur(Expediteur expediteur) {
        return expediteurRepository.save(expediteur);
    }

    /**
     * Récupère la liste de tous les expéditeurs enregistrés.
     *
     * @return Liste complète des expéditeurs
     */
    public List<Expediteur> getAllExpediteurs() {
        return expediteurRepository.findAll();
    }

    /**
     * Récupère un expéditeur par son identifiant.
     *
     * @param id Identifiant unique de l’expéditeur
     * @return L’expéditeur correspondant
     * @throws RuntimeException si aucun expéditeur n’est trouvé
     */
    public Expediteur getExpediteurById(Long id) {
        return expediteurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Expéditeur introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’un expéditeur existant.
     *
     * @param id Identifiant de l’expéditeur à modifier
     * @param newExpediteur Nouvelles données de l’expéditeur
     * @return L’expéditeur mis à jour
     */
    public Expediteur updateExpediteur(Long id, Expediteur newExpediteur) {
        Expediteur expediteur = getExpediteurById(id);
        expediteur.setNomDeStructure(newExpediteur.getNomDeStructure());
        expediteur.setNomDuResponsable(newExpediteur.getNomDuResponsable());
        expediteur.setAdresseGeographique(newExpediteur.getAdresseGeographique());
        expediteur.setAdresseEmail(newExpediteur.getAdresseEmail());
        expediteur.setTel(newExpediteur.getTel());
        expediteur.setTypeStructure(newExpediteur.getTypeStructure());
        expediteur.setDateEnregistrement(newExpediteur.getDateEnregistrement());
        return expediteurRepository.save(expediteur);
    }

    /**
     * Supprime un expéditeur de la base de données.
     *
     * @param id Identifiant de l’expéditeur à supprimer
     * @throws RuntimeException si l’expéditeur n’existe pas
     */
    public void deleteExpediteur(Long id) {
        if (!expediteurRepository.existsById(id)) {
            throw new RuntimeException("L’expéditeur avec l'id " + id + " n'existe pas.");
        }
        expediteurRepository.deleteById(id);
    }
}