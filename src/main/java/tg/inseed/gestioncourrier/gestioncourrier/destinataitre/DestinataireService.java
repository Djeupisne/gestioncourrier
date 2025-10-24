package tg.inseed.gestioncourrier.gestioncourrier.destinataitre;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les destinataires.
 * Ce service interagit avec {@link DestinataireRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class DestinataireService {

    @Autowired
    private final DestinataireRepository destinataireRepository;

    public DestinataireService(DestinataireRepository destinataireRepository) {
        this.destinataireRepository = destinataireRepository;
    }

    public Destinataire createDestinataire(Destinataire destinataire) {
        return destinataireRepository.save(destinataire);
    }

    public List<Destinataire> getAllDestinataires() {
        return destinataireRepository.findAll();
    }

    public Destinataire getDestinataireById(Long id) {
        return destinataireRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Destinataire introuvable avec l'id: " + id));
    }

    public Destinataire updateDestinataire(Long id, Destinataire newDestinataire) {
        Destinataire destinataire = getDestinataireById(id);
        destinataire.setNomDeStructure(newDestinataire.getNomDeStructure());
        destinataire.setNomDuResponsable(newDestinataire.getNomDuResponsable());
        destinataire.setAdresseGeographique(newDestinataire.getAdresseGeographique());
        destinataire.setAdresseEmail(newDestinataire.getAdresseEmail());
        destinataire.setTel(newDestinataire.getTel());
        return destinataireRepository.save(destinataire);
    }

    public void deleteDestinataire(Long id) {
        if (!destinataireRepository.existsById(id)) {
            throw new RuntimeException("Le destinataire avec l'id " + id + " n'existe pas.");
        }
        destinataireRepository.deleteById(id);
    }
}