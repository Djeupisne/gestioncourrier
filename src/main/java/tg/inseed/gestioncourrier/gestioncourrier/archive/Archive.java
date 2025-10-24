package tg.inseed.gestioncourrier.gestioncourrier.archive;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import tg.inseed.gestioncourrier.gestioncourrier.courriers.Courrier;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.Utilisateur;

/**
 * Classe représentant une archive dans le système de gestion des courriers.
 * Une archive est liée à un courrier et contient des informations sur sa date d’archivage et son emplacement.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "archive")
public class Archive {

    /**
     * Identifiant unique de l’archive
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_archive")
    @JsonProperty("id_archive")
    private Long id;

    /**
     * Date d’archivage du courrier
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date_archivage", nullable = false)
    @JsonProperty("date_archivage")
    private Date dateArchivage;

    /**
     * Emplacement physique ou numérique de l’archive
     */
    @Column(name = "emplacement", nullable = false, length = 100)
    @JsonProperty("emplacement")
    private String emplacement;

    /**
     * Courrier associé à cette archive
     */
    @ManyToOne
    @JoinColumn(name = "id_courrier", nullable = false)
    @JsonProperty("courrier")
    private Courrier courrier;

    @ManyToOne
    @JoinColumn(name="id_utilisateur", nullable= false)
    private Utilisateur utilisateur;

    /**
     * Constructeur sans argument requis par JPA
     */
    public Archive() {}
}
