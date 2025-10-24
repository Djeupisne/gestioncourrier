package tg.inseed.gestioncourrier.gestioncourrier.document;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les documents.
 * Ce service interagit avec {@link DocumentRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class DocumentService {

    @Autowired
    private final DocumentRepository documentRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param documentRepository Repository JPA pour l'entité Document
     */
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * Ajoute un nouveau document à la base de données.
     *
     * @param document Objet représentant le document à créer
     * @return Le document ajouté
     */
    public Document createDocument(Document document) {
        return documentRepository.save(document);
    }

    /**
     * Récupère la liste de tous les documents enregistrés.
     *
     * @return Liste complète des documents
     */
    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    /**
     * Récupère un document par son identifiant.
     *
     * @param id Identifiant unique du document
     * @return Le document correspondant
     * @throws RuntimeException si aucun document n’est trouvé
     */
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Document introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’un document existant.
     *
     * @param id Identifiant du document à modifier
     * @param newDocument Nouvelles données du document
     * @return Le document mis à jour
     */
    public Document updateDocument(Long id, Document newDocument) {
        Document document = getDocumentById(id);
        document.setNom(newDocument.getNom());
        document.setType(newDocument.getType());
        document.setCheminFichier(newDocument.getCheminFichier());
        document.setDateAjout(newDocument.getDateAjout());
        document.setCourrier(newDocument.getCourrier());
        return documentRepository.save(document);
    }

    /**
     * Supprime un document de la base de données.
     *
     * @param id Identifiant du document à supprimer
     * @throws RuntimeException si le document n’existe pas
     */
    public void deleteDocument(Long id) {
        if (!documentRepository.existsById(id)) {
            throw new RuntimeException("Le document avec l'id " + id + " n'existe pas.");
        }
        documentRepository.deleteById(id);
    }
}