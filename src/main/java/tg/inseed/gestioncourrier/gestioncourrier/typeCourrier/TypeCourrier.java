package tg.inseed.gestioncourrier.gestioncourrier.typeCourrier;

import java.util.ArrayList;
import java.util.List;

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
import tg.inseed.gestioncourrier.gestioncourrier.courriers.Courrier;

/**
 * Classe représentant un type de courrier dans le système.
 * Permet de catégoriser les courriers selon leur nature ou leur usage.
 * 
 * Exemple : Courrier entrant, Courrier sortant, Note interne
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "type_courrier")
public class TypeCourrier {

    /**
     * Identifiant unique du type de courrier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_courrier")
    @JsonProperty("id_type_courrier")
    private Long idType;

    /**
     * Libellé du type de courrier
     * Exemple : Courrier entrant
     */
    @Column(name = "libelle", nullable = false, length = 100)
    @JsonProperty("libelle")
    private String libelle;

    /**
     * Description du type de courrier
     */
    @Column(name = "description", length = 255)
    @JsonProperty("description")
    private String description;

    @OneToMany(mappedBy="typeCourrier")
    @JsonIgnore
    private List<Courrier> courrier= new ArrayList<>();

    /**
     * Constructeur sans argument requis par JPA
     */
    public TypeCourrier() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idType == null) ? 0 : idType.hashCode());
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
        TypeCourrier other = (TypeCourrier) obj;
        if (idType == null) {
            if (other.idType != null)
                return false;
        } else if (!idType.equals(other.idType))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "TypeCourrier [idType=" + idType + ", libelle=" + libelle + ", description=" + description + "]";
    }

   
}