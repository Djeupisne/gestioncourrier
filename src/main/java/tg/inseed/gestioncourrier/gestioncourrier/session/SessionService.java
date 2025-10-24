package tg.inseed.gestioncourrier.gestioncourrier.session;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les sessions.
 * Ce service interagit avec {@link SessionRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class SessionService {

    @Autowired
    private final SessionDataRepository sessionDataRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param sessionDataRepository Repository JPA pour l'entité Session
     */
    public SessionService(SessionDataRepository sessionDataRepository) {
        this.sessionDataRepository = sessionDataRepository;
    }

    /**
     * Ajoute une nouvelle session à la base de données.
     *
     * @param session Objet représentant la session à créer
     * @return La session ajoutée
     */
    public Session createSession(Session session) {
        return sessionDataRepository.save(session);
    }

    /**
     * Récupère la liste de toutes les sessions enregistrées.
     *
     * @return Liste complète des sessions
     */
    public List<Session> getAllSessions() {
        return sessionDataRepository.findAll();
    }

    /**
     * Récupère une session par son identifiant.
     *
     * @param id Identifiant unique de la session
     * @return La session correspondante
     * @throws RuntimeException si aucune session n’est trouvée
     */
    public Session getSessionById(Long id) {
        return sessionDataRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Session introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’une session existante.
     *
     * @param id Identifiant de la session à modifier
     * @param newSession Nouvelles données de la session
     * @return La session mise à jour
     */
    public Session updateSession(Long id, Session newSession) {
        Session session = getSessionById(id);
        session.setUtilisateur(newSession.getUtilisateur());
        session.setDateConnexion(newSession.getDateConnexion());
        session.setDateDeconnexion(newSession.getDateDeconnexion());
        return sessionDataRepository.save(session);
    }

    /**
     * Supprime une session de la base de données.
     *
     * @param id Identifiant de la session à supprimer
     * @throws RuntimeException si la session n’existe pas
     */
    public void deleteSession(Long id) {
        if (!sessionDataRepository.existsById(id)) {
            throw new RuntimeException("La session avec l'id " + id + " n'existe pas.");
        }
        sessionDataRepository.deleteById(id);
    }
}