package tg.inseed.gestioncourrier.gestioncourrier.direction;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import tg.inseed.gestioncourrier.gestioncourrier.affectation.Affectation;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.Utilisateur;

/**
 * Classe représentant une direction ou un département interne de l’INSEED.
 * Une direction peut être associée à des utilisateurs, des courriers ou des dossiers.
 * 
 * Exemple : Direction des ressources humaines, Direction technique
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "direction")
public class Direction {

    /**
     * Identifiant unique de la direction
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direction")
    @JsonProperty("id_direction")
    private Long idDirection;

    /**
     * Nom de la direction
     * Exemple : Direction des ressources humaines
     */
    @Column(name = "nomDirection", nullable = false, length = 100)
    @JsonProperty("nomDirection")
    private String nomDirection;

    /**
     * Description ou mission de la direction
     */
    @Column(name = "descriptionDirection", length = 255)
    @JsonProperty("descriptionDirection")
    private String descriptionDirection;

    /**
     * Direction affectéé
     */

   @OneToMany(mappedBy = "direction")
   @JsonIgnore
   private List <Affectation> affectation = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_utilisateur", nullable = false)
    @JsonProperty("id_utilisateur")
    private Utilisateur utilisateur;


    /**
     * Constructeur sans argument requis par JPA
     */
    public Direction() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idDirection == null) ? 0 : idDirection.hashCode());
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
        Direction other = (Direction) obj;
        if (idDirection == null) {
            if (other.idDirection != null)
                return false;
        } else if (!idDirection.equals(other.idDirection))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Direction [idDirection=" + idDirection + ", nomDirection=" + nomDirection + ", descriptionDirection="
                + descriptionDirection + "]";
    }

    
}