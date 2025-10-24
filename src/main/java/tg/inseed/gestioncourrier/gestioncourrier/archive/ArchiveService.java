package tg.inseed.gestioncourrier.gestioncourrier.archive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les archives.
 * Ce service interagit avec {@link ArchiveRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class ArchiveService {

    @Autowired
    private final ArchiveRepository archiveRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param archiveRepository Repository JPA pour l'entité Archive
     */
    public ArchiveService(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    /**
     * Ajoute une nouvelle archive à la base de données.
     *
     * @param archive Objet représentant l’archive à créer
     * @return L’archive ajoutée
     */
    public Archive createArchive(Archive archive) {
        return archiveRepository.save(archive);
    }

    /**
     * Récupère la liste de toutes les archives enregistrées.
     *
     * @return Liste complète des archives
     */
    public List<Archive> getAllArchives() {
        return archiveRepository.findAll();
    }

    /**
     * Récupère une archive par son identifiant.
     *
     * @param id Identifiant unique de l’archive
     * @return L’archive correspondante
     * @throws RuntimeException si aucune archive n’est trouvée
     */
    public Archive getArchiveById(Long id) {
        return archiveRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Archive introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’une archive existante.
     *
     * @param id Identifiant de l’archive à modifier
     * @param newArchive Nouvelles données de l’archive
     * @return L’archive mise à jour
     */
    public Archive updateArchive(Long id, Archive newArchive) {
        Archive archive = getArchiveById(id);
        archive.setDateArchivage(newArchive.getDateArchivage());
        archive.setEmplacement(newArchive.getEmplacement());
        archive.setCourrier(newArchive.getCourrier());
        return archiveRepository.save(archive);
    }

    /**
     * Supprime une archive de la base de données.
     *
     * @param id Identifiant de l’archive à supprimer
     * @throws RuntimeException si l’archive n’existe pas
     */
    public void deleteArchive(Long id) {
        if (!archiveRepository.existsById(id)) {
            throw new RuntimeException("L’archive avec l'id " + id + " n'existe pas.");
        }
        archiveRepository.deleteById(id);
    }
}