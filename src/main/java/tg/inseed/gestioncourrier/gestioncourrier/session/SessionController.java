package tg.inseed.gestioncourrier.gestioncourrier.session;

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
 * Contrôleur REST pour gérer les opérations CRUD sur les sessions.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link SessionService}.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-sessions")
public class SessionController {

    @Autowired
    private final SessionService sessionService;

    /**
     * Constructeur avec injection du service
     *
     * @param sessionService Service de gestion des sessions
     */
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * Endpoint POST pour créer une nouvelle session.
     *
     * @param session Données de la session à créer
     * @return La session créée
     */
    @PostMapping("/ajouter")
    public Session createSession(@RequestBody Session session) {
        return sessionService.createSession(session);
    }

    /**
     * Endpoint GET pour récupérer toutes les sessions.
     *
     * @return Liste de toutes les sessions enregistrées
     */
    @GetMapping("/lister")
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    /**
     * Endpoint GET pour récupérer une session par son ID.
     *
     * @param id Identifiant de la session
     * @return La session correspondante
     */
    @GetMapping("/{id}")
    public Session getSessionById(@PathVariable Long id) {
        return sessionService.getSessionById(id);
    }

    /**
     * Endpoint PUT pour modifier une session existante.
     *
     * @param id Identifiant de la session à modifier
     * @param session Nouvelles données de la session
     * @return La session mise à jour
     */
    @PutMapping("/modify/{id}")
    public Session updateSession(@PathVariable Long id, @RequestBody Session session) {
        return sessionService.updateSession(id, session);
    }

    /**
     * Endpoint DELETE pour supprimer une session.
     *
     * @param id Identifiant de la session à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
    }
}