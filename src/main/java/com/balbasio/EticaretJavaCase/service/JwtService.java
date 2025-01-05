package com.balbasio.EticaretJavaCase.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.balbasio.EticaretJavaCase.enums.ERole;
import com.balbasio.EticaretJavaCase.repository.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
    public class JwtService {

        private final String secretKey = "qBz1+!rUc7w$eS2#";
        private final String issuer = "EticaretAppProje";
        private final Long expiresAt = 1000L *60 * 60; //60dk token süresi

        private final String USER_KEY = "USER_ETICARETAPP";
        private final String VERIFICATION_TOKEN_KEY = "VERIFICATION_TOKEN";

        Algorithm algorithm = Algorithm.HMAC512(secretKey);
        Date createdDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis() + expiresAt);
    public Optional<String> createToken(Long userId){
        String token;
        try{
            token = JWT.create()
                    .withAudience()
                    .withClaim(USER_KEY,userId) // DİKKAT!!! buralara(Claim) eklediğiniz datalar şifrelenmez
                    .withIssuer(issuer) // jwt yi üreten sahiplik
                    .withIssuedAt(new Date()) // jwt üretilme zamanı
                    .withExpiresAt(new Date(System.currentTimeMillis()+expiresAt))// jwt nin sona erme tarihi
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        }catch (Exception exception){
            return Optional.empty();
        }
    }
        public String createUserToken(Long authId, ERole userRole) {
            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(createdDate)
                    .withExpiresAt(expirationDate)
                    .withClaim(USER_KEY, authId)
                    .withClaim("role",userRole.name())
                    .sign(algorithm);
        }

        public String createVerificationToken(Long authId) {
            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(createdDate)
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000L * 60 * 5)) //5dk doğrulama süresi
                    .withClaim(VERIFICATION_TOKEN_KEY, authId)
                    .sign(algorithm);
        }


        public Optional<Long> validateToken(String token) {
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
            if(Objects.isNull(decodedJWT)) return Optional.empty();
            return Optional.of(decodedJWT.getClaim(USER_KEY).asLong());
        }

        public Optional<Long> validateVerificationToken(String token) {
            DecodedJWT decodedJWT = JWT.require(algorithm).build().verify(token);
            if(Objects.isNull(decodedJWT)) return Optional.empty();
            return Optional.of(decodedJWT.getClaim(VERIFICATION_TOKEN_KEY).asLong());
        }

    public Optional<Long> getIdByToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if(decodedJWT == null)
                return Optional.empty();
            Long userId = decodedJWT.getClaim(USER_KEY).asLong();
            return Optional.of(userId);
        }catch (Exception exception){
            return  Optional.empty();
        }
    }



    }

