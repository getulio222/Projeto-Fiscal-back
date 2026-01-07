package io.github.getulio222.cadastramento.config.security;

import io.github.getulio222.cadastramento.dto.LoginDTORequest;
import io.github.getulio222.cadastramento.dto.TokenDTOResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public TokenDTOResponse login(LoginDTORequest dto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.usuario(),
                        dto.senha()
                )
        );

        String token = jwtService.generateToken(authentication.getName());

        return new TokenDTOResponse(token, "Bearer");
    }
}
