package com.E_Commerce.E_Commerce.Api.security;

import com.E_Commerce.E_Commerce.Api.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    
    @Value("${api.security.secret}")
    private String apisSecret;
    
    public String getToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(apisSecret);
            return JWT.create().withIssuer("e-commerce")
                    .withSubject(user.getEmail())
                    .withClaim("id",user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
            
        } catch (RuntimeException exception){
            throw new RuntimeException("Error al crear el token", exception);
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token){
        if(token == null){
            throw new RuntimeException(("Token nulo"));
        }
        DecodedJWT verifier = null;
        try{
            Algorithm algorithm= Algorithm.HMAC256(apisSecret);
            verifier = JWT.require(algorithm).withIssuer("e-commerce")
                    .build().verify(token);
            return verifier.getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Verifier invalido");
        }
    }
}
