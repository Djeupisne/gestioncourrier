package tg.inseed.gestioncourrier.gestioncourrier.destinataitre;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les destinataires.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link DestinataireService}.
 * 
 * Un destinataire est une structure qui reçoit un courrier envoyé par l’INSEED.
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-destinataires")
public class DestinataireController {

    @Autowired
    private final DestinataireService destinataireService;

    public DestinataireController(DestinataireService destinataireService) {
        this.destinataireService = destinataireService;
    }

    @PostMapping("/ajouter")
    public Destinataire createDestinataire(@RequestBody Destinataire destinataire) {
        return destinataireService.createDestinataire(destinataire);
    }

    @GetMapping("/list")
    public List<Destinataire> getAllDestinataires() {
        return destinataireService.getAllDestinataires();
    }

    @GetMapping("/{id}")
    public Destinataire getDestinataireById(@PathVariable Long id) {
        return destinataireService.getDestinataireById(id);
    }

    @PutMapping("/update/{id}")
    public Destinataire updateDestinataire(@PathVariable Long id, @RequestBody Destinataire destinataire) {
        return destinataireService.updateDestinataire(id, destinataire);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDestinataire(@PathVariable Long id) {
        destinataireService.deleteDestinataire(id);
    }
}