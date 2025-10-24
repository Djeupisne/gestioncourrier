package tg.inseed.gestioncourrier.gestioncourrier.affectation;

import java.time.LocalDate;

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
import tg.inseed.gestioncourrier.gestioncourrier.direction.Direction;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.Utilisateur;

/**
 * Entité représentant une affectation dans le système.
 * Permet de suivre l’attribution d’un courrier à un utilisateur pour traitement.
 */
@Entity // Déclare cette classe comme une entité JPA
@Getter // Génère les getters automatiquement
@Setter // Génère les setters automatiquement
@Table(name = "affectation") // Nom de la table en base
public class Affectation {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrément
    @Column(name = "id_affectation")
    @JsonProperty("id_affectation")
    private Long id;

    @ManyToOne // Relation avec le courrier concerné
    @JoinColumn(name = "id_courrier", nullable = false)
    @JsonProperty("courrier")
    private Courrier courrier;

    @ManyToOne // Relation avec l’utilisateur affecté
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonProperty("utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne // Relation avec la direction concernée
    @JoinColumn(name = "id_direction", nullable = false)
    @JsonProperty("direction")
    private Direction direction;

    @Column(name = "date_affectation", nullable = false)
    @JsonProperty("date_affectation")
    private LocalDate dateAffectation;

    @Column(name = "motif", length = 255)
    @JsonProperty("motif")
    private String motif; // Motif ou commentaire de l’affectation

    public Affectation() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        Affectation other = (Affectation) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Affectation [id=" + id + ", courrier=" + courrier + ", utilisateur=" + utilisateur + ", direction="
                + direction + ", dateAffectation=" + dateAffectation + ", motif=" + motif + "]";
    } // Constructeur par défaut requis par JPA

    
}