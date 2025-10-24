package tg.inseed.gestioncourrier.gestioncourrier.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import tg.inseed.gestioncourrier.gestioncourrier.security.JwtAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                // URLs publiques
                .requestMatchers(
                    "/api/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api-docs/**",
                    "/actuator/health",
                    "/error"
                ).permitAll()
                
                // Règles d'accès par rôle selon le cahier des charges
                
                // ADMIN - Accès complet
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                
                // DG - Gestion des affectations et validation
                .requestMatchers("/api/dg/**").hasAnyRole("DG", "ADMIN")
                .requestMatchers("/api/courriers/affecter/**").hasAnyRole("DG", "ADMIN")
                .requestMatchers("/api/courriers/valider/**").hasAnyRole("DG", "ADMIN")
                .requestMatchers("/api/fiches-transmission/dg/**").hasAnyRole("DG", "ADMIN")
                
                // SECRETARIAT - Gestion courriers entrants/sortants
                .requestMatchers("/api/courriers/entrants/**").hasAnyRole("SECRETARIAT", "ADMIN")
                .requestMatchers("/api/courriers/sortants/**").hasAnyRole("SECRETARIAT", "ADMIN")
                .requestMatchers("/api/fiches-transmission/**").hasAnyRole("SECRETARIAT", "ADMIN")
                .requestMatchers("/api/decharges/**").hasAnyRole("SECRETARIAT", "ADMIN")
                
                // DIRECTION - Traitement des courriers affectés
                .requestMatchers("/api/courriers/traiter/**").hasAnyRole("DIRECTION", "ADMIN", "DIVISION")
                .requestMatchers("/api/courriers/mes-affectations").hasAnyRole("DIRECTION", "DIVISION", "SERVICES", "ADMIN")
                .requestMatchers("/api/directions/**").hasAnyRole("DIRECTION", "ADMIN", "DG")
                
                // DIVISION & SERVICES - Consultation et traitement
                .requestMatchers("/api/courriers/consulter/**").hasAnyRole("DIRECTION", "DIVISION", "SERVICES", "SECRETARIAT", "DG", "ADMIN")
                .requestMatchers("/api/tableau-bord/**").hasAnyRole("DIRECTION", "DIVISION", "SERVICES", "SECRETARIAT", "DG", "ADMIN")
                .requestMatchers("/api/statistiques/**").hasAnyRole("DIRECTION", "DIVISION", "SERVICES", "SECRETARIAT", "DG", "ADMIN")
                
                // ARCHIVES - Consultation par tous les utilisateurs authentifiés
                .requestMatchers("/api/archives/**").authenticated()
                
                // Gestion des utilisateurs
                .requestMatchers("/api/utilisateurs/profil").authenticated()
                .requestMatchers("/api/utilisateurs/changer-mot-de-passe").authenticated()
                .requestMatchers("/api/utilisateurs/**").hasRole("ADMIN")
                
                // Par défaut, nécessite une authentification
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type", 
            "X-Requested-With", 
            "Accept", 
            "Origin", 
            "Access-Control-Request-Method", 
            "Access-Control-Request-Headers"
        ));
        configuration.setExposedHeaders(Arrays.asList(
            "Access-Control-Allow-Origin", 
            "Access-Control-Allow-Credentials"
        ));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}