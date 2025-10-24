package tg.inseed.gestioncourrier.gestioncourrier.role;

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
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.Utilisateur;

/**
 * Classe représentant un rôle dans le système.
 * Chaque rôle définit une fonction ou un niveau d’accès pour un utilisateur.
 * 
 * Exemple : DG, Agent, Secrétaire
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {

    /**
     * Identifiant unique du rôle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    @JsonProperty("id_role")
    private Long idRole;

    /**
     * Libellé du rôle
     * Exemple : Agent
     */
    @Column(name = "libelle", nullable = false, length = 50)
    @JsonProperty("libelle")
    private String libelleRole;

    @OneToMany(mappedBy="role")
    @JsonIgnore
    private List <Utilisateur> utilisateur = new ArrayList<>() ;

 

    /**
     * Constructeur sans argument requis par JPA
     */
    public Role() {}
    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idRole == null) ? 0 : idRole.hashCode());
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
        Role other = (Role) obj;
        if (idRole == null) {
            if (other.idRole != null)
                return false;
        } else if (!idRole.equals(other.idRole))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Role [idRole=" + idRole + ", libelleRole=" + libelleRole + "]";
    }

    
}