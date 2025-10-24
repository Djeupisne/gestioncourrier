// ============================================
// FICHIER 8: ChangePasswordRequest.java
// Chemin: src/main/java/tg/inseed/gestioncourrier/gestioncourrier/auth/ChangePasswordRequest.java
// ============================================

package tg.inseed.gestioncourrier.gestioncourrier.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    
    @NotBlank(message = "L'ancien mot de passe est obligatoire")
    private String ancienMotDePasse;

    @NotBlank(message = "Le nouveau mot de passe est obligatoire")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",
        message = "Le mot de passe doit contenir au moins une majuscule, une minuscule, un chiffre et un caractère spécial"
    )
    private String nouveauMotDePasse;

    @NotBlank(message = "La confirmation du mot de passe est obligatoire")
    private String confirmationMotDePasse;
}

