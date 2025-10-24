package tg.inseed.gestioncourrier.gestioncourrier.archive;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les archives.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link ArchiveService}.
 * 
 * Une archive est liée à un courrier et contient des informations sur sa date d’archivage et son emplacement.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-archives")
public class ArchiveController {

    @Autowired
    private final ArchiveService archiveService;

    /**
     * Constructeur avec injection du service
     *
     * @param archiveService Service de gestion des archives
     */
    public ArchiveController(ArchiveService archiveService) {
        this.archiveService = archiveService;
    }

    /**
     * Endpoint POST pour créer une nouvelle archive.
     *
     * @param archive Données de l’archive à créer
     * @return L’archive créée
     */
    @PostMapping("/ajouter")
    public Archive createArchive(@RequestBody Archive archive) {
        return archiveService.createArchive(archive);
    }

    /**
     * Endpoint GET pour récupérer toutes les archives.
     *
     * @return Liste de toutes les archives enregistrées
     */
    @GetMapping("/list")
    public List<Archive> getAllArchives() {
        return archiveService.getAllArchives();
    }

    /**
     * Endpoint GET pour récupérer une archive par son identifiant.
     *
     * @param id Identifiant unique de l’archive
     * @return L’archive correspondante
     */
    @GetMapping("/{id}")
    public Archive getArchiveById(@PathVariable Long id) {
        return archiveService.getArchiveById(id);
    }

    /**
     * Endpoint PUT pour mettre à jour une archive existante.
     *
     * @param id Identifiant de l’archive à modifier
     * @param archive Nouvelles données de l’archive
     * @return L’archive mise à jour
     */
    @PutMapping("/update/{id}")
    public Archive updateArchive(@PathVariable Long id, @RequestBody Archive archive) {
        return archiveService.updateArchive(id, archive);
    }

    /**
     * Endpoint DELETE pour supprimer une archive.
     *
     * @param id Identifiant de l’archive à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteArchive(@PathVariable Long id) {
        archiveService.deleteArchive(id);
    }
}