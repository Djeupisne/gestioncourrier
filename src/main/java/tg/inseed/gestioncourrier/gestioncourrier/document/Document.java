package tg.inseed.gestioncourrier.gestioncourrier.document;

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
import tg.inseed.gestioncourrier.gestioncourrier.dossier.Dossier;
import tg.inseed.gestioncourrier.gestioncourrier.regleDeConservation.RegleDeConservation;

/**
 * Classe représentant un document joint ou associé à un courrier.
 * Un document peut être un fichier physique ou numérique lié à un courrier.
 * 
 * Exemple : pièce jointe, rapport, formulaire
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "document")
public class Document {

    /**
     * Identifiant unique du document
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_document")
    @JsonProperty("id_document")
    private Long id;

    /**
     * Nom du document
     * Exemple : Rapport mensuel
     */
    @Column(name = "nom", nullable = false, length = 100)
    @JsonProperty("nom")
    private String nom;

    /**
     * Type ou format du document
     * Exemple : PDF, DOCX
     */
    @Column(name = "type", length = 50)
    @JsonProperty("type")
    private String type;

    /**
     * Chemin ou référence du fichier (si numérique)
     */
    @Column(name = "chemin_fichier", length = 255)
    @JsonProperty("chemin_fichier")
    private String cheminFichier;

    /**
     * Date d’ajout du document
     */
    @Column(name = "date_ajout")
    @JsonProperty("date_ajout")
    private LocalDate dateAjout;

    /**
     * Courrier associé au document
     */
    @ManyToOne
    @JoinColumn(name = "id_courrier", nullable = false)
    @JsonProperty("courrier")
    private Courrier courrier;

    /**
     * Chaque document appartient à un seul dossier
     */
    @ManyToOne
    @JoinColumn(name="id_dossier", nullable=false)
    @JsonProperty("dossier")
    private Dossier dossier;

    /**
     * Chaque document est soumis à une seule règle de conservation
     */
    @ManyToOne
    @JsonProperty("regle")
    @JoinColumn(name="id_regle", nullable=false)
    private RegleDeConservation regleDeConservation;


    /**
     * Constructeur sans argument requis par JPA
     */
    public Document() {}
}