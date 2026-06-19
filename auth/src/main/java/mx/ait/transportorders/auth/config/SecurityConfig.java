package mx.ait.transportorders.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.auth.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						authRequest -> authRequest.requestMatchers("/auth/**", 
								"/v3/api-docs", "/v3/api-docs/**",
								"/swagger-ui/**", "/swagger-ui.html").permitAll().anyRequest()
						.authenticated()).sessionManagement(
						sessionManager -> sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authProvider)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();

	}

}
