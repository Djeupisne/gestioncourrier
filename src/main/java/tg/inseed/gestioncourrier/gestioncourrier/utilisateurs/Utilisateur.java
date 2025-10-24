package tg.inseed.gestioncourrier.gestioncourrier.utilisateurs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import tg.inseed.gestioncourrier.gestioncourrier.affectation.Affectation;
import tg.inseed.gestioncourrier.gestioncourrier.archive.Archive;
import tg.inseed.gestioncourrier.gestioncourrier.decharge.Decharge;
import tg.inseed.gestioncourrier.gestioncourrier.direction.Direction;
import tg.inseed.gestioncourrier.gestioncourrier.journalisation.Journalisation;

import tg.inseed.gestioncourrier.gestioncourrier.session.Session;



/**
 * Classe représentant un utilisateur du système de gestion des courriers
 * 
 * @author KENKOU Marê Dave Christian
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "utilisateur")
public class Utilisateur {

    /**
     * Identifiant unique de l'utilisateur
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    @JsonProperty("id_utilisateur")
    private Long idUtilisateur;

    /**
     * Nom de l'utilisateur
     * Exemple : KENKOU
     */
    @Column(name = "nom_utilisateur", nullable = false, length = 50)
    @JsonProperty("nom_utilisateur")
    private String nomUtilisateur;

    /**
     * Prénom de l'utilisateur
     * Exemple : Marê Dave
     */
    @Column(name = "prenom_utilisateur", nullable = false, length = 75)
    @JsonProperty("prenom_utilisateur")
    private String prenomUtilisateur;

    /**
     * Adresse email de l'utilisateur ou encore son identifiant de connexion
     * Exemple : mare.christian@inseed.tg
     */
    @Column(name = "email_utilisateur", nullable = false, length = 100, unique = true)
    @JsonProperty("email_utilisateur")
    private String emailUtilisateur;

    /**
     * Mot de passe de l'identifiant de connexion de l'utilisateur
     * Exemple : mdp1234
     */
    
    @Column(name = "mot_de_passe", nullable = false, length = 100)
    @JsonProperty("mot_de_passe" )
    private String motDePasse;

    /**
     * Rôle de l'utilisateur dans le système
     * Exemple : DG, Agent, Secrétaire
     */
    @Column(name = "role_utilisateur", nullable = false, length = 30)
    @JsonProperty("role_utilisateur")
    private String role;

     /**
     * Liste des journalisations associées à cet utilisateur
     * Relation OneToMany vers Journalisation
     */
   @OneToMany(mappedBy = "utilisateur")
   @JsonIgnore
   private Set<Journalisation> journalisation = new HashSet<>();

   @OneToMany(mappedBy = "utilisateur")
   @JsonIgnore
   private Set<Session> session = new HashSet<>();
   

   @OneToMany(mappedBy = "utilisateur")
   @JsonIgnore
   private List <Decharge> decharge = new ArrayList<>();

   @OneToMany(mappedBy = "utilisateur")
   @JsonIgnore
   private List <Affectation> affectation = new ArrayList<>();

   @OneToMany(mappedBy = "utilisateur")
   @JsonIgnore
   private List <Archive> archive = new ArrayList<>();

   @OneToMany(mappedBy="utilisateur")
   @JsonIgnore
   private List <Direction> direction = new ArrayList<>();
        
    /**
     * Constructeur sans argument requis par JPA
     */
    public Utilisateur() {
    }

   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idUtilisateur == null) ? 0 : idUtilisateur.hashCode());
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
        Utilisateur other = (Utilisateur) obj;
        if (idUtilisateur == null) {
            if (other.idUtilisateur != null)
                return false;
        } else if (!idUtilisateur.equals(other.idUtilisateur))
            return false;
        return true;
    }

@Override
public String toString() {
    return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nomUtilisateur=" + nomUtilisateur
            + ", prenomUtilisateur=" + prenomUtilisateur + ", emailUtilisateur=" + emailUtilisateur
            + ", motDePasse=****" + ", role=" + role + "]";
}

    
}
