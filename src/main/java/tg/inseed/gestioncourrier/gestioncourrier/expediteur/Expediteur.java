package tg.inseed.gestioncourrier.gestioncourrier.expediteur;

import java.time.LocalDate;
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
import tg.inseed.gestioncourrier.gestioncourrier.courriers.Courrier;

/**
 * Classe représentant un expéditeur dans le système de gestion des courriers.
 * Un expéditeur est une structure externe qui envoie des courriers à l’INSEED.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "expediteur")
public class Expediteur {

    /**
     * Identifiant unique de l’expéditeur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediteur")
    @JsonProperty("id_expediteur")
    private Long id;

    /**
     * Nom de la structure expéditrice
     * Exemple : Ministère de l’Économie
     */
    @Column(name = "nom_de_structure", nullable = false, length = 255)
    @JsonProperty("nom_de_structure")
    private String nomDeStructure;

    /**
     * Nom du responsable de la structure
     * Exemple : M. Kossi Adjakpa
     */
    @Column(name = "nom_du_responsable", nullable = false, length = 255)
    @JsonProperty("nom_du_responsable")
    private String nomDuResponsable;

    /**
     * Adresse géographique de la structure
     * Exemple : Quartier administratif, Lomé
     */
    @Column(name = "adresse_geographique", nullable = false, length = 255)
    @JsonProperty("adresse_geographique")
    private String adresseGeographique;

    /**
     * Adresse email de la structure
     * Exemple : contact@economie.tg
     */
    @Column(name = "adresse_email", nullable = false, length = 255)
    @JsonProperty("adresse_email")
    private String adresseEmail;

    /**
     * Numéro de téléphone de la structure
     * Exemple : 22890112233
     */
    @Column(name = "tel", nullable = false, length = 15)
    @JsonProperty("tel")
    private String tel;

    /**
     * Type de structure (Ministère, ONG, Entreprise, etc.)
     */
    @Column(name = "type_structure", length = 100)
    @JsonProperty("type_structure")
    private String typeStructure;

    /**
     * Date d’enregistrement de l’expéditeur dans le système
     */
    @Column(name = "date_enregistrement")
    @JsonProperty("date_enregistrement")
    private LocalDate dateEnregistrement;

    @OneToMany(mappedBy="expediteur")
    @JsonIgnore
    private List<Courrier> courrier ;

    /**
     * Constructeur sans argument requis par JPA
     */
    public Expediteur() {}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Expediteur other = (Expediteur) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Expediteur [id=" + id + ", nomDeStructure=" + nomDeStructure + ", nomDuResponsable=" + nomDuResponsable
                + ", adresseGeographique=" + adresseGeographique + ", adresseEmail=" + adresseEmail + ", tel=" + tel
                + ", typeStructure=" + typeStructure + ", dateEnregistrement=" + dateEnregistrement + "]";
    }

  
    
}