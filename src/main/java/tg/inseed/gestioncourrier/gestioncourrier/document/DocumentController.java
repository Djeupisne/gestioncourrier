package tg.inseed.gestioncourrier.gestioncourrier.document;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les documents.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link DocumentService}.
 * 
 * Un document peut être joint à un courrier ou archivé pour référence.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-documents")
public class DocumentController {

    @Autowired
    private final DocumentService documentService;

    /**
     * Constructeur avec injection du service
     *
     * @param documentService Service de gestion des documents
     */
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Endpoint POST pour créer un nouveau document.
     *
     * @param document Données du document à créer
     * @return Le document créé
     */
    @PostMapping("/ajouter")
    public Document createDocument(@RequestBody Document document) {
        return documentService.createDocument(document);
    }

    /**
     * Endpoint GET pour récupérer tous les documents.
     *
     * @return Liste de tous les documents enregistrés
     */
    @GetMapping("/list")
    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }

    /**
     * Endpoint GET pour récupérer un document par son identifiant.
     *
     * @param id Identifiant unique du document
     * @return Le document correspondant
     */
    @GetMapping("/{id}")
    public Document getDocumentById(@PathVariable Long id) {
        return documentService.getDocumentById(id);
    }

    /**
     * Endpoint PUT pour mettre à jour un document existant.
     *
     * @param id Identifiant du document à modifier
     * @param document Nouvelles données du document
     * @return Le document mis à jour
     */
    @PutMapping("/update/{id}")
    public Document updateDocument(@PathVariable Long id, @RequestBody Document document) {
        return documentService.updateDocument(id, document);
    }

    /**
     * Endpoint DELETE pour supprimer un document.
     *
     * @param id Identifiant du document à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
    }
}