package tg.inseed.gestioncourrier.gestioncourrier.direction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les directions.
 * Ce service interagit avec {@link DirectionRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class DirectionService {

    @Autowired
    private final DirectionRepository directionRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param directionRepository Repository JPA pour l'entité Direction
     */
    public DirectionService(DirectionRepository directionRepository) {
        this.directionRepository = directionRepository;
    }

    /**
     * Ajoute une nouvelle direction à la base de données.
     *
     * @param direction Objet représentant la direction à créer
     * @return La direction ajoutée
     */
    public Direction createDirection(Direction direction) {
        return directionRepository.save(direction);
    }

    /**
     * Récupère la liste de toutes les directions enregistrées.
     *
     * @return Liste complète des directions
     */
    public List<Direction> getAllDirections() {
        return directionRepository.findAll();
    }

    /**
     * Récupère une direction par son identifiant.
     *
     * @param id Identifiant unique de la direction
     * @return La direction correspondante
     * @throws RuntimeException si aucune direction n’est trouvée
     */
    public Direction getDirectionById(Long id) {
        return directionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Direction introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’une direction existante.
     *
     * @param id Identifiant de la direction à modifier
     * @param newDirection Nouvelles données de la direction
     * @return La direction mise à jour
     */
    public Direction updateDirection(Long id, Direction newDirection) {
        Direction direction = getDirectionById(id);
        direction.setNomDirection(newDirection.getNomDirection());
        direction.setDescriptionDirection(newDirection.getDescriptionDirection());
        return directionRepository.save(direction);
    }

    /**
     * Supprime une direction de la base de données.
     *
     * @param id Identifiant de la direction à supprimer
     * @throws RuntimeException si la direction n’existe pas
     */
    public void deleteDirection(Long id) {
        if (!directionRepository.existsById(id)) {
            throw new RuntimeException("La direction avec l'id " + id + " n'existe pas.");
        }
        directionRepository.deleteById(id);
    }
}