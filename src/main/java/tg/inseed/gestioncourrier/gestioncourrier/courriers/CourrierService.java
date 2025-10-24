package tg.inseed.gestioncourrier.gestioncourrier.courriers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourrierService {

    @Autowired
    private final CourrierRepository courrierRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param courrierRepository Repository JPA pour l'entité Courrier
     */
    public CourrierService(CourrierRepository courrierRepository) {
        this.courrierRepository = courrierRepository;
    }

    /**
     * Ajoute un nouveau courrier à la base de données.
     *
     * @param courrier Objet représentant le courrier à créer
     * @return Le courrier ajouté
     */
    public Courrier createCourrier(Courrier courrier) {
        return courrierRepository.save(courrier);
    }

    /**
     * Récupère la liste de tous les courriers enregistrés.
     *
     * @return Liste complète des courriers
     */
    public List<Courrier> getAllCourriers() {
        return courrierRepository.findAll();
    }

    /**
     * Récupère un courrier par son identifiant.
     *
     * @param id Identifiant unique du courrier
     * @return Le courrier correspondant
     * @throws RuntimeException si aucun courrier n'est trouvé
     */
    public Courrier getCourrierById(Long id) {
        return courrierRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Courrier introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’un courrier existant.
     *
     * @param id Identifiant du courrier à modifier
     * @param newCourrier Nouvelles données du courrier
     * @return Le courrier mis à jour
     */
    public Courrier updateCourrier(Long id, Courrier newCourrier) {
        Courrier courrier = getCourrierById(id);
        courrier.setObjet(newCourrier.getObjet());
        courrier.setDateReception(newCourrier.getDateReception());
        courrier.setExpediteur(newCourrier.getExpediteur());
        courrier.setDestinataire(newCourrier.getDestinataire());
        courrier.setStatut(newCourrier.getStatut());
        return courrierRepository.save(courrier);
    }

    /**
     * Supprime un courrier de la base de données.
     *
     * @param id Identifiant du courrier à supprimer
     * @throws RuntimeException si le courrier n'existe pas
     */
    public void deleteCourrier(Long id) {
        if (!courrierRepository.existsById(id)) {
            throw new RuntimeException("Le courrier avec l'id " + id + " n'existe pas.");
        }
    
        courrierRepository.deleteById(id);
    }


}