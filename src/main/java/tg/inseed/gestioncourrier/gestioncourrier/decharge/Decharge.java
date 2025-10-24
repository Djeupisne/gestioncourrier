package tg.inseed.gestioncourrier.gestioncourrier.decharge;

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
import tg.inseed.gestioncourrier.gestioncourrier.courriers.Courrier;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.Utilisateur;

/**
 * Classe représentant une décharge de réception d’un courrier.
 * Une décharge est signée par un utilisateur au moment de la réception d’un courrier.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "decharge")
public class Decharge {

    /**
     * Identifiant unique de la décharge
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_decharge")
    @JsonProperty("id_decharge")
    private Long id_decharge;

    /**
     * Courrier concerné par la décharge
     */
    @ManyToOne
    @JoinColumn(name = "id_courrier", nullable = false)
    @JsonProperty("courrier")
    private Courrier courrier;

    /**
     * Utilisateur qui a signé la décharge
     */
    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonProperty("utilisateur")
    private Utilisateur utilisateur;

    /**
     * Date et heure de signature de la décharge
     */
    @Column(name = "date_signature", nullable = false)
    @JsonProperty("date_signature")
    private LocalDateTime dateSignature;

    /**
     * Observations éventuelles lors de la réception
     */
    @Column(name = "observation", length = 255)
    @JsonProperty("observation")
    private String observation;


    /**
     * Constructeur sans argument requis par JPA
     */
    public Decharge() {}


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_decharge == null) ? 0 : id_decharge.hashCode());
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
        Decharge other = (Decharge) obj;
        if (id_decharge == null) {
            if (other.id_decharge != null)
                return false;
        } else if (!id_decharge.equals(other.id_decharge))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Decharge [id_decharge=" + id_decharge + ", courrier=" + courrier + ", utilisateur=" + utilisateur
                + ", dateSignature=" + dateSignature + ", observation=" + observation + "]";
    }

    

}