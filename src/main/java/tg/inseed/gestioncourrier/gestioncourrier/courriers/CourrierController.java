package tg.inseed.gestioncourrier.gestioncourrier.courriers;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les courriers.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link CourrierService}.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-courriers")
public class CourrierController 
    {

    @Autowired
    private final CourrierService courrierService;

    /**
     * Constructeur avec injection du service
     *
     * @param courrierService Service de gestion des courriers
     */
    public CourrierController(CourrierService courrierService) {
        this.courrierService = courrierService;
    }

    /**
     * Endpoint POST pour créer un nouveau courrier.
     *
     * @param courrier Données du courrier à créer
     * @return Le courrier créé
     */
    @PostMapping("/creer")
    public Courrier createCourrier(@RequestBody Courrier courrier) {
        return courrierService.createCourrier(courrier);
    }

    /**
     * Endpoint GET pour récupérer tous les courriers.
     *
     * @return Liste de tous les courriers enregistrés
     */
    @GetMapping("afficher")
    public List<Courrier> getAllCourriers() {
        return courrierService.getAllCourriers();
    }

    /**
     * Endpoint GET pour récupérer un courrier par son ID.
     *
     * @param id Identifiant du courrier
     * @return Le courrier correspondant
     */
    @GetMapping("/{id}")
    public Courrier getCourrierById(@PathVariable Long id) {
        return courrierService.getCourrierById(id);
    }

    /**
     * Endpoint PUT pour modifier un courrier existant.
     *
     * @param id Identifiant du courrier à modifier
     * @param courrier Nouvelles données du courrier
     * @return Le courrier mis à jour
     */
    @PutMapping("/{id}")
    public Courrier updateCourrier(@PathVariable Long id, @RequestBody Courrier courrier) {
        return courrierService.updateCourrier(id, courrier);
    }

    /**
     * Endpoint DELETE pour supprimer un courrier.
     *
     * @param id Identifiant du courrier à supprimer
     */
    @DeleteMapping("/{id}")
    public void deleteCourrier(@PathVariable Long id) {
        courrierService.deleteCourrier(id);
    }

}
