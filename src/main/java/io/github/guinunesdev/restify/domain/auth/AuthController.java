package io.github.guinunesdev.restify.domain.auth;

import io.github.guinunesdev.restify.security.JwtTokenProvider;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthenticationManager authManager,
                          JwtTokenProvider tokenProvider) {
        this.authManager = authManager;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
        String token = tokenProvider.generateToken(req.getUsername());
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @Getter @Setter
    public static class LoginRequest {
        private String username;
        private String password;
    }
    @Getter
    public static class LoginResponse {
        private final String token;
        public LoginResponse(String token) { this.token = token; }
    }
}