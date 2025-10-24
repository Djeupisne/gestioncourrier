// ============================================
// FICHIER 7: RefreshTokenRequest.java
// Chemin: src/main/java/tg/inseed/gestioncourrier/gestioncourrier/auth/RefreshTokenRequest.java
// ============================================

package tg.inseed.gestioncourrier.gestioncourrier.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {
    
    @NotBlank(message = "Le refresh token est obligatoire")
    private String refreshToken;
}

