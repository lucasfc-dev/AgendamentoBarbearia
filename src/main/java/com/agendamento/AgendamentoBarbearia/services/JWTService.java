package com.agendamento.AgendamentoBarbearia.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JWTService {
    private Algorithm algorithm;

    public JWTService(@Value("${api.config.security.secret}") String secret) {
        algorithm = Algorithm.HMAC256(secret);
    }


    public String generateToken(String username){
        try {
            return JWT.create()
                    .withIssuer("barbearia-api")
                    .withSubject(username)
                    .withExpiresAt(getExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            return "";
        }
    }

    public String verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("barbearia-api")
                    .build();
            var decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant getExpirationTime() {
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }
}
