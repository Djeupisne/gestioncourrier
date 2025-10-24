package tg.inseed.gestioncourrier.gestioncourrier.FicheDeTransmission;

import java.time.LocalDate;
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
 * Entité représentant une fiche de transmission.
 * Utilisée pour acheminer un courrier avec des instructions précises.
 */
@Entity // Indique que cette classe est une entité JPA
@Getter // Génère automatiquement les getters
@Setter // Génère automatiquement les setters
@Table(name = "fiche_de_transmission") // Nom de la table en base
public class FicheDeTransmission {

    @Id // Clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrément
    @Column(name = "id_fiche") // Nom de la colonne
    @JsonProperty("id_fiche") // Nom utilisé en JSON
    private Long idFiche;

    @Column(name = "date_envoi", nullable = false) // Date d’envoi obligatoire
    @JsonProperty("date_envoi")
    private LocalDate dateEnvoi;

    @Column(name = "reference", length = 100) // Référence du courrier
    @JsonProperty("reference")
    private String reference;

    @OneToMany(mappedBy="ficheDeTransmission")
    @JsonIgnore
    private List<Courrier> courrier = new ArrayList<>();

    // Cases à cocher (instructions)
    @Column(name = "tres_urgent")
    private boolean tresUrgent; // Très urgent

    @Column(name = "urgent")
    private boolean urgent; // Urgent

    @Column(name = "men_parler")
    private boolean menParler; // À discuter oralement

    @Column(name = "pour_attribution")
    private boolean pourAttribution; // Pour attribution

    @Column(name = "pour_etude_et_avis")
    private boolean pourEtudeEtAvis; // Pour étude et avis

    @Column(name = "pour_disposition_a_prendre")
    private boolean pourDisposition; // Pour disposition à prendre

    @Column(name = "pour_element_de_reponse")
    private boolean elementDeReponse; // Pour élément de réponse

    @Column(name = "fin_retour")
    private boolean finRetour; // Pour retour final

    @Column(name = "pour_suite_a_donner")
    private boolean pourSuiteADonner; // Pour suite à donner

    @Column(name = "pour_visa_prealable")
    private boolean pourVisaPrealable; // Pour visa préalable

    @Column(name = "pour_le_necessaire_a_faire")
    private boolean pourNecessaire; // Pour le nécessaire à faire

    @Column(name = "note_pour_le_dg")
    private boolean notePourLeDG; // Note pour le Directeur Général

    @Column(name = "pour_resumer_succinct")
    private boolean pourResumerSuccinct; // Pour résumé succinct

    @Column(name = "note_ministre")
    private boolean noteMinistre; // Note à l’attention du Ministre

    @Column(name = "copie_a")
    private boolean copieA; // Copie à...

    @Column(name = "me_representer")
    private boolean meRepresenter; // Me représenter

    @Column(name = "pour_etude_en_rapport_avec")
    private boolean pourEtude; // Pour étude en rapport avec...

    @Column(name = "lettre_de_transmission_a")
    private boolean lettreDeTransmission; // Lettre de transmission

    @Column(name = "Bordereau_d_envoie") 
    private boolean BordereauEnvoi;

    @Column(name = "pour_information")
    private boolean pourInformation; // Pour information

    @Column(name = "bordeau_d_envoi")
    private boolean bordeauEnvoi; // Bordereau d’envoi

    @Column(name = "a_toute_fin_utile")
    private boolean aTouteFinUtile; // À toute fin utile

    @Column(name = "en_instance")
    private boolean enInstance; // En instance

    @Column(name = "a_classer")
    private boolean aClasser; // À classer

    @Column(name = "courrier_reserve")
    private boolean courrierReserve; // Courrier réservé

    @Column(name = "observation", length = 255)
    @JsonProperty("observation")
    private String observation; // Commentaire libre

    public FicheDeTransmission() {}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idFiche == null) ? 0 : idFiche.hashCode());
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
        FicheDeTransmission other = (FicheDeTransmission) obj;
        if (idFiche == null) {
            if (other.idFiche != null)
                return false;
        } else if (!idFiche.equals(other.idFiche))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "FicheDeTransmission [idFiche=" + idFiche + ", dateEnvoi=" + dateEnvoi + ", reference=" + reference
                + ", tresUrgent=" + tresUrgent + ", urgent=" + urgent + ", menParler=" + menParler
                + ", pourAttribution=" + pourAttribution + ", pourEtudeEtAvis=" + pourEtudeEtAvis + ", pourDisposition="
                + pourDisposition + ", elementDeReponse=" + elementDeReponse + ", finRetour=" + finRetour
                + ", pourSuiteADonner=" + pourSuiteADonner + ", pourVisaPrealable=" + pourVisaPrealable
                + ", pourNecessaire=" + pourNecessaire + ", notePourLeDG=" + notePourLeDG + ", pourResumerSuccinct="
                + pourResumerSuccinct + ", noteMinistre=" + noteMinistre + ", copieA=" + copieA + ", meRepresenter="
                + meRepresenter + ", pourEtude=" + pourEtude + ", lettreDeTransmission=" + lettreDeTransmission
                + ", BordereauEnvoi=" + BordereauEnvoi + ", pourInformation=" + pourInformation + ", bordeauEnvoi="
                + bordeauEnvoi + ", aTouteFinUtile=" + aTouteFinUtile + ", enInstance=" + enInstance + ", aClasser="
                + aClasser + ", courrierReserve=" + courrierReserve + ", observation=" + observation + "]";
    } // Constructeur par défaut requis par JPA

    
    
}