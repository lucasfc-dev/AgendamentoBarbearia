package com.agendamento.AgendamentoBarbearia.services;

import com.agendamento.AgendamentoBarbearia.entities.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.AlgorithmConstraints;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

@Service
public class JWTService {
    private final Algorithm algorithm;

    public JWTService(
            @Value("${api.security.token.secret}") String secret
    ) {
        this.algorithm = Algorithm.HMAC256(secret);
    }

    public String generateToken(User user){
        try{
            return JWT.create()
                    .withIssuer("test-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token",e);
        }
    }

    public String validateToken(String token){
        try{
            return JWT.require(algorithm)
                    .withIssuer("test-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant getExpirationDate(){
        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
    }
}
