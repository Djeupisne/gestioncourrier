package tg.inseed.gestioncourrier.gestioncourrier.direction;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les directions.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link DirectionService}.
 * 
 * Exemple d’usage : regrouper les utilisateurs ou courriers par département.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-directions")
public class DirectionController {

    @Autowired
    private final DirectionService directionService;

    /**
     * Constructeur avec injection du service
     *
     * @param directionService Service de gestion des directions
     */
    public DirectionController(DirectionService directionService) {
        this.directionService = directionService;
    }

    /**
     * Endpoint POST pour créer une nouvelle direction.
     *
     * @param direction Données de la direction à créer
     * @return La direction créée
     */
    @PostMapping("/ajouter")
    public Direction createDirection(@RequestBody Direction direction) {
        return directionService.createDirection(direction);
    }

    /**
     * Endpoint GET pour récupérer toutes les directions.
     *
     * @return Liste de toutes les directions enregistrées
     */
    @GetMapping("/list")
    public List<Direction> getAllDirections() {
        return directionService.getAllDirections();
    }

    /**
     * Endpoint GET pour récupérer une direction par son identifiant.
     *
     * @param id Identifiant unique de la direction
     * @return La direction correspondante
     */
    @GetMapping("/{id}")
    public Direction getDirectionById(@PathVariable Long id) {
        return directionService.getDirectionById(id);
    }

    /**
     * Endpoint PUT pour mettre à jour une direction existante.
     *
     * @param id Identifiant de la direction à modifier
     * @param direction Nouvelles données de la direction
     * @return La direction mise à jour
     */
    @PutMapping("/update/{id}")
    public Direction updateDirection(@PathVariable Long id, @RequestBody Direction direction) {
        return directionService.updateDirection(id, direction);
    }

    /**
     * Endpoint DELETE pour supprimer une direction.
     *
     * @param id Identifiant de la direction à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDirection(@PathVariable Long id) {
        directionService.deleteDirection(id);
    }
}