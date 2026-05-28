package io.github.getulio222.projetofiscal.config.security;

import io.github.getulio222.projetofiscal.dto.auth.LoginDTORequest;
import io.github.getulio222.projetofiscal.dto.auth.TokenDTOResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTOResponse> login(
            @RequestBody @Valid LoginDTORequest dto) {

        return ResponseEntity.ok(authService.login(dto));
    }
}