package tg.inseed.gestioncourrier.gestioncourrier.session;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.Utilisateur;

/**
 * Classe représentant une session de connexion d’un utilisateur.
 * Chaque session contient les informations de connexion et de déconnexion liées à un utilisateur.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "session")
public class Session {

    /**
     * Identifiant unique de la session
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_session")
    @JsonProperty("id_session")
    private Long id_session;

    /**
     * Utilisateur associé à la session
     */
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonProperty("utilisateur")
    private Utilisateur utilisateur;

    /**
     * Date et heure de connexion
     * Exemple : 2025-10-16T08:30:00
     */
    @Column(name = "date_connexion", nullable = false)
    @JsonProperty("date_connexion")
    private LocalDateTime dateConnexion;

    /**
     * Date et heure de déconnexion
     * Exemple : 2025-10-16T17:45:00
     */
    @Column(name = "date_deconnexion")
    @JsonProperty("date_deconnexion")
    private LocalDateTime dateDeconnexion;

   

    /**
     * Constructeur sans argument requis par JPA
     */
    public Session() {}



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_session == null) ? 0 : id_session.hashCode());
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Session other = (Session) obj;
        if (id_session == null) {
            if (other.id_session != null)
                return false;
        } else if (!id_session.equals(other.id_session))
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "Session [id_session=" + id_session + ", utilisateur=" + utilisateur + ", dateConnexion=" + dateConnexion
                + ", dateDeconnexion=" + dateDeconnexion + "]";
    }

   

    
    
}