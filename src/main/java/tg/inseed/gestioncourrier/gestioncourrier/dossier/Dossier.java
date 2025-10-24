package tg.inseed.gestioncourrier.gestioncourrier.dossier;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
 * Classe représentant un dossier dans le système.
 * Un dossier permet de regrouper des courriers ou documents selon une logique métier.
 * 
 * Exemple : Dossier RH, Dossier Financier, etc.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "dossier")
public class Dossier {

    /**
     * Identifiant unique du dossier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dossier")
    @JsonProperty("id_dossier")
    private Long id;

    /**
     * Nom du dossier
     * Exemple : Dossier RH
     */
    @Column(name = "nom", nullable = false, length = 50)
    @JsonProperty("nom")
    private String nom;

    /**
     * Description du contenu ou de l’objectif du dossier
     * Exemple : Contient tous les courriers liés aux ressources humaines
     */
    @Column(name = "description", length = 255)
    @JsonProperty("description")
    private String description;
  

    /**
     * Un dossier contenant plusieurs documents
     */
   @OneToMany(mappedBy = "dossier")
   @JsonIgnore
   private Set<Document> document = new HashSet<>();


    /**
     * Constructeur sans argument requis par JPA
     */
    public Dossier() {}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Dossier other = (Dossier) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Dossier [id=" + id + ", nom=" + nom + ", description=" + description + "]";
    }

 

    
}