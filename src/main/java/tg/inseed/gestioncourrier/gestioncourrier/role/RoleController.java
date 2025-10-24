package tg.inseed.gestioncourrier.gestioncourrier.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur REST pour gérer les opérations CRUD sur les rôles.
 * Ce contrôleur expose les endpoints HTTP pour interagir avec {@link RoleService}.
 * 
 * @author KENKOU
 * @version 1.0
 * @since 10/2025
 */
@RestController
@RequestMapping("/api-roles")
public class RoleController {

    @Autowired
    private final RoleService roleService;

    /**
     * Constructeur avec injection du service
     *
     * @param roleService Service de gestion des rôles
     */
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Endpoint POST pour créer un nouveau rôle.
     *
     * @param role Données du rôle à créer
     * @return Le rôle créé
     */
    @PostMapping("/ajouter")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    /**
     * Endpoint GET pour récupérer tous les rôles.
     *
     * @return Liste de tous les rôles enregistrés
     */
    @GetMapping("/liste")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * Endpoint GET pour récupérer un rôle par son ID.
     *
     * @param id Identifiant du rôle
     * @return Le rôle correspondant
     */
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    /**
     * Endpoint PUT pour modifier un rôle existant.
     *
     * @param id Identifiant du rôle à modifier
     * @param role Nouvelles données du rôle
     * @return Le rôle mis à jour
     */
    @PutMapping("/modify/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    /**
     * Endpoint DELETE pour supprimer un rôle.
     *
     * @param id Identifiant du rôle à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}