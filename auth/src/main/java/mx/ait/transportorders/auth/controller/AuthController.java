package mx.ait.transportorders.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mx.ait.transportorders.auth.dto.AuthResponse;
import mx.ait.transportorders.auth.dto.LoginRequest;
import mx.ait.transportorders.auth.dto.RegisterRequest;
import mx.ait.transportorders.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "auth", description = "Endpoints para el manejo de la seguridad con jwt")
public class AuthController {

	private final AuthService authService;

	@PostMapping(value = "login")
	@Operation(summary = "Autenticación de usuario", description = "Autenticación para obtener el token jwt")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		
		return ResponseEntity.ok(authService.login(request));
	}

	@PostMapping(value = "register")
	@Operation(summary = "Registro de usuario", description = "Registro de usuario para obtener el token jwt")
	public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
		
		return ResponseEntity.ok(authService.register(request));
	}
}
