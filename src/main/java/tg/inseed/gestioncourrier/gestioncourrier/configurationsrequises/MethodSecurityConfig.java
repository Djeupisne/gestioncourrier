package tg.inseed.gestioncourrier.gestioncourrier.configurationsrequises;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig {
    // Rien d’autre à ajouter ici pour l’activation
}
