package tg.inseed.gestioncourrier.gestioncourrier.courriers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import tg.inseed.gestioncourrier.gestioncourrier.FicheDeTransmission.FicheDeTransmission;
import tg.inseed.gestioncourrier.gestioncourrier.affectation.Affectation;
import tg.inseed.gestioncourrier.gestioncourrier.decharge.Decharge;
import tg.inseed.gestioncourrier.gestioncourrier.destinataitre.Destinataire;
import tg.inseed.gestioncourrier.gestioncourrier.expediteur.Expediteur;
import tg.inseed.gestioncourrier.gestioncourrier.statut.Statut;
import tg.inseed.gestioncourrier.gestioncourrier.typeCourrier.TypeCourrier;


@Entity
@Getter
@Setter
@Table(name = "courrier")
public class Courrier {
/**
     * Identifiant unique du courrier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_courrier")
    @JsonProperty("id_courrier")
    private Long idCourrier;

    /**
     * Objet du courrier
     * Exemple : Demande de partenariat
     */
    @Column(name = "objet", nullable = false, length = 150)
    @JsonProperty("objet")
    private String objet;

    /**
     * Date de réception du courrier
     * Exemple : 2025-10-16
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date_reception", nullable = false)
    @JsonProperty("date_reception")
    private Date dateReception;

    /**
     * Utilisateur expéditeur du courrier
     */
    @ManyToOne
    @JoinColumn(name = "id_expediteur", nullable = false)
    @JsonProperty("id_expediteur")
    private Expediteur expediteur;

    @ManyToOne
    @JoinColumn(name = "id_type_courrier", nullable = false)
    @JsonProperty("id_type_courrier")
    private TypeCourrier typeCourrier;
    
    


    /**
     * Utilisateur destinataire du courrier
     */
    @ManyToOne
    @JoinColumn(name = "id_destinataire", nullable = false)
    @JsonProperty("id_destinataire")
    private Destinataire destinataire;

    /**
     * Statut du courrier (ex : En attente, Traité)
     */
    @ManyToOne
    @JoinColumn(name = "id_statut", nullable = false)
    @JsonProperty("id_statut")
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "id_fiche", nullable = false)
    @JsonProperty("id_fiche")
    private FicheDeTransmission ficheDeTransmission;

    @OneToMany(mappedBy="courrier")
    @JsonIgnore
    private List <Decharge> decharge = new ArrayList<>();

    @OneToMany(mappedBy = "courrier")
    @JsonIgnore
    private List <Affectation> affectation = new ArrayList<>();


    /**
     * Constructeur sans argument requis par JPA
     */
    public Courrier() {}

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idCourrier);
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
        final Courrier other = (Courrier) obj;
        return Objects.equals(this.idCourrier, other.idCourrier);
    }

    @Override
    public String toString() {
        return "Courrier [idCourrier=" + idCourrier + ", objet=" + objet + ", dateReception=" + dateReception
                + ", expediteur=" + expediteur + ", destinataire=" + destinataire + ", statut=" + statut + "]";
    }

    
}
