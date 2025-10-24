package tg.inseed.gestioncourrier.gestioncourrier.regleDeConservation;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tg.inseed.gestioncourrier.gestioncourrier.document.Document;

/**
 * Classe représentant une règle de conservation documentaire.
 * Elle définit la durée et les conditions de conservation d’un type de courrier ou document.
 * 
 * Exemple : conserver les courriers RH pendant 5 ans.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "regle_conservation")
public class RegleDeConservation {

    /**
     * Identifiant unique de la règle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_regle")
    @JsonProperty("id_regle")
    private Long id;

    /**
     * Description de la règle
     * Exemple : "Conserver les courriers administratifs pendant 3 ans"
     */
    @Column(name = "description", nullable = false, length = 255)
    @JsonProperty("description")
    private String description;

    /**
     * Durée de conservation en années
     * Exemple : 5
     */
    @Column(name = "duree_conservation", nullable = false)
    @JsonProperty("duree_conservation")
    private Integer dureeConservation;

    @OneToMany(mappedBy="regleDeConservation")
    @JsonIgnore
    private List<Document> document;

    /**
     * Constructeur sans argument requis par JPA
     */
    public RegleDeConservation() {}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final RegleDeConservation other = (RegleDeConservation) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "RegleDeConservation [id=" + id + ", description=" + description + ", dureeConservation="
                + dureeConservation + "]";
    }

    
}