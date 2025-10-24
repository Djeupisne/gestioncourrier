package tg.inseed.gestioncourrier.gestioncourrier.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service permettant la gestion des opérations CRUD sur les rôles.
 * Ce service interagit avec {@link RoleRepository} pour effectuer les opérations en base.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@Service
public class RoleService {

    @Autowired
    private final RoleRepository roleRepository;

    /**
     * Constructeur avec injection du repository
     *
     * @param roleRepository Repository JPA pour l'entité Role
     */
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Ajoute un nouveau rôle à la base de données.
     *
     * @param role Objet représentant le rôle à créer
     * @return Le rôle ajouté
     */
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    /**
     * Récupère la liste de tous les rôles enregistrés.
     *
     * @return Liste complète des rôles
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Récupère un rôle par son identifiant.
     *
     * @param id Identifiant unique du rôle
     * @return Le rôle correspondant
     * @throws RuntimeException si aucun rôle n’est trouvé
     */
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rôle introuvable avec l'id: " + id));
    }

    /**
     * Met à jour les informations d’un rôle existant.
     *
     * @param id Identifiant du rôle à modifier
     * @param newRole Nouvelles données du rôle
     * @return Le rôle mis à jour
     */
    public Role updateRole(Long id, Role newRole) {
        Role role = getRoleById(id);
        role.setLibelleRole(newRole.getLibelleRole());
        return roleRepository.save(role);
    }

    /**
     * Supprime un rôle de la base de données.
     *
     * @param id Identifiant du rôle à supprimer
     * @throws RuntimeException si le rôle n’existe pas
     */
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Le rôle avec l'id " + id + " n'existe pas.");
        }
        roleRepository.deleteById(id);
    }
}