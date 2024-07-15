package br.ufsm.csi.gpsmanager.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceJWT {
    // org.springframework.security.core.userdetails.User;
    public String gerarToken(User user) {
        try {
            //TODO: Mudar a chave para algo mais seguro
            Algorithm algorithm = Algorithm.HMAC256("CSI2024");
            return JWT.create()
                    .withIssuer("API GPS Manager")
                    .withSubject(user.getUsername())
                    //TODO: Implementar a permissão do usuário (remover o hardcode)
                    .withClaim("ROLE",
                            "USER")
                            //user.getAuthorities().stream().toList().get(0).toString())
                    .withExpiresAt(dataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar o token JWT", e);
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String getSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("CSI2024");
            return JWT.require(algorithm)
                    .withIssuer("API GPS Manager")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Token inválido ou expirado");
        }
    }

    public boolean validateToken(String token, UserDetails userDetails) {
       final String userLogin = getSubject(token);
       return (userLogin.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return LocalDateTime.now().isAfter(LocalDateTime.ofInstant(dataExpiracao(), ZoneOffset.of("-03:00")));
    }
}