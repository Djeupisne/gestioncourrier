package tg.inseed.gestioncourrier.gestioncourrier.journalisation;

import java.time.LocalDate;
import java.util.Objects;

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
 * Classe représentant une entrée de journalisation dans le système.
 * Elle permet de tracer les actions réalisées par les utilisateurs.
 * 
 * Exemple : Connexion, création de courrier, suppression d’un dossier, etc.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "journalisation")
public class Journalisation {

    /**
     * Identifiant unique de la journalisation
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_journalisation")
    @JsonProperty("id_journalisation")
    private Long id;

    /**
     * Description de l'action réalisée
     * Exemple : "Création d’un nouveau courrier"
     */
    @Column(name = "action", nullable = false, length = 255)
    @JsonProperty("action")
    private String action;

    /**
     * Date de l'action
     */
    @Column(name = "date_action", nullable = false)
    @JsonProperty("date_action")
    private LocalDate dateAction;

    /**
     * Utilisateur ayant réalisé l'action
     */
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonProperty("utilisateur")
    private Utilisateur utilisateur;



    /**
     * Constructeur sans argument requis par JPA
     */
    public Journalisation() {}


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Journalisation other = (Journalisation) obj;
        return Objects.equals(this.id, other.id);
    }


    @Override
    public String toString() {
        return "Journalisation [id=" + id + ", action=" + action + ", dateAction=" + dateAction + "]";
    }


    

    
}