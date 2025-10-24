package tg.inseed.gestioncourrier.gestioncourrier.destinataitre;

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
 * Classe représentant un destinataire dans le système de gestion des courriers.
 * Un destinataire est une structure qui reçoit un courrier envoyé par l’INSEED.
 * 
 * Exemple : Ministère de la Santé, ONG locale, entreprise partenaire
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "destinataire")
public class Destinataire {

    /**
     * Identifiant unique du destinataire
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destinataire")
    @JsonProperty("id_destinataire")
    private Long id;

    /**
     * Nom de la structure destinataire
     */
    @Column(name = "nom_de_structure", nullable = false, length = 255)
    @JsonProperty("nom_de_structure")
    private String nomDeStructure;

    /**
     * Nom du responsable de la structure
     */
    @Column(name = "nom_du_responsable", nullable = false, length = 255)
    @JsonProperty("nom_du_responsable")
    private String nomDuResponsable;

    /**
     * Adresse géographique de la structure
     */
    @Column(name = "adresse_geographique", nullable = false, length = 255)
    @JsonProperty("adresse_geographique")
    private String adresseGeographique;

    /**
     * Adresse email de la structure
     */
    @Column(name = "adresse_email", nullable = false, length = 255)
    @JsonProperty("adresse_email")
    private String adresseEmail;

    /**
     * Numéro de téléphone de la structure
     */
    @Column(name = "tel", nullable = false, length = 15)
    @JsonProperty("tel")
    private String tel;

    @OneToMany(mappedBy="destinataire")
    @JsonIgnore
    private List<Courrier> courrier ;

    /**
     * Constructeur sans argument requis par JPA
     */
    public Destinataire() {}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final Destinataire other = (Destinataire) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Destinataire [id=" + id + ", nomDeStructure=" + nomDeStructure + ", nomDuResponsable="
                + nomDuResponsable + ", adresseGeographique=" + adresseGeographique + ", adresseEmail=" + adresseEmail
                + ", tel=" + tel + ", courrier=" + courrier + "]";
    }

   

    
}
