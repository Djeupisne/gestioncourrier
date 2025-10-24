package tg.inseed.gestioncourrier.gestioncourrier.FicheDeTransmission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service métier pour gérer les fiches de transmission.
 * Contient la logique métier et les appels au repository.
 */
@Service // Déclare cette classe comme un service Spring
public class FicheDeTransmissionService {

    @Autowired // Injection du repository
    private final FicheDeTransmissionRepository ficheRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param ficheRepository Repository JPA pour FicheDeTransmission
     */
    public FicheDeTransmissionService(FicheDeTransmissionRepository ficheRepository) {
        this.ficheRepository = ficheRepository;
    }

    /**
     * Crée une nouvelle fiche de transmission
     *
     * @param fiche Données de la fiche
     * @return Fiche enregistrée
     */
    public FicheDeTransmission createFiche(FicheDeTransmission fiche) {
        return ficheRepository.save(fiche);
    }

    /**
     * Récupère toutes les fiches enregistrées
     *
     * @return Liste des fiches
     */
    public List<FicheDeTransmission> getAllFiches() {
        return ficheRepository.findAll();
    }

    /**
     * Récupère une fiche par son identifiant
     *
     * @param id Identifiant de la fiche
     * @return Fiche correspondante
     */
    public FicheDeTransmission getFicheById(Long id) {
        return ficheRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Fiche introuvable avec l'id: " + id));
    }

    /**
     * Met à jour une fiche existante
     *
     * @param id Identifiant de la fiche
     * @param newFiche Nouvelles données
     * @return Fiche mise à jour
     */
    public FicheDeTransmission updateFiche(Long id, FicheDeTransmission newFiche) {
        FicheDeTransmission fiche = getFicheById(id);

        // Mise à jour des champs principaux
        fiche.setDateEnvoi(newFiche.getDateEnvoi());
        fiche.setReference(newFiche.getReference());
        fiche.setObservation(newFiche.getObservation());

        // Mise à jour des cases à cocher
        fiche.setTresUrgent(newFiche.isTresUrgent());
        fiche.setUrgent(newFiche.isUrgent());
        fiche.setMenParler(newFiche.isMenParler());
        fiche.setPourAttribution(newFiche.isPourAttribution());
        fiche.setPourEtudeEtAvis(newFiche.isPourEtudeEtAvis());
        fiche.setPourDisposition(newFiche.isPourDisposition());
        fiche.setElementDeReponse(newFiche.isElementDeReponse());
        fiche.setFinRetour(newFiche.isFinRetour());
        fiche.setPourSuiteADonner(newFiche.isPourSuiteADonner());
        fiche.setPourVisaPrealable(newFiche.isPourVisaPrealable());
        fiche.setPourNecessaire(newFiche.isPourNecessaire());
        fiche.setNotePourLeDG(newFiche.isNotePourLeDG());
        fiche.setPourResumerSuccinct(newFiche.isPourResumerSuccinct());
        fiche.setNoteMinistre(newFiche.isNoteMinistre());
        fiche.setCopieA(newFiche.isCopieA());
        fiche.setMeRepresenter(newFiche.isMeRepresenter());
        fiche.setPourEtude(newFiche.isPourEtude());
        fiche.setLettreDeTransmission(newFiche.isLettreDeTransmission());
        fiche.setPourInformation(newFiche.isPourInformation());
        fiche.setBordeauEnvoi(newFiche.isBordeauEnvoi());
        fiche.setATouteFinUtile(newFiche.isATouteFinUtile());
        fiche.setEnInstance(newFiche.isEnInstance());
        fiche.setAClasser(newFiche.isAClasser());
        fiche.setCourrierReserve(newFiche.isCourrierReserve());

        return ficheRepository.save(fiche);
    }

    /**
     * Supprime une fiche de transmission
     *
     * @param id Identifiant de la fiche
     */
    public void deleteFiche(Long id) {
        if (!ficheRepository.existsById(id)) {
            throw new RuntimeException("La fiche avec l'id " + id + " n'existe pas.");
        }
        ficheRepository.deleteById(id);
    }
}