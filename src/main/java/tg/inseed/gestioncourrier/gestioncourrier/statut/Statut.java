package tg.inseed.gestioncourrier.gestioncourrier.statut;

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

@Entity
@Getter
@Setter
@Table(name="Statut")
public class Statut {
    
    /**
     * Identifiant unique du statut
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_statut")
    @JsonProperty("id_statut")
    private Long idStatut;

    /**
     * Libellé du statut
     * Exemple : traité
     */
    @Column(name = "libelle_statut", nullable = false, length = 50)
    @JsonProperty("libelle_statut")
    private String libelleStatut;
    
    @OneToMany(mappedBy="statut")
    @JsonIgnore
    private List<Courrier> courrier;


    public Statut() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idStatut == null) ? 0 : idStatut.hashCode());
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
        Statut other = (Statut) obj;
        if (idStatut == null) {
            if (other.idStatut != null)
                return false;
        } else if (!idStatut.equals(other.idStatut))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Statut [idStatut=" + idStatut + ", libelle_statut=" + libelleStatut + "]";
    }

    
}
