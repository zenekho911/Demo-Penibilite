package demo.penibilite.backend.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private JwtAuthConverter jwtAuthConverter;

	public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
		this.jwtAuthConverter = jwtAuthConverter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(ar -> ar
						// accès public
		                .requestMatchers("/actuator/**","/swagger-ui/**", "/v3/api-docs/**").permitAll()
		                // accès salariés
		                .requestMatchers("/api/salaries/**").hasAnyAuthority("ROLE_SALARIE")

		                // accès employeurs
		                .requestMatchers("/api/employeurs/**", "/api/facteurs/**").hasAnyAuthority("ROLE_EMPLOYEUR")

		                // accès agents Carsat
		                .requestMatchers("/api/agents/**").hasAnyAuthority("ROLE_AGENT")

		                
		                .anyRequest().authenticated())
				.oauth2ResourceServer(o2 -> o2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))).build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*")); // Spécifiez l'origine de votre application Angular
		configuration.setAllowedMethods(Arrays.asList("*"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration); // Appliquez la configuration CORS aux endpoints API
		return source;
	}

}

