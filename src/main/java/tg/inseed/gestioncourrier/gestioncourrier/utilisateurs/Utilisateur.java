package tg.inseed.gestioncourrier.gestioncourrier.utilisateurs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tg.inseed.gestioncourrier.gestioncourrier.affectation.Affectation;
import tg.inseed.gestioncourrier.gestioncourrier.archive.Archive;
import tg.inseed.gestioncourrier.gestioncourrier.decharge.Decharge;
import tg.inseed.gestioncourrier.gestioncourrier.direction.Direction;
import tg.inseed.gestioncourrier.gestioncourrier.journalisation.Journalisation;
import tg.inseed.gestioncourrier.gestioncourrier.session.Session;

@Entity
@Getter
@Setter
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
    @JsonProperty("id_utilisateur")
    private Long idUtilisateur;

    @Column(name = "nom_utilisateur", nullable = false, length = 50)
    @JsonProperty("nom_utilisateur")
    private String nomUtilisateur;

    @Column(name = "prenom_utilisateur", nullable = false, length = 75)
    @JsonProperty("prenom_utilisateur")
    private String prenomUtilisateur;

    @Column(name = "email_utilisateur", nullable = false, length = 100, unique = true)
    @JsonProperty("email_utilisateur")
    private String emailUtilisateur;

    @Column(name = "mot_de_passe", nullable = false, length = 100)
    @JsonProperty("mot_de_passe")
    private String motDePasse;

    // Changement important : utilisation de l'enum pour le rôle
    @Enumerated(EnumType.STRING)
    @Column(name = "role_utilisateur", nullable = false, length = 30)
    @JsonProperty("role_utilisateur")
    private RoleUtilisateur role;

    // Relation avec l'entité Role (si vous voulez garder les deux)
    @ManyToOne
    @JoinColumn(name = "id_role")
    @JsonIgnore
    private tg.inseed.gestioncourrier.gestioncourrier.role.Role roleEntity;

    // Champs pour la sécurité
    @Column(name = "actif", nullable = false)
    private boolean actif = true;

    @Column(name = "verrouille", nullable = false)
    private boolean verrouille = false;

    @Column(name = "tentatives_echec", nullable = false)
    private int tentativesEchec = 0;

    @Transient
    private final int MAX_TENTATIVES = 3;

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private Set<Journalisation> journalisation = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private Set<Session> session = new HashSet<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Decharge> decharge = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Affectation> affectation = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Archive> archive = new ArrayList<>();

    @OneToMany(mappedBy = "utilisateur")
    @JsonIgnore
    private List<Direction> direction = new ArrayList<>();

    public Utilisateur() {
    }

    // Méthodes pour la gestion de la sécurité
    public void incrementerTentativesEchec() {
        this.tentativesEchec++;
        if (this.tentativesEchec >= MAX_TENTATIVES) {
            this.verrouille = true;
        }
    }

    public void reinitialiserTentativesEchec() {
        this.tentativesEchec = 0;
        this.verrouille = false;
    }

    // Implémentation des méthodes de UserDetails
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getAuthority()));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.motDePasse;
    }

    @Override
    @JsonIgnore
    public String getUsername() {
        return this.emailUtilisateur;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !this.verrouille;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.actif;
    }

    // Getters supplémentaires pour compatibilité
    public boolean getVerrouille() {
        return this.verrouille;
    }

    public boolean getActif() {
        return this.actif;
    }

    // Getter pour le rôle en string (compatibilité)
    public String getRoleString() {
        return this.role.getCode();
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
                + ", role=" + role + "]";
    }
}