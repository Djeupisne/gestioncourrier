package tg.inseed.gestioncourrier.gestioncourrier.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tg.inseed.gestioncourrier.gestioncourrier.security.JwtService;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.RoleUtilisateur;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.Utilisateur;
import tg.inseed.gestioncourrier.gestioncourrier.utilisateurs.UtilisateurRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            Utilisateur utilisateur = utilisateurRepository.findByEmailUtilisateur(request.getEmail())
                    .orElseThrow(() -> new BadCredentialsException("Email ou mot de passe incorrect"));

            if (utilisateur.getVerrouille()) {
                return ResponseEntity.status(403).body(Map.of(
                    "success", false,
                    "message", "Compte verrouillé. Contactez l'administrateur."
                ));
            }

            if (!utilisateur.getActif()) {
                return ResponseEntity.status(403).body(Map.of(
                    "success", false,
                    "message", "Compte désactivé. Contactez l'administrateur."
                ));
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse())
            );

            utilisateur.reinitialiserTentativesEchec();
            utilisateurRepository.save(utilisateur);

            String accessToken = jwtService.generateToken(utilisateur);
            String refreshToken = jwtService.generateRefreshToken(utilisateur);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("access_token", accessToken);
            response.put("refresh_token", refreshToken);
            response.put("token_type", "Bearer");
            response.put("utilisateur", Map.of(
                "id", utilisateur.getIdUtilisateur(),
                "nom", utilisateur.getNomUtilisateur(),
                "prenom", utilisateur.getPrenomUtilisateur(),
                "email", utilisateur.getEmailUtilisateur(),
                "role", utilisateur.getRoleString()
            ));

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            utilisateurRepository.findByEmailUtilisateur(request.getEmail())
                    .ifPresent(user -> {
                        user.incrementerTentativesEchec();
                        utilisateurRepository.save(user);
                    });

            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Email ou mot de passe incorrect"
            ));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        try {
            if (utilisateurRepository.findByEmailUtilisateur(request.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Cet email est déjà utilisé"
                ));
            }

            // Validation du rôle
            if (!RoleUtilisateur.isValidRole(request.getRole())) {
                String rolesValides = Arrays.stream(RoleUtilisateur.values())
                        .map(RoleUtilisateur::getCode)
                        .collect(Collectors.joining(", "));
                
                return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "Rôle invalide. Rôles autorisés: " + rolesValides
                ));
            }

            RoleUtilisateur role = RoleUtilisateur.fromString(request.getRole());

            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setNomUtilisateur(request.getNom());
            utilisateur.setPrenomUtilisateur(request.getPrenom());
            utilisateur.setEmailUtilisateur(request.getEmail());
            utilisateur.setMotDePasse(passwordEncoder.encode(request.getMotDePasse()));
            utilisateur.setRole(role);
            utilisateur.setActif(true);
            utilisateur.setVerrouille(false);

            utilisateurRepository.save(utilisateur);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Utilisateur créé avec succès",
                "utilisateur", Map.of(
                    "id", utilisateur.getIdUtilisateur(),
                    "nom", utilisateur.getNomUtilisateur(),
                    "prenom", utilisateur.getPrenomUtilisateur(),
                    "email", utilisateur.getEmailUtilisateur(),
                    "role", utilisateur.getRoleString()
                )
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "Erreur lors de la création de l'utilisateur: " + e.getMessage()
            ));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            String email = jwtService.extractUsername(request.getRefreshToken());
            Utilisateur utilisateur = utilisateurRepository.findByEmailUtilisateur(email)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            if (jwtService.isTokenValid(request.getRefreshToken(), utilisateur)) {
                String accessToken = jwtService.generateToken(utilisateur);
                return ResponseEntity.ok(Map.of(
                    "success", true,
                    "access_token", accessToken,
                    "token_type", "Bearer"
                ));
            }

            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Token de rafraîchissement invalide"
            ));

        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Erreur lors du rafraîchissement du token"
            ));
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest request, Authentication authentication) {
        try {
            String email = authentication.getName();
            Utilisateur utilisateur = utilisateurRepository.findByEmailUtilisateur(email)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            if (!passwordEncoder.matches(request.getAncienMotDePasse(), utilisateur.getMotDePasse())) {
                return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "L'ancien mot de passe est incorrect"
                ));
            }

            if (!request.getNouveauMotDePasse().equals(request.getConfirmationMotDePasse())) {
                return ResponseEntity.status(400).body(Map.of(
                    "success", false,
                    "message", "Les mots de passe ne correspondent pas"
                ));
            }

            utilisateur.setMotDePasse(passwordEncoder.encode(request.getNouveauMotDePasse()));
            utilisateurRepository.save(utilisateur);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Mot de passe modifié avec succès"
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "Erreur lors du changement de mot de passe"
            ));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Map.of(
            "success", true,
            "message", "Déconnexion réussie"
        ));
    }

    @GetMapping("/roles")
    public ResponseEntity<?> getRoles() {
        try {
            var roles = Arrays.stream(RoleUtilisateur.values())
                    .map(role -> Map.of(
                        "code", role.getCode(),
                        "authority", role.getAuthority(),
                        "description", getRoleDescription(role)
                    ))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(Map.of(
                "success", true,
                "roles", roles
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "Erreur lors de la récupération des rôles"
            ));
        }
    }

    private String getRoleDescription(RoleUtilisateur role) {
        switch (role) {
            case ADMIN: return "Administrateur système - Accès complet";
            case DG: return "Directeur Général - Affectation et validation des courriers";
            case SECRETARIAT: return "Secrétariat - Gestion des courriers entrants/sortants";
            case DIRECTION: return "Direction - Traitement des courriers affectés";
            case DIVISION: return "Division - Traitement des courriers";
            case SERVICES: return "Services - Traitement des courriers";
            default: return role.getCode();
        }
    }
}