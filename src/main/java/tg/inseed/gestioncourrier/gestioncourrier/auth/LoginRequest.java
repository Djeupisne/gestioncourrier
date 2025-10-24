// ============================================
// FICHIER 5: LoginRequest.java
// Chemin: src/main/java/tg/inseed/gestioncourrier/gestioncourrier/auth/LoginRequest.java
// ============================================

package tg.inseed.gestioncourrier.gestioncourrier.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String motDePasse;
}

