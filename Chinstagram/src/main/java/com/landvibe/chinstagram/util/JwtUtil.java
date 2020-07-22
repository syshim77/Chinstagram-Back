package com.landvibe.chinstagram.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private String SECRET_KEY = "SECRETKEY";
    private Date EXPIRED_TIME = new Date(System.currentTimeMillis() + 1000 * 10);
    private String ISSUER = "SYS";

    public String createToken() {
        return JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(EXPIRED_TIME)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }

    public void verifyToken(String givenToken) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .withIssuer(ISSUER)
                .build();

        verifier.verify(givenToken);
    }
}
